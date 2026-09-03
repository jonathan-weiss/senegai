package senegai.codegen.renderer.model.be

import senegai.codegen.renderer.model.NameCase
import senegai.model.schema.BuiltInType
import senegai.model.schema.ItemId

data class BeItemModel(
    val itemDescription: BeItemDescriptionModel,
    val attributes: List<BeAttributeModel>,
    /**
     * The attribute that identifies this item (its primary key), or `null` if this item
     * has none. It is always a UUID.
     */
    val idAttribute: BeAttributeModel?,
) {
    val itemId: ItemId = itemDescription.itemId
    val itemName: NameCase = itemDescription.itemName

    /** Whether this item is identified by a primary key and can therefore be addressed, searched and referenced. */
    val hasPrimaryKey: Boolean = idAttribute != null

    /**
     * The primary key, for the templates that are only rendered for an item that has one
     * (controller, service, repository, search, by-ids, example data initializer).
     */
    val primaryKeyAttribute: BeAttributeModel
        get() = requireNotNull(idAttribute) {
            "The item '${itemName.pascalCase}' declares no primary key."
        }

    val usedEnums: List<BeEnumModel> = attributes
        .filterIsInstance<EnumBeAttributeModel>()
        .map { it.enum }
        .distinct()

    val attributesWithItemType: List<ItemBeIAttributeModel> = attributes
        .filterIsInstance<ItemBeIAttributeModel>()

    val attributesWithEnumType: List<EnumBeAttributeModel> = attributes
        .filterIsInstance<EnumBeAttributeModel>()

    val builtInAttributes: List<BuiltInTypeBeAttributeModel> = attributes
        .filterIsInstance<BuiltInTypeBeAttributeModel>()

    /**
     * All attributes referencing another item. They are a subset of [builtInAttributes],
     * as a reference is transported as the UUID of the referenced item.
     */
    val attributesWithItemReference: List<ItemReferenceBeAttributeModel> = attributes
        .filterIsInstance<ItemReferenceBeAttributeModel>()

    /** All items referenced by an attribute of this item, e.g. to inject one example data fetcher per item. */
    val referencedItems: List<BeReferencedItemModel> = attributesWithItemReference
        .map { it.referencedItem }
        .distinct()

    val builtInTypeAndEnumAttributes: List<BeAttributeModel> = attributes
        .filter { it.isBuiltIn || it.isEnum }

    val directlyNestedItems: List<BeItemDescriptionModel> = attributes
        .filterIsInstance<ItemBeIAttributeModel>()
        .map { it.referencedItem }
        .distinct()

    /**
     * Item references are left out: their example data is not generated but fetched from the
     * already existing instances of the referenced item.
     */
    val exampleDataGeneratorConfigs: List<BeExampleDataGeneratorConfig> = attributes
        .filterIsInstance<BuiltInTypeBeAttributeModel>()
        .filter { !it.isItemReference }
        .map { it.exampleDataGeneratorConfig }
        .distinctBy { it.fullQualifiedName }

    /**
     * UUIDs are edited with the very same text input as strings, therefore they count
     * as text attributes for everything that is about rendering an input field.
     */
    val containsTextAttributes: Boolean = attributesOfTypes(TEXT_LIKE_BUILT_IN_TYPES, isList = false).any()
    val containsBooleanAttributes: Boolean = attributesOfType(BuiltInType.BOOLEAN, isList = false).any()
    val containsNumberAttributes: Boolean = attributesOfType(BuiltInType.NUMBER, isList = false).any()

    val containsTextListAttributes: Boolean = attributesOfTypes(TEXT_LIKE_BUILT_IN_TYPES, isList = true).any()
    val containsBooleanListAttributes: Boolean = attributesOfType(BuiltInType.BOOLEAN, isList = true).any()
    val containsNumberListAttributes: Boolean = attributesOfType(BuiltInType.NUMBER, isList = true).any()

    /** Whether any attribute is a [BuiltInType.UUID], e.g. to render the import of the UUID type. */
    val containsUuidAttributes: Boolean = attributesOfTypes(setOf(BuiltInType.UUID)).any()

    private fun attributesOfType(filterBuiltInType: BuiltInType, isList: Boolean): List<BeAttributeModel> =
        attributesOfTypes(setOf(filterBuiltInType), isList)

    /** All built-in attributes of one of the [filterBuiltInTypes], of any cardinality if [isList] is `null`. */
    private fun attributesOfTypes(filterBuiltInTypes: Set<BuiltInType>, isList: Boolean? = null): List<BeAttributeModel> {
        return attributes
            .filterIsInstance<BuiltInTypeBeAttributeModel>()
            .filter { it.builtInType in filterBuiltInTypes && (isList == null || it.isList == isList) }
    }

    private companion object {
        /** Built-in types that are rendered as a text input on the client. */
        private val TEXT_LIKE_BUILT_IN_TYPES = setOf(BuiltInType.STRING, BuiltInType.UUID)
    }
}
