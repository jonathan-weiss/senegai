package senegai.codegen.validation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import senegai.model.schema.BuiltInType
import senegai.model.schema.EnumId
import senegai.model.schema.EnumType
import senegai.model.schema.ExampleDataCategory
import senegai.model.schema.Item
import senegai.model.schema.ItemAttribute
import senegai.model.schema.ItemAttributeType
import senegai.model.schema.ItemId
import senegai.model.schema.SchemaData
import senegai.model.schema.UiBlock
import senegai.model.schema.UiEntity
import senegai.model.schema.UiEntityEditorColumn
import senegai.model.schema.UiEntityEditorEntityNestedItemConfiguration
import senegai.model.schema.UiEntityEditorItemConfiguration
import senegai.model.schema.UiEntityEditorRootItemConfiguration
import senegai.model.schema.UiEntityEditorTab
import senegai.model.schema.UiEntityEditorView
import senegai.model.schema.UiEntitySearchResultView
import senegai.model.schema.UiItem
import senegai.model.schema.UiItemAttributeBlock

class SchemaDataValidatorTest {

    @Test
    fun `a schema without any violation is valid`() {
        SchemaDataValidator().validate(
            schemaData(
                items = listOf(contact, address),
                enums = listOf(gender),
                uiItems = listOf(
                    UiItem(itemId = contactId, displayAttributeNames = listOf("Firstname", "ContactSalutation")),
                    UiItem(itemId = addressId, displayAttributeNames = listOf("Street")),
                ),
                uiEntities = listOf(
                    uiEntity(
                        rootItem = contact,
                        searchResultAttributeNames = listOf("ContactId", "Firstname", "ContactSalutation"),
                        itemConfigurations = listOf(
                            rootItemConfiguration(attributeNames = listOf("ContactId", "Firstname")),
                            nestedItemConfiguration(addressId, attributeNames = listOf("Street")),
                        ),
                    )
                ),
            )
        )
    }

    @Test
    fun `an item name is declared only once`() {
        val exception = validationFailureOf(schemaData(items = listOf(contact, contact)))

        assertEquals("SchemaData > items", exception.path.toString())
        assertProblemContains(exception, "The item name 'Contact' is declared by 2 items")
    }

    @Test
    fun `an item name is in PascalCase`() {
        val lowercaseItem = Item(itemId = TestItemId("contact"), attributes = emptyList())

        val exception = validationFailureOf(schemaData(items = listOf(lowercaseItem)))

        assertEquals("SchemaData > items[0] 'contact'", exception.path.toString())
        assertProblemContains(exception, "Declare it as 'Contact' instead.")
    }

    @Test
    fun `an item is identified by one single attribute only`() {
        val itemWithTwoPrimaryKeys = Item(
            itemId = contactId,
            attributes = listOf(
                attribute(name = "ContactId", type = BuiltInType.UUID, isPrimaryKey = true),
                attribute(name = "OtherId", type = BuiltInType.UUID, isPrimaryKey = true),
            ),
        )

        val exception = validationFailureOf(schemaData(items = listOf(itemWithTwoPrimaryKeys)))

        assertEquals("SchemaData > items[0] 'Contact'", exception.path.toString())
        assertProblemContains(exception, "attributes[0] 'ContactId', attributes[1] 'OtherId'")
    }

    @Test
    fun `an item is identified by an attribute of the built-in type UUID only`() {
        val itemWithStringPrimaryKey = Item(
            itemId = contactId,
            attributes = listOf(attribute(name = "ContactId", type = BuiltInType.STRING, isPrimaryKey = true)),
        )

        val exception = validationFailureOf(schemaData(items = listOf(itemWithStringPrimaryKey)))

        assertEquals("SchemaData > items[0] 'Contact' > attributes[0] 'ContactId'", exception.path.toString())
        assertProblemContains(exception, "it is of the built-in type STRING")
    }

    @Test
    fun `an item is identified by a single mandatory attribute only`() {
        val itemWithNullablePrimaryKey = Item(
            itemId = contactId,
            attributes = listOf(
                attribute(name = "ContactId", type = BuiltInType.UUID, isNullable = true, isPrimaryKey = true),
            ),
        )

        val exception = validationFailureOf(schemaData(items = listOf(itemWithNullablePrimaryKey)))

        assertEquals("SchemaData > items[0] 'Contact' > attributes[0] 'ContactId'", exception.path.toString())
        assertProblemContains(exception, "nullable=true and multiple=false")
    }

    @Test
    fun `an attribute name is declared only once per item`() {
        val itemWithDuplicateAttribute = Item(
            itemId = contactId,
            attributes = listOf(attribute(name = "Firstname"), attribute(name = "Firstname")),
        )

        val exception = validationFailureOf(schemaData(items = listOf(itemWithDuplicateAttribute)))

        assertEquals("SchemaData > items[0] 'Contact'", exception.path.toString())
        assertProblemContains(exception, "the attribute name 'Firstname' 2 times")
    }

    @Test
    fun `an attribute name is in PascalCase`() {
        val itemWithCamelCaseAttribute = Item(
            itemId = contactId,
            attributes = listOf(attribute(name = "firstname")),
        )

        val exception = validationFailureOf(schemaData(items = listOf(itemWithCamelCaseAttribute)))

        assertEquals("SchemaData > items[0] 'Contact' > attributes[0] 'firstname'", exception.path.toString())
        assertProblemContains(exception, "Declare it as 'Firstname' instead.")
    }

    @Test
    fun `only an attribute of a built-in type declares an example data category`() {
        val itemWithEnumAttribute = Item(
            itemId = contactId,
            attributes = listOf(
                attribute(
                    name = "ContactSalutation",
                    type = genderId,
                    exampleDataCategory = ExampleDataCategory.FIRSTNAME,
                ),
            ),
        )

        val exception = validationFailureOf(schemaData(items = listOf(itemWithEnumAttribute)))

        assertEquals("SchemaData > items[0] 'Contact' > attributes[0] 'ContactSalutation'", exception.path.toString())
        assertProblemContains(exception, "it is of the enum type 'Gender'")
    }

    @Test
    fun `an enum name is declared only once`() {
        val exception = validationFailureOf(schemaData(enums = listOf(gender, gender)))

        assertEquals("SchemaData > enums", exception.path.toString())
        assertProblemContains(exception, "The enum name 'Gender' is declared by 2 enum types")
    }

    @Test
    fun `an enum name is in PascalCase`() {
        val lowercaseEnum = EnumType(enumId = TestEnumId("gender"), enumValues = listOf("Mr"))

        val exception = validationFailureOf(schemaData(enums = listOf(lowercaseEnum)))

        assertEquals("SchemaData > enums[0] 'gender'", exception.path.toString())
        assertProblemContains(exception, "Declare it as 'Gender' instead.")
    }

    @Test
    fun `an enum value is declared only once per enum type`() {
        val enumWithDuplicateValue = EnumType(enumId = genderId, enumValues = listOf("Mr", "Ms", "Mr"))

        val exception = validationFailureOf(schemaData(enums = listOf(enumWithDuplicateValue)))

        assertEquals("SchemaData > enums[0] 'Gender'", exception.path.toString())
        assertProblemContains(exception, "the value 'Mr' 2 times, namely as enumValues[0], enumValues[2]")
    }

    @Test
    fun `an enum value is in PascalCase`() {
        val enumWithLowercaseValue = EnumType(enumId = genderId, enumValues = listOf("Mr", "mrs"))

        val exception = validationFailureOf(schemaData(enums = listOf(enumWithLowercaseValue)))

        assertEquals("SchemaData > enums[0] 'Gender' > enumValues[1] 'mrs'", exception.path.toString())
        assertProblemContains(exception, "Declare it as 'Mrs' instead.")
    }

    @Test
    fun `a ui configuration shows only attributes of its own item as display attributes`() {
        val exception = validationFailureOf(
            schemaData(
                items = listOf(contact),
                uiItems = listOf(UiItem(itemId = contactId, displayAttributeNames = listOf("Firstname", "Street"))),
            )
        )

        assertEquals(
            "SchemaData > uiItems[0] 'Contact' > displayAttributeNames[1] 'Street'",
            exception.path.toString(),
        )
        assertProblemContains(exception, "the item 'Contact' has no such attribute")
    }

    @Test
    fun `a ui configuration is declared only for an item of the schema`() {
        val exception = validationFailureOf(
            schemaData(
                items = listOf(contact),
                uiItems = listOf(UiItem(itemId = addressId, displayAttributeNames = listOf("Street"))),
            )
        )

        assertEquals("SchemaData > uiItems[0] 'Address'", exception.path.toString())
        assertProblemContains(exception, "no such item is declared in the schema")
    }

    @Test
    fun `a ui configuration shows every display attribute only once`() {
        val exception = validationFailureOf(
            schemaData(
                items = listOf(contact),
                uiItems = listOf(
                    UiItem(itemId = contactId, displayAttributeNames = listOf("Firstname", "ContactId", "Firstname"))
                ),
            )
        )

        assertEquals("SchemaData > uiItems[0] 'Contact'", exception.path.toString())
        assertProblemContains(
            exception,
            "'Firstname' as a display attribute 2 times, namely as displayAttributeNames[0], " +
                    "displayAttributeNames[2]",
        )
    }

    @Test
    fun `a ui configuration shows no attribute of an item type as a display attribute`() {
        val exception = validationFailureOf(
            schemaData(
                items = listOf(contact),
                uiItems = listOf(
                    UiItem(itemId = contactId, displayAttributeNames = listOf("Firstname", "HomeAddress"))
                ),
            )
        )

        assertEquals(
            "SchemaData > uiItems[0] 'Contact' > displayAttributeNames[1] 'HomeAddress'",
            exception.path.toString(),
        )
        assertProblemContains(exception, "that attribute is of the item 'Address'")
    }

    @Test
    fun `a search result shows only attributes of the root item`() {
        val exception = validationFailureOf(
            schemaData(
                items = listOf(contact),
                uiEntities = listOf(
                    uiEntity(rootItem = contact, searchResultAttributeNames = listOf("Firstname", "Street"))
                ),
            )
        )

        assertEquals(
            "SchemaData > uiEntities[0] 'Contact' > searchResultView > attributeNames[1] 'Street'",
            exception.path.toString(),
        )
        assertProblemContains(exception, "the root item 'Contact' has no such attribute")
    }

    @Test
    fun `a search result shows no attribute of an item type`() {
        val exception = validationFailureOf(
            schemaData(
                items = listOf(contact),
                uiEntities = listOf(
                    uiEntity(rootItem = contact, searchResultAttributeNames = listOf("Firstname", "HomeAddress"))
                ),
            )
        )

        assertEquals(
            "SchemaData > uiEntities[0] 'Contact' > searchResultView > attributeNames[1] 'HomeAddress'",
            exception.path.toString(),
        )
        assertProblemContains(exception, "that attribute is of the item 'Address'")
    }

    @Test
    fun `an attribute block of the root item configuration shows only attributes of the root item`() {
        val exception = validationFailureOf(
            schemaData(
                items = listOf(contact),
                uiEntities = listOf(
                    uiEntity(
                        rootItem = contact,
                        itemConfigurations = listOf(
                            rootItemConfiguration(
                                attributeNames = listOf("Firstname"),
                                tabAttributeNames = listOf("Firstname", "Street"),
                            )
                        ),
                    )
                ),
            )
        )

        assertEquals(
            "SchemaData > uiEntities[0] 'Contact' > editorView > itemConfiguration[0] 'Contact' > " +
                    "tabs[0] 'tab.common' > columns[0] > blocks[1] 'Street'",
            exception.path.toString(),
        )
        assertProblemContains(exception, "the item 'Contact' it is configured for has no such attribute")
    }

    @Test
    fun `an attribute block of a nested item configuration shows only attributes of that nested item`() {
        val exception = validationFailureOf(
            schemaData(
                items = listOf(contact, address),
                uiEntities = listOf(
                    uiEntity(
                        rootItem = contact,
                        itemConfigurations = listOf(
                            nestedItemConfiguration(addressId, attributeNames = listOf("Street", "Firstname"))
                        ),
                    )
                ),
            )
        )

        assertEquals(
            "SchemaData > uiEntities[0] 'Contact' > editorView > itemConfiguration[0] 'Address' > " +
                    "noTab > columns[0] > blocks[1] 'Firstname'",
            exception.path.toString(),
        )
        assertProblemContains(exception, "the item 'Address' it is configured for has no such attribute")
    }

    @Test
    fun `an editor is configured only for an item of the schema`() {
        val exception = validationFailureOf(
            schemaData(
                items = listOf(contact),
                uiEntities = listOf(
                    uiEntity(
                        rootItem = contact,
                        itemConfigurations = listOf(
                            nestedItemConfiguration(addressId, attributeNames = listOf("Street"))
                        ),
                    )
                ),
            )
        )

        assertEquals(
            "SchemaData > uiEntities[0] 'Contact' > editorView > itemConfiguration[0] 'Address'",
            exception.path.toString(),
        )
        assertProblemContains(exception, "no such item is declared in the schema")
    }

    // **************
    // Test fixtures
    // **************

    private data class TestItemId(override val itemName: String) : ItemId

    private data class TestEnumId(override val enumName: String) : EnumId

    private val contactId = TestItemId("Contact")
    private val addressId = TestItemId("Address")
    private val genderId = TestEnumId("Gender")

    private val gender = EnumType(enumId = genderId, enumValues = listOf("Mr", "Ms", "Mrs"))

    private val contact = Item(
        itemId = contactId,
        attributes = listOf(
            attribute(name = "ContactId", type = BuiltInType.UUID, isPrimaryKey = true),
            attribute(name = "Firstname", exampleDataCategory = ExampleDataCategory.FIRSTNAME),
            attribute(name = "ContactSalutation", type = genderId),
            attribute(name = "HomeAddress", type = addressId),
        ),
    )

    private val address = Item(
        itemId = addressId,
        attributes = listOf(attribute(name = "Street")),
    )

    private fun attribute(
        name: String,
        type: ItemAttributeType = BuiltInType.STRING,
        isNullable: Boolean = false,
        isMultiple: Boolean = false,
        exampleDataCategory: ExampleDataCategory? = null,
        isPrimaryKey: Boolean = false,
        isReference: Boolean = false,
    ): ItemAttribute = ItemAttribute(
        attributeName = name,
        isNullable = isNullable,
        isMultiple = isMultiple,
        type = type,
        exampleDataCategory = exampleDataCategory,
        isPrimaryKey = isPrimaryKey,
        isReference = isReference,
    )

    private fun schemaData(
        items: List<Item> = emptyList(),
        enums: List<EnumType> = emptyList(),
        uiItems: List<UiItem> = emptyList(),
        uiEntities: List<UiEntity> = emptyList(),
    ): SchemaData = SchemaData(items = items, enums = enums, uiItems = uiItems, uiEntities = uiEntities)

    private fun uiEntity(
        rootItem: Item,
        searchResultAttributeNames: List<String> = emptyList(),
        itemConfigurations: List<UiEntityEditorItemConfiguration> = emptyList(),
    ): UiEntity = UiEntity(
        uiEntityName = rootItem.itemName,
        rootItem = rootItem,
        editorView = UiEntityEditorView(itemConfiguration = itemConfigurations),
        searchResultView = UiEntitySearchResultView(attributeNames = searchResultAttributeNames),
    )

    private fun rootItemConfiguration(
        attributeNames: List<String> = emptyList(),
        tabAttributeNames: List<String> = emptyList(),
    ): UiEntityEditorRootItemConfiguration = UiEntityEditorRootItemConfiguration(
        noTab = listOf(column(attributeNames)),
        tabs = if (tabAttributeNames.isEmpty()) {
            emptyList()
        } else {
            listOf(UiEntityEditorTab(tabTranslationKey = "tab.common", columns = listOf(column(tabAttributeNames))))
        },
    )

    private fun nestedItemConfiguration(
        itemId: ItemId,
        attributeNames: List<String> = emptyList(),
    ): UiEntityEditorEntityNestedItemConfiguration = UiEntityEditorEntityNestedItemConfiguration(
        itemId = itemId,
        noTab = listOf(column(attributeNames)),
    )

    private fun column(attributeNames: List<String>): UiEntityEditorColumn = UiEntityEditorColumn(
        blocks = attributeNames.map { UiItemAttributeBlock(attributeName = it) as UiBlock },
    )

    private fun validationFailureOf(schemaData: SchemaData): SchemaDataValidationException =
        assertThrows<SchemaDataValidationException> { SchemaDataValidator().validate(schemaData) }

    private fun assertProblemContains(exception: SchemaDataValidationException, expectedPart: String) {
        assertTrue(
            exception.problem.contains(expectedPart),
            "Expected the problem to contain '$expectedPart', but it was '${exception.problem}'",
        )
    }
}
