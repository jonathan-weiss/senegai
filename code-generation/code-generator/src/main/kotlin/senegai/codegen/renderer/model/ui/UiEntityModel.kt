package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.EnumType

data class UiEntityModel(
    val entityRootItem: UiItemModel,
    val entityItemModels: List<UiItemModel>,
    val entityEnumTypes: List<EnumType>,
) {
    val entityName: NameCase = entityRootItem.itemName

    val searchResultAttributes: List<UiItemAttributeModel> = entityRootItem.attributes
    val searchCriteriaAttributes: List<UiItemAttributeModel> = entityRootItem.attributes
    val summaryAttributes: List<UiItemAttributeModel> = entityRootItem.attributes
    val allAttributes: List<UiItemAttributeModel> = entityRootItem.attributes
}
