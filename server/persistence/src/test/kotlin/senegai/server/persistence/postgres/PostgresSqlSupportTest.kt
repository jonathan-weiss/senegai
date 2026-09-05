package senegai.server.persistence.postgres

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.jdbc.core.SqlParameterValue
import senegai.server.service.bo.AppellatioComis
import java.lang.reflect.Proxy
import java.sql.ResultSet
import java.sql.Types
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

private enum class Salutation { MR, MS }

private data class Address(val street: String, val town: String)

/**
 * A [ResultSet] backed by a map, built through a proxy because only the two getters
 * [columnValue] uses have to answer and [ResultSet] declares far too many methods to implement.
 */
private fun resultSetOf(vararg columns: Pair<String, Any?>): ResultSet {
    val values = columns.toMap()
    return Proxy.newProxyInstance(
        ResultSet::class.java.classLoader,
        arrayOf(ResultSet::class.java),
    ) { _, method, args ->
        val columnLabel = args[0] as String
        require(values.containsKey(columnLabel)) { "No column '$columnLabel' in the result set." }
        when (method.name) {
            "getString" -> values[columnLabel]
            "getObject" -> values[columnLabel]
            else -> throw UnsupportedOperationException(method.name)
        }
    } as ResultSet
}

class ColumnValueTest {

    @Test
    fun `reads the scalar types a column can be declared with`() {
        val id = UUID.randomUUID()
        val resultSet = resultSetOf(
            "ID" to id,
            "NAME" to "Ada",
            "AGE" to 36,
            "VEGETARIAN" to true,
        )

        assertEquals(id, resultSet.columnValue<UUID>("ID"))
        assertEquals("Ada", resultSet.columnValue<String>("NAME"))
        assertEquals(36, resultSet.columnValue<Int>("AGE"))
        assertEquals(true, resultSet.columnValue<Boolean>("VEGETARIAN"))
    }

    @Test
    fun `reads a null of every nullable scalar type`() {
        val resultSet = resultSetOf("ID" to null, "NAME" to null, "AGE" to null, "VEGETARIAN" to null)

        assertNull(resultSet.columnValue<UUID?>("ID"))
        assertNull(resultSet.columnValue<String?>("NAME"))
        assertNull(resultSet.columnValue<Int?>("AGE"))
        assertNull(resultSet.columnValue<Boolean?>("VEGETARIAN"))
    }

    @Test
    fun `reads an enum from its name`() {
        assertEquals(Salutation.MS, resultSetOf("SALUTATION" to "MS").columnValue<Salutation>("SALUTATION"))
        assertNull(resultSetOf("SALUTATION" to null).columnValue<Salutation?>("SALUTATION"))
    }

    @Test
    fun `reads an enum from the database spelling declared for it`() {
        val resultSet = resultSetOf("APPELLATIO" to "vir-honoratus")

        assertEquals(AppellatioComis.VIR_HONORATUS, resultSet.columnValue<AppellatioComis>("APPELLATIO"))
    }

    @Test
    fun `refuses a column value that is the spelling of no enum value`() {
        val resultSet = resultSetOf("APPELLATIO" to "VIR_HONORATUS")

        val exception = assertThrows<IllegalStateException> { resultSet.columnValue<AppellatioComis>("APPELLATIO") }

        assertTrue(
            exception.message.orEmpty().contains("is stored for no value of the enum 'AppellatioComis'"),
            "Unexpected message '${exception.message}'",
        )
    }

    @Test
    fun `reads a nested item from jsonb`() {
        val resultSet = resultSetOf("HOME_ADDRESS" to """{"street":"Bahnhofstrasse 1","town":"Zurich"}""")

        assertEquals(Address("Bahnhofstrasse 1", "Zurich"), resultSet.columnValue<Address>("HOME_ADDRESS"))
        assertNull(resultSetOf("HOME_ADDRESS" to null).columnValue<Address?>("HOME_ADDRESS"))
    }

    @Test
    fun `keeps the element type of a list read from jsonb`() {
        val resultSet = resultSetOf("ADDRESSES" to """[{"street":"Bahnhofstrasse 1","town":"Zurich"}]""")

        // the element type survives only because T is reified all the way into the object mapper
        assertEquals(listOf(Address("Bahnhofstrasse 1", "Zurich")), resultSet.columnValue<List<Address>>("ADDRESSES"))
    }

    @Test
    fun `keeps the element type of a list of built-in values`() {
        val id = UUID.randomUUID()
        val resultSet = resultSetOf("REFERENCES" to """["$id"]""")

        assertEquals(listOf(id), resultSet.columnValue<List<UUID>>("REFERENCES"))
    }
}

class ParamValueTest {

    @Test
    fun `passes a value of a scalar type through unchanged`() {
        val id = UUID.randomUUID()

        assertEquals(id, paramValue(id))
        assertEquals("Ada", paramValue("Ada"))
        assertEquals(36, paramValue(36))
        assertEquals(true, paramValue(true))
        assertNull(paramValue(null))
    }

    @Test
    fun `writes an enum as its name as long as no database spelling is declared for it`() {
        val parameter = paramValue(Salutation.MS) as SqlParameterValue

        assertEquals("MS", parameter.value)
        // the column decides whether that is a text or a SQL enum value
        assertEquals(Types.OTHER, parameter.sqlType)
    }

    @Test
    fun `writes an enum as the database spelling declared for it`() {
        val parameter = paramValue(AppellatioComis.VIR_HONORATUS) as SqlParameterValue

        assertEquals("vir-honoratus", parameter.value)
    }

    @Test
    fun `writes everything without a flat representation as json`() {
        assertEquals("""{"street":"Bahnhofstrasse 1","town":"Zurich"}""", paramValue(Address("Bahnhofstrasse 1", "Zurich")))
        assertEquals("""["a","b"]""", paramValue(listOf("a", "b")))
    }
}
