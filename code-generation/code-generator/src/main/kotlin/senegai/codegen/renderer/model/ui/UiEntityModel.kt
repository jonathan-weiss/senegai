package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase

data class UiEntityModel(
    val entityName: NameCase,
    val entityRootItem: UiItemModel,
    /** The attribute of the [entityRootItem] that identifies the entity. It is always a UUID. */
    val idAttribute: UiAttributeModel,
    val entityItemModels: List<UiItemModel>,
    val entityEnumTypes: List<UiEnumModel>,
) {
    val searchResultAttributes: List<UiAttributeModel> = entityRootItem.attributes
}
