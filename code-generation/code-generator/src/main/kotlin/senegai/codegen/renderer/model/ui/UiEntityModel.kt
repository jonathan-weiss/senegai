package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase

data class UiEntityModel(
    val entityName: NameCase,
    val entityRootItem: UiItemModel,
    val entityItemModels: List<UiItemModel>,
    val entityEnumTypes: List<UiEnumModel>,
) {
    val searchResultAttributes: List<UiAttributeModel> = entityRootItem.attributes
    val idAttribute: UiAttributeModel = entityRootItem.attributes.first() // TODO make configurable
}
