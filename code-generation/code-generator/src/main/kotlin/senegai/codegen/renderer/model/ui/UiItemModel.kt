package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.ItemId

data class UiItemModel(
    val itemDescription: UiItemDescriptionModel,
    val attributes: List<UiItemAttributeModel>,
) {
    val itemId: ItemId = itemDescription.itemId
    val itemName: NameCase = itemDescription.itemName

    val attributesWithAngularFormInitialValues: List<UiItemAttributeModel> = attributes
        .filter { it is BuiltInTypeUiAttributeModel || it.isList || it.isEnum }

    val usedEnums: List<UiEnumModel> = attributes
        .filterIsInstance<EnumUiAttributeModel>()
        .map { it.enum }
        .distinct()

    val attributesWithItemType: List<ItemUiIAttributeModel> = attributes
        .filterIsInstance<ItemUiIAttributeModel>()

    val attributesWithEnumType: List<EnumUiAttributeModel> = attributes
        .filterIsInstance<EnumUiAttributeModel>()

    val builtInTypeAndEnumAttributes: List<UiItemAttributeModel> = attributes
        .filter { it.isBuiltIn || it.isEnum }

    val directlyNestedItems: List<UiItemDescriptionModel> = attributes
        .filterIsInstance<ItemUiIAttributeModel>()
        .map { it.referencedItem }
        .distinct()

    val containsTextAttributes: Boolean = attributesOfType(BuiltInType.STRING, isList = false).any()
    val containsBooleanAttributes: Boolean = attributesOfType(BuiltInType.BOOLEAN, isList = false).any()
    val containsNumberAttributes: Boolean = attributesOfType(BuiltInType.NUMBER, isList = false).any()

    val containsTextListAttributes: Boolean = attributesOfType(BuiltInType.STRING, isList = true).any()
    val containsBooleanListAttributes: Boolean = attributesOfType(BuiltInType.BOOLEAN, isList = true).any()
    val containsNumberListAttributes: Boolean = attributesOfType(BuiltInType.NUMBER, isList = true).any()

    private fun attributesOfType(filterBuiltInType: BuiltInType, isList: Boolean): List<UiItemAttributeModel> {
        return attributes
            .filterIsInstance<BuiltInTypeUiAttributeModel>()
            .filter { it.builtInType == filterBuiltInType && it.isList == isList }
    }
}
