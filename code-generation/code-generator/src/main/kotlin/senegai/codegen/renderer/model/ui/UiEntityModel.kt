package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase

/**
 * One editor shell of the frontend: a directory that bundles the Angular components
 * (board, search, result, form, ...) around one root item with a primary key.
 * It has no counterpart in the backend.
 */
data class UiEntityModel(
    val entityName: NameCase,
    val entityRootItem: UiItemModel,
    val entityItemModels: List<UiItemModel>,
    val entityEnumTypes: List<UiEnumModel>,
) {
    /**
     * The attribute of the [entityRootItem] that identifies it, e.g. for the route
     * parameter of the edit route. It is always a UUID, and it is always present,
     * because a UiEntity can only be built on a root item with a primary key.
     */
    val idAttribute: UiAttributeModel = entityRootItem.primaryKeyAttribute

    val searchResultAttributes: List<UiAttributeModel> = entityRootItem.searchResultAttributes

    /** See [UiItemModel.displayAttributes]. */
    val displayAttributes: List<UiAttributeModel> = entityRootItem.displayAttributes
}
