package senegai.server.persistence.postgres

import org.springframework.jdbc.core.SqlParameterValue
import tools.jackson.databind.ObjectMapper
import tools.jackson.module.kotlin.jacksonObjectMapper
import tools.jackson.module.kotlin.readValue
import java.sql.ResultSet
import java.sql.Types
import java.util.UUID

internal val jsonbObjectMapper: ObjectMapper = jacksonObjectMapper()

internal fun toJsonb(value: Any?): String? = value?.let { jsonbObjectMapper.writeValueAsString(it) }

internal inline fun <reified T : Any> ResultSet.getJsonb(columnLabel: String): T =
    jsonbObjectMapper.readValue<T>(getString(columnLabel))

internal inline fun <reified T : Any> ResultSet.getJsonbOrNull(columnLabel: String): T? =
    getString(columnLabel)?.let { jsonbObjectMapper.readValue<T>(it) }

/**
 * Reads one column as the type the caller expects it in, so that every generated row mapper
 * line looks the same regardless of the column's type or nullability.
 *
 * [T] is taken from the business object's constructor parameter, and it is the same class for
 * `String` and `String?`, therefore nullability needs no case of its own: a SQL NULL in a
 * column the business object declares non-null fails on the cast, which is what should happen.
 * Anything that is neither a scalar nor an enum has no flat relational representation and is
 * therefore stored as `jsonb` — a nested item or a list of any kind.
 *
 * An enum value is read through the database spelling of its values, so that a column holding
 * `PG-13` becomes `MpaaRating.P_G13`.
 *
 * A `Double` is read as a `BigDecimal` rather than with `getObject(_, Double::class)`, because
 * the driver refuses that conversion for a `numeric` column ("conversion to class
 * java.lang.Double from numeric not supported") — and `numeric` is exactly what an existing
 * schema spells a decimal column as. `getBigDecimal` accepts every numeric column type and
 * returns `null` for a SQL NULL.
 */
@Suppress("UNCHECKED_CAST")
internal inline fun <reified T> ResultSet.columnValue(columnLabel: String): T = when {
    T::class == UUID::class -> getObject(columnLabel, UUID::class.java)
    T::class == String::class -> getString(columnLabel)
    T::class == Int::class -> getObject(columnLabel, Int::class.javaObjectType)
    T::class == Double::class -> getBigDecimal(columnLabel)?.toDouble()
    T::class == Boolean::class -> getObject(columnLabel, Boolean::class.javaObjectType)
    T::class.java.isEnum -> getString(columnLabel)?.let { enumValueOf(T::class.java, it) }
    // reading it through the reified T keeps the full generic type, e.g. List<ArticulusInteriorBO>
    else -> getString(columnLabel)?.let { jsonbObjectMapper.readValue<T>(it) }
} as T

/**
 * The counterpart of [columnValue]: turns a business object value into the JDBC parameter it is
 * stored as.
 *
 * An enum value is passed as its database spelling with an unspecified SQL type, which lets
 * PostgreSQL take the type from the column it is written to: a SQL enum type rejects a parameter
 * sent as a string ("column is of type mpaa_rating but expression is of type character varying"),
 * while a `text` column accepts the very same parameter.
 */
internal fun paramValue(value: Any?): Any? = when (value) {
    null, is String, is Int, is Double, is Boolean, is UUID -> value
    is Enum<*> -> SqlParameterValue(Types.OTHER, databaseValueOf(value))
    else -> toJsonb(value)
}

private val databaseEnumByEnumClass: Map<Class<*>, DatabaseEnum> =
    (DATABASE_ENUMS + GENERATED_DATABASE_ENUMS).associateBy { it.enumClass }

private val databaseValueByEnumValue: Map<Enum<*>, String> = databaseEnumByEnumClass.values
    .flatMap { it.databaseValueByEnumValue.entries }
    .associate { (enumValue, databaseValue) -> enumValue to databaseValue }

/**
 * How [enumValue] is spelled in the database. An enum that is declared nowhere — a test fixture,
 * as every enum of the model is generated into `GENERATED_DATABASE_ENUMS` — is stored as its name.
 */
private fun databaseValueOf(enumValue: Enum<*>): String = databaseValueByEnumValue[enumValue] ?: enumValue.name

/**
 * The counterpart of [databaseValueOf]: the value of [enumClass] that is stored as [databaseValue].
 * It is internal, not private, because the inline [columnValue] calls it.
 */
internal fun enumValueOf(enumClass: Class<*>, databaseValue: String): Enum<*> {
    val databaseEnum = databaseEnumByEnumClass[enumClass]
        ?: return enumClass.enumConstants.map { it as Enum<*> }.single { it.name == databaseValue }

    return databaseEnum.enumValueByDatabaseValue[databaseValue]
        ?: error(
            "The value '$databaseValue' is stored for no value of the enum '${enumClass.simpleName}'. " +
                    "Stored are ${databaseEnum.enumValueByDatabaseValue.keys}."
        )
}

/**
 * Turns free text into the literal part of an `ILIKE` pattern, so that a query containing
 * `%`, `_` or the escape character itself matches those characters instead of acting as a wildcard.
 */
internal fun String.escapeForLikePattern(): String =
    replace("""\""", """\\""")
        .replace("%", """\%""")
        .replace("_", """\_""")
