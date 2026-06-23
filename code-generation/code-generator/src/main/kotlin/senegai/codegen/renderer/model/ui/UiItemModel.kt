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
        .filter { it.type is BuiltInTypeUiItemAttributeTypeModel || it.isList }

    val attributesWithItem: List<AttributeAndItemDescriptionModel> = attributes
        .filter { it.isItem }
        .map { AttributeAndItemDescriptionModel(
            attribute = it,
            type = it.type as ItemUiItemAttributeTypeModel,
        ) }

    val attributesWithBuiltInType: List<AttributeAndBuiltInTypeDescriptionModel> = attributes
        .filter { it.isBuiltIn }
        .map { AttributeAndBuiltInTypeDescriptionModel(
            attribute = it,
            type = it.type as BuiltInTypeUiItemAttributeTypeModel,
        ) }

    val attributesWithLists: List<UiItemAttributeModel> = attributes
        .filter { it.isList }

    val directlyNestedItems: List<UiItemDescriptionModel> = attributes
        .map { it.type }
        .filterIsInstance<ItemUiItemAttributeTypeModel>()
        .map { it.item }
        .distinct()

    val containsTextAttributes: Boolean = attributesOfType(BuiltInType.STRING, isList = false).any()
    val containsBooleanAttributes: Boolean = attributesOfType(BuiltInType.BOOLEAN, isList = false).any()
    val containsNumberAttributes: Boolean = attributesOfType(BuiltInType.NUMBER, isList = false).any()

    val containsTextListAttributes: Boolean = attributesOfType(BuiltInType.STRING, isList = true).any()
    val containsBooleanListAttributes: Boolean = attributesOfType(BuiltInType.BOOLEAN, isList = true).any()
    val containsNumberListAttributes: Boolean = attributesOfType(BuiltInType.NUMBER, isList = true).any()

    private fun attributesOfType(filterBuiltInType: BuiltInType, isList: Boolean): List<UiItemAttributeModel> {
        return attributes.filter { it.type is BuiltInTypeUiItemAttributeTypeModel && it.type.builtInType == filterBuiltInType && it.isList == isList }
    }
}
