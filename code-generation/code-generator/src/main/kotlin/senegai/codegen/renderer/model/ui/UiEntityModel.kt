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
    /**
     * The attributes shown as the columns of the search result table, in the order
     * declared in the `searchResult` section of the UiEntity. They are always attributes
     * of the [entityRootItem].
     */
    val searchResultAttributes: List<UiAttributeModel>,
) {
    /**
     * The attribute of the [entityRootItem] that identifies it, e.g. for the route
     * parameter of the edit route. It is always present, because a UiEntity can only be
     * built on a root item with a primary key.
     */
    val idAttribute: BuiltInTypeUiAttributeModel = entityRootItem.primaryKeyAttribute

    /** See [UiItemModel.displayAttributes]. */
    val displayAttributes: List<UiAttributeModel> = entityRootItem.displayAttributes
}
