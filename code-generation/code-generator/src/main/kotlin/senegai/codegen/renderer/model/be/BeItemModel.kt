package senegai.codegen.renderer.model.be

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.ItemId

data class BeItemModel(
    val entityName: NameCase,
    val itemDescription: BeItemDescriptionModel,
    val attributes: List<BeAttributeModel>,
) {
    val itemId: ItemId = itemDescription.itemId
    val itemName: NameCase = itemDescription.itemName

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

    val builtInTypeAndEnumAttributes: List<BeAttributeModel> = attributes
        .filter { it.isBuiltIn || it.isEnum }

    val directlyNestedItems: List<BeItemDescriptionModel> = attributes
        .filterIsInstance<ItemBeIAttributeModel>()
        .map { it.referencedItem }
        .distinct()

    val containsTextAttributes: Boolean = attributesOfType(BuiltInType.STRING, isList = false).any()
    val containsBooleanAttributes: Boolean = attributesOfType(BuiltInType.BOOLEAN, isList = false).any()
    val containsNumberAttributes: Boolean = attributesOfType(BuiltInType.NUMBER, isList = false).any()

    val containsTextListAttributes: Boolean = attributesOfType(BuiltInType.STRING, isList = true).any()
    val containsBooleanListAttributes: Boolean = attributesOfType(BuiltInType.BOOLEAN, isList = true).any()
    val containsNumberListAttributes: Boolean = attributesOfType(BuiltInType.NUMBER, isList = true).any()

    private fun attributesOfType(filterBuiltInType: BuiltInType, isList: Boolean): List<BeAttributeModel> {
        return attributes
            .filterIsInstance<BuiltInTypeBeAttributeModel>()
            .filter { it.builtInType == filterBuiltInType && it.isList == isList }
    }
}
