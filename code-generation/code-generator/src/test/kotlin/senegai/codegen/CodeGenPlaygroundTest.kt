package senegai.codegen

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

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
    fun `test renderer model converter`() {
        val schemaData = fetchSchemaData()
        val schemaModel = convertToSchemaModel(schemaData)
        assertTrue(schemaModel.uiModel.uiEntities.isNotEmpty())
        assertTrue(schemaModel.uiModel.uiEnums.isNotEmpty())
        assertTrue(schemaModel.uiModel.uiItems.isNotEmpty())
        assertTrue(schemaModel.uiModel.uiEntitiesViews.isNotEmpty())

        assertTrue(schemaModel.beModel.items.isNotEmpty())
        assertTrue(schemaModel.beModel.enums.isNotEmpty())
    }

    @Test
    fun `only items with a primary key get the whole stack`() {
        val schemaModel = convertToSchemaModel(fetchSchemaData())

        // Country is only a nested item, it has no primary key and therefore no controller,
        // no service, no repository and no reference components.
        assertTrue(schemaModel.beModel.itemsWithPrimaryKey.size < schemaModel.beModel.items.size)
        assertTrue(schemaModel.uiModel.uiItemsWithPrimaryKey.size < schemaModel.uiModel.uiItems.size)

        assertEquals(
            schemaModel.beModel.itemsWithPrimaryKey.map { it.itemName },
            schemaModel.uiModel.uiItemsWithPrimaryKey.map { it.itemName },
        )
    }

    @Test
    fun `every item is mapped exactly once, independent of the UiEntities it appears in`() {
        val schemaModel = convertToSchemaModel(fetchSchemaData())

        assertEquals(
            schemaModel.uiModel.uiItems.map { it.itemId }.distinct().size,
            schemaModel.uiModel.uiItems.size,
        )
        assertEquals(
            schemaModel.beModel.items.map { it.itemId }.distinct().size,
            schemaModel.beModel.items.size,
        )
    }
}
