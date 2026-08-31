package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.model.schema.BuiltInType
import senegai.model.schema.ItemId

data class UiItemModel(
    val itemDescription: UiItemDescriptionModel,
    val attributes: List<UiAttributeModel>,
) {
    val itemId: ItemId = itemDescription.itemId
    val itemName: NameCase = itemDescription.itemName

    val attributesWithAngularFormInitialValues: List<UiAttributeModel> = attributes
        .filter { it is BuiltInTypeUiAttributeModel || it.isList || it.isEnum }

    val attributesWithCustomValidation: List<UiAttributeModel> = attributes
        .filter { it.hasCustomValidation }

    val usedEnums: List<UiEnumModel> = attributes
        .filterIsInstance<EnumUiAttributeModel>()
        .map { it.enum }
        .distinct()

    val attributesWithItemType: List<ItemUiIAttributeModel> = attributes
        .filterIsInstance<ItemUiIAttributeModel>()

    val attributesWithEnumType: List<EnumUiAttributeModel> = attributes
        .filterIsInstance<EnumUiAttributeModel>()

    val builtInTypeAndEnumAttributes: List<UiAttributeModel> = attributes
        .filter { it.isBuiltIn || it.isEnum }

    val directlyNestedItems: List<UiItemDescriptionModel> = attributes
        .filterIsInstance<ItemUiIAttributeModel>()
        .map { it.referencedItem }
        .distinct()

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

    private fun attributesOfType(filterBuiltInType: BuiltInType, isList: Boolean): List<UiAttributeModel> =
        attributesOfTypes(setOf(filterBuiltInType), isList)

    /** All built-in attributes of one of the [filterBuiltInTypes], of any cardinality if [isList] is `null`. */
    private fun attributesOfTypes(filterBuiltInTypes: Set<BuiltInType>, isList: Boolean? = null): List<UiAttributeModel> {
        return attributes
            .filterIsInstance<BuiltInTypeUiAttributeModel>()
            .filter { it.builtInType in filterBuiltInTypes && (isList == null || it.isList == isList) }
    }

    private companion object {
        /** Built-in types that are rendered as a text input on the client. */
        private val TEXT_LIKE_BUILT_IN_TYPES = setOf(BuiltInType.STRING, BuiltInType.UUID)
    }
}
