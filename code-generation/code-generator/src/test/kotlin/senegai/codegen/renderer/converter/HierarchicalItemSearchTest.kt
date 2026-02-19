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
                ItemAttribute("field1", ItemAttributeCardinality.EXACTLY_ONE, BuiltInType.STRING)
            )
        )

        val itemB = Item(
            itemId = itemIdB,
            attributes = listOf(
                ItemAttribute("nestedC", ItemAttributeCardinality.EXACTLY_ONE, itemIdC)
            )
        )

        val itemA = Item(
            itemId = itemIdA,
            attributes = listOf(
                ItemAttribute("nestedB", ItemAttributeCardinality.EXACTLY_ONE, itemIdB)
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
                ItemAttribute("field1", ItemAttributeCardinality.EXACTLY_ONE, BuiltInType.STRING),
                ItemAttribute("field2", ItemAttributeCardinality.EXACTLY_ONE, BuiltInType.NUMBER),
                ItemAttribute("field3", ItemAttributeCardinality.ZERO_TO_ONE, BuiltInType.BOOLEAN)
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
                ItemAttribute("nestedB", ItemAttributeCardinality.EXACTLY_ONE, itemIdB)
            )
        )

        val itemB = Item(
            itemId = itemIdB,
            attributes = listOf(
                ItemAttribute("nestedA", ItemAttributeCardinality.EXACTLY_ONE, itemIdA)
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
                ItemAttribute("self", ItemAttributeCardinality.ZERO_TO_ONE, itemIdA)
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
                ItemAttribute("field1", ItemAttributeCardinality.EXACTLY_ONE, BuiltInType.STRING)
            )
        )

        val itemB = Item(
            itemId = itemIdB,
            attributes = listOf(
                ItemAttribute("nestedD", ItemAttributeCardinality.EXACTLY_ONE, itemIdD)
            )
        )

        val itemC = Item(
            itemId = itemIdC,
            attributes = listOf(
                ItemAttribute("nestedD", ItemAttributeCardinality.EXACTLY_ONE, itemIdD)
            )
        )

        val itemA = Item(
            itemId = itemIdA,
            attributes = listOf(
                ItemAttribute("nestedB", ItemAttributeCardinality.EXACTLY_ONE, itemIdB),
                ItemAttribute("nestedC", ItemAttributeCardinality.EXACTLY_ONE, itemIdC)
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
                ItemAttribute("field1", ItemAttributeCardinality.EXACTLY_ONE, BuiltInType.STRING)
            )
        )

        val itemA = Item(
            itemId = itemIdA,
            attributes = listOf(
                ItemAttribute("nestedB1", ItemAttributeCardinality.EXACTLY_ONE, itemIdB),
                ItemAttribute("nestedB2", ItemAttributeCardinality.ZERO_TO_ONE, itemIdB)
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
                ItemAttribute("field1", ItemAttributeCardinality.EXACTLY_ONE, BuiltInType.STRING)
            )
        )

        val itemD = Item(
            itemId = itemIdD,
            attributes = listOf(
                ItemAttribute("nestedE", ItemAttributeCardinality.EXACTLY_ONE, itemIdE)
            )
        )

        val itemC = Item(
            itemId = itemIdC,
            attributes = listOf(
                ItemAttribute("nestedD", ItemAttributeCardinality.EXACTLY_ONE, itemIdD)
            )
        )

        val itemB = Item(
            itemId = itemIdB,
            attributes = listOf(
                ItemAttribute("nestedC", ItemAttributeCardinality.EXACTLY_ONE, itemIdC)
            )
        )

        val itemA = Item(
            itemId = itemIdA,
            attributes = listOf(
                ItemAttribute("nestedB", ItemAttributeCardinality.EXACTLY_ONE, itemIdB)
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
                ItemAttribute("field1", ItemAttributeCardinality.EXACTLY_ONE, BuiltInType.STRING)
            )
        )

        val itemA = Item(
            itemId = itemIdA,
            attributes = listOf(
                ItemAttribute("stringField", ItemAttributeCardinality.EXACTLY_ONE, BuiltInType.STRING),
                ItemAttribute("numberField", ItemAttributeCardinality.EXACTLY_ONE, BuiltInType.NUMBER),
                ItemAttribute("boolField", ItemAttributeCardinality.EXACTLY_ONE, BuiltInType.BOOLEAN),
                ItemAttribute("enumField", ItemAttributeCardinality.EXACTLY_ONE, enumId),
                ItemAttribute("entityField", ItemAttributeCardinality.EXACTLY_ONE, entityId),
                ItemAttribute("nestedItem", ItemAttributeCardinality.EXACTLY_ONE, itemIdB)
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
                ItemAttribute("nestedB", ItemAttributeCardinality.EXACTLY_ONE, itemIdB)
            )
        )

        val allItems = listOf(itemA) // ItemB is missing

        // When
        val result = HierarchicalItemSearch.findAllItemNames(itemA, allItems)

        // Then - should only include ItemA, skip missing ItemB gracefully
        assertEquals(setOf(itemIdA, itemIdB), result)
    }
}
