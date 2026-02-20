package senegai.codegen.renderer.model.ui

import org.codeblessing.templatetools.CaseUtil
import senegai.codegen.renderer.model.NameCase

data class UiEntityModel(
    val entityRootItem: UiItemModel,
    val entityItemModels: List<UiItemModel>,
) {
    val entityName: NameCase = entityRootItem.itemName

    val searchResultAttributes: List<UiItemAttributeModel> = entityRootItem.attributes
    val searchCriteriaAttributes: List<UiItemAttributeModel> = entityRootItem.attributes
    val summaryAttributes: List<UiItemAttributeModel> = entityRootItem.attributes
    val allAttributes: List<UiItemAttributeModel> = entityRootItem.attributes
}
