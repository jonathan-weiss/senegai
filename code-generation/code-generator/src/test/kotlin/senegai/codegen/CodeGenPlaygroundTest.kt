package senegai.codegen

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import senegai.codegen.renderer.model.db.DbColumnModel
import senegai.codegen.renderer.model.db.DbSqlType
import senegai.codegen.renderer.model.db.DbTableModel

class CodeGenPlaygroundTest {

    @Test
    fun `test code generation definition data`() {
        val schemaData = fetchSchemaData()
        assertTrue(schemaData.items.isNotEmpty())
        assertTrue(schemaData.enums.isNotEmpty())
        assertTrue(schemaData.uiEntities.isNotEmpty())
        assertTrue(schemaData.items.any { it.hasPrimaryKey })
        assertTrue(schemaData.items.any { !it.hasPrimaryKey })
    }

    @Test
    fun `the essential model data passes all validations`() {
        validateSchemaData(fetchSchemaData())
    }

    @Test
    fun `test renderer model converter`() {
        val schemaData = fetchSchemaData()
        val schemaModel = convertToSchemaModel(schemaData)
        assertTrue(schemaModel.uiModel.uiEntities.isNotEmpty())
        assertTrue(schemaModel.uiModel.uiEnums.isNotEmpty())
        assertTrue(schemaModel.uiModel.uiItems.isNotEmpty())
        assertTrue(schemaModel.uiModel.uiEntitiesViews.isNotEmpty())

        assertTrue(schemaModel.beModel.items.isNotEmpty())
        assertTrue(schemaModel.beModel.enums.isNotEmpty())

        assertTrue(schemaModel.dbModel.tables.isNotEmpty())
    }

    @Test
    fun `every item with a primary key is stored in exactly one table`() {
        val schemaData = fetchSchemaData()
        val dbModel = convertToSchemaModel(schemaData).dbModel

        assertEquals(
            schemaData.items.filter { it.hasPrimaryKey }.map { it.itemId },
            dbModel.tables.map { it.itemId },
        )
    }

    @Test
    fun `a declared table and column name wins over the derived one`() {
        val dbModel = convertToSchemaModel(fetchSchemaData()).dbModel
        val company = dbModel.tables.single { it.itemName.isEqual("Company") }

        assertEquals("T_COMPANY", company.tableName)
        assertEquals("COMPANY_PK", company.primaryKeyColumn.columnName)
        assertEquals(DbSqlType.INTEGER, company.primaryKeyColumn.sqlType)
        assertEquals("COMPANY_NAME", company.columnsWithoutPrimaryKey.single().columnName)
    }

    @Test
    fun `an item without a database configuration derives its names in screaming snake case`() {
        val dbModel = convertToSchemaModel(fetchSchemaData()).dbModel
        val contact = dbModel.tables.single { it.itemName.isEqual("Contact") }

        assertEquals("CONTACT", contact.tableName)
        assertEquals("CONTACT_ID", contact.primaryKeyColumn.columnName)
        assertEquals(DbSqlType.UUID, contact.primaryKeyColumn.sqlType)
        assertEquals("ALL_KNOWN_NICKNAMES", contact.column("AllKnownNicknames").columnName)
    }

    @Test
    fun `an attribute without a flat relational representation is stored as jsonb`() {
        val dbModel = convertToSchemaModel(fetchSchemaData()).dbModel
        val contact = dbModel.tables.single { it.itemName.isEqual("Contact") }

        // a nested item, a list of nested items and a list of built-in types
        assertEquals(DbSqlType.JSONB, contact.column("HomeAddress").sqlType)
        assertEquals(DbSqlType.JSONB, contact.column("MandatoryAddresses").sqlType)
        assertEquals(DbSqlType.JSONB, contact.column("AllKnownNicknames").sqlType)

        assertEquals(DbSqlType.TEXT, contact.column("ContactSalutation").sqlType)
        assertEquals(DbSqlType.BOOLEAN, contact.column("Vegetarian").sqlType)
        assertEquals(DbSqlType.INTEGER, contact.column("Age").sqlType)
        // a reference is stored as the primary key of the referenced item, here a STRING one
        assertEquals(DbSqlType.TEXT, contact.column("MyReferenceToAddress").sqlType)
    }

    private fun DbTableModel.column(attributeName: String): DbColumnModel =
        columns.single { it.attributeName.isEqual(attributeName) }
}
