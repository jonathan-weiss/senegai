package senegai.server.persistence.postgres

import tools.jackson.databind.ObjectMapper
import tools.jackson.module.kotlin.jacksonObjectMapper
import tools.jackson.module.kotlin.readValue
import java.sql.ResultSet
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
 */
@Suppress("UNCHECKED_CAST")
internal inline fun <reified T> ResultSet.columnValue(columnLabel: String): T = when {
    T::class == UUID::class -> getObject(columnLabel, UUID::class.java)
    T::class == String::class -> getString(columnLabel)
    T::class == Int::class -> getObject(columnLabel, Int::class.javaObjectType)
    T::class == Boolean::class -> getObject(columnLabel, Boolean::class.javaObjectType)
    T::class.java.isEnum -> getString(columnLabel)?.let { name ->
        T::class.java.enumConstants.single { (it as Enum<*>).name == name }
    }
    // reading it through the reified T keeps the full generic type, e.g. List<ArticulusInteriorBO>
    else -> getString(columnLabel)?.let { jsonbObjectMapper.readValue<T>(it) }
} as T

/** The counterpart of [columnValue]: turns a business object value into the JDBC parameter it is stored as. */
internal fun paramValue(value: Any?): Any? = when (value) {
    null, is String, is Int, is Boolean, is UUID -> value
    is Enum<*> -> value.name
    else -> toJsonb(value)
}

/**
 * Turns free text into the literal part of an `ILIKE` pattern, so that a query containing
 * `%`, `_` or the escape character itself matches those characters instead of acting as a wildcard.
 */
internal fun String.escapeForLikePattern(): String =
    replace("""\""", """\\""")
        .replace("%", """\%""")
        .replace("_", """\_""")
