package senegai.codegen.renderer.converter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import senegai.codegen.schema.*

internal class HierarchicalItemSearchTest {

    // Test helper to create ItemId instances
    private fun itemId(name: String) = object : ItemId {
        override val itemName: String = name
    }

    @Test
    fun `happy case - simple linear hierarchy`() {
        // Given: A -> B -> C
        val itemIdC = itemId("ItemC")
        val itemIdB = itemId("ItemB")
        val itemIdA = itemId("ItemA")

        val itemC = Item(
            itemId = itemIdC,
            attributes = listOf(
                createItemAttribute("field1",  BuiltInType.STRING)
            )
        )

        val itemB = Item(
            itemId = itemIdB,
            attributes = listOf(
                createItemAttribute("nestedC",  itemIdC)
            )
        )

        val itemA = Item(
            itemId = itemIdA,
            attributes = listOf(
                createItemAttribute("nestedB",  itemIdB)
            )
        )

        val allItems = listOf(itemA, itemB, itemC)

        // When
        val result = HierarchicalItemSearch.findAllItemNames(itemA, allItems)

        // Then
        assertEquals(setOf(itemIdA, itemIdB, itemIdC), result)
    }

    @Test
    fun `no nesting - only built-in types`() {
        // Given: A with only primitive attributes
        val itemIdA = itemId("ItemA")

        val itemA = Item(
            itemId = itemIdA,
            attributes = listOf(
                createItemAttribute("field1",  BuiltInType.STRING),
                createItemAttribute("field2",  BuiltInType.NUMBER),
                createItemAttribute("field3",  BuiltInType.BOOLEAN)
            )
        )

        val allItems = listOf(itemA)

        // When
        val result = HierarchicalItemSearch.findAllItemNames(itemA, allItems)

        // Then
        assertEquals(setOf(itemIdA), result)
    }

    @Test
    fun `circular reference - A references B references A`() {
        // Given: A -> B -> A (circular)
        val itemIdA = itemId("ItemA")
        val itemIdB = itemId("ItemB")

        val itemA = Item(
            itemId = itemIdA,
            attributes = listOf(
                createItemAttribute("nestedB",  itemIdB)
            )
        )

        val itemB = Item(
            itemId = itemIdB,
            attributes = listOf(
                createItemAttribute("nestedA",  itemIdA)
            )
        )

        val allItems = listOf(itemA, itemB)

        // When
        val result = HierarchicalItemSearch.findAllItemNames(itemA, allItems)

        // Then - should include both items exactly once, no infinite loop
        assertEquals(setOf(itemIdA, itemIdB), result)
    }

    @Test
    fun `self reference - A references itself`() {
        // Given: A -> A
        val itemIdA = itemId("ItemA")

        val itemA = Item(
            itemId = itemIdA,
            attributes = listOf(
                createItemAttribute("self",  itemIdA)
            )
        )

        val allItems = listOf(itemA)

        // When
        val result = HierarchicalItemSearch.findAllItemNames(itemA, allItems)

        // Then - should include A exactly once
        assertEquals(setOf(itemIdA), result)
    }

    @Test
    fun `diamond pattern - A references B and C, both reference D`() {
        // Given: A -> B -> D
        //        A -> C -> D
        val itemIdD = itemId("ItemD")
        val itemIdB = itemId("ItemB")
        val itemIdC = itemId("ItemC")
        val itemIdA = itemId("ItemA")

        val itemD = Item(
            itemId = itemIdD,
            attributes = listOf(
                createItemAttribute("field1",  BuiltInType.STRING)
            )
        )

        val itemB = Item(
            itemId = itemIdB,
            attributes = listOf(
                createItemAttribute("nestedD",  itemIdD)
            )
        )

        val itemC = Item(
            itemId = itemIdC,
            attributes = listOf(
                createItemAttribute("nestedD",  itemIdD)
            )
        )

        val itemA = Item(
            itemId = itemIdA,
            attributes = listOf(
                createItemAttribute("nestedB",  itemIdB),
                createItemAttribute("nestedC",  itemIdC)
            )
        )

        val allItems = listOf(itemA, itemB, itemC, itemD)

        // When
        val result = HierarchicalItemSearch.findAllItemNames(itemA, allItems)

        // Then - D should appear only once despite being referenced twice
        assertEquals(setOf(itemIdA, itemIdB, itemIdC, itemIdD), result)
    }

    @Test
    fun `multiple references to same item from single parent`() {
        // Given: A has two attributes both referencing B
        val itemIdB = itemId("ItemB")
        val itemIdA = itemId("ItemA")

        val itemB = Item(
            itemId = itemIdB,
            attributes = listOf(
                createItemAttribute("field1",  BuiltInType.STRING)
            )
        )

        val itemA = Item(
            itemId = itemIdA,
            attributes = listOf(
                createItemAttribute("nestedB1",  itemIdB),
                createItemAttribute("nestedB2",  itemIdB)
            )
        )

        val allItems = listOf(itemA, itemB)

        // When
        val result = HierarchicalItemSearch.findAllItemNames(itemA, allItems)

        // Then - B should appear only once
        assertEquals(setOf(itemIdA, itemIdB), result)
    }

    @Test
    fun `deep nesting - five levels deep`() {
        // Given: A -> B -> C -> D -> E
        val itemIdE = itemId("ItemE")
        val itemIdD = itemId("ItemD")
        val itemIdC = itemId("ItemC")
        val itemIdB = itemId("ItemB")
        val itemIdA = itemId("ItemA")

        val itemE = Item(
            itemId = itemIdE,
            attributes = listOf(
                createItemAttribute("field1",  BuiltInType.STRING)
            )
        )

        val itemD = Item(
            itemId = itemIdD,
            attributes = listOf(
                createItemAttribute("nestedE",  itemIdE)
            )
        )

        val itemC = Item(
            itemId = itemIdC,
            attributes = listOf(
                createItemAttribute("nestedD",  itemIdD)
            )
        )

        val itemB = Item(
            itemId = itemIdB,
            attributes = listOf(
                createItemAttribute("nestedC",  itemIdC)
            )
        )

        val itemA = Item(
            itemId = itemIdA,
            attributes = listOf(
                createItemAttribute("nestedB",  itemIdB)
            )
        )

        val allItems = listOf(itemA, itemB, itemC, itemD, itemE)

        // When
        val result = HierarchicalItemSearch.findAllItemNames(itemA, allItems)

        // Then
        assertEquals(setOf(itemIdA, itemIdB, itemIdC, itemIdD, itemIdE), result)
    }

    @Test
    fun `mixed attribute types - ItemId, EntityId, EnumId, and BuiltInType`() {
        // Given: Item with various attribute types, only ItemId should be followed
        val enumId = object : EnumId {
            override val enumName: String = "Status"
        }

        val entityId = object : EntityId {
            override val entityName: String = "User"
        }

        val itemIdB = itemId("ItemB")
        val itemIdA = itemId("ItemA")

        val itemB = Item(
            itemId = itemIdB,
            attributes = listOf(
                createItemAttribute("field1",  BuiltInType.STRING)
            )
        )

        val itemA = Item(
            itemId = itemIdA,
            attributes = listOf(
                createItemAttribute("stringField",  BuiltInType.STRING),
                createItemAttribute("numberField",  BuiltInType.NUMBER),
                createItemAttribute("boolField",  BuiltInType.BOOLEAN),
                createItemAttribute("enumField",  enumId),
                createItemAttribute("entityField",  entityId),
                createItemAttribute("nestedItem",  itemIdB)
            )
        )

        val allItems = listOf(itemA, itemB)

        // When
        val result = HierarchicalItemSearch.findAllItemNames(itemA, allItems)

        // Then - only ItemId references should be followed
        assertEquals(setOf(itemIdA, itemIdB), result)
    }

    @Test
    fun `empty attributes list`() {
        // Given: Item with no attributes
        val itemIdA = itemId("ItemA")

        val itemA = Item(
            itemId = itemIdA,
            attributes = emptyList()
        )

        val allItems = listOf(itemA)

        // When
        val result = HierarchicalItemSearch.findAllItemNames(itemA, allItems)

        // Then
        assertEquals(setOf(itemIdA), result)
    }

    @Test
    fun `referenced item not in allItems list - graceful handling`() {
        // Given: A references B, but B is not in allItems
        val itemIdA = itemId("ItemA")
        val itemIdB = itemId("ItemB")

        val itemA = Item(
            itemId = itemIdA,
            attributes = listOf(
                createItemAttribute("nestedB",  itemIdB)
            )
        )

        val allItems = listOf(itemA) // ItemB is missing

        // When
        val result = HierarchicalItemSearch.findAllItemNames(itemA, allItems)

        // Then - should only include ItemA, skip missing ItemB gracefully
        assertEquals(setOf(itemIdA, itemIdB), result)
    }
    
    private fun createItemAttribute(name: String, type: ItemAttributeType = BuiltInType.STRING): ItemAttribute {
        return ItemAttribute(
            attributeName = name,
            isNullable = false,
            isMultiple = false,
            type = type,
        )
    }
}
