package senegai.server.persistence.postgres

import tools.jackson.databind.ObjectMapper
import tools.jackson.module.kotlin.jacksonObjectMapper
import tools.jackson.module.kotlin.readValue
import java.sql.ResultSet

internal val jsonbObjectMapper: ObjectMapper = jacksonObjectMapper()

internal fun toJsonb(value: Any?): String? = value?.let { jsonbObjectMapper.writeValueAsString(it) }

internal inline fun <reified T : Any> ResultSet.getJsonb(columnLabel: String): T =
    jsonbObjectMapper.readValue<T>(getString(columnLabel))

internal inline fun <reified T : Any> ResultSet.getJsonbOrNull(columnLabel: String): T? =
    getString(columnLabel)?.let { jsonbObjectMapper.readValue<T>(it) }

/**
 * Turns free text into the literal part of an `ILIKE` pattern, so that a query containing
 * `%`, `_` or the escape character itself matches those characters instead of acting as a wildcard.
 */
internal fun String.escapeForLikePattern(): String =
    replace("""\""", """\\""")
        .replace("%", """\%""")
        .replace("_", """\_""")
