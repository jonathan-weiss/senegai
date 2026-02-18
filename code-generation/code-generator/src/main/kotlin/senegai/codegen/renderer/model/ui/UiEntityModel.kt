package senegai.codegen.renderer.model.ui

import org.codeblessing.templatetools.CaseUtil

data class UiEntityModel(
    val entityRootItem: UiItemModel
) {
    val entityItemModels: List<UiItemModel> = searchRecursiveInNestedUiItemModel(entityRootItem)

    val entityName: String = entityRootItem.itemName
    val entityNameLowercase: String = entityRootItem.itemName.lowercase()
    val entityNameDashCase: String = CaseUtil.camelToDashCase(entityRootItem.itemName.lowercase())

    val searchResultAttributes: List<UiItemAttributeModel> = entityRootItem.attributes
    val searchCriteriaAttributes: List<UiItemAttributeModel> = entityRootItem.attributes
    val summaryAttributes: List<UiItemAttributeModel> = entityRootItem.attributes

    private fun searchRecursiveInNestedUiItemModel(itemModel: UiItemModel): List<UiItemModel> {
        // TODO implement recursive
        return listOf(itemModel)
    }
}
