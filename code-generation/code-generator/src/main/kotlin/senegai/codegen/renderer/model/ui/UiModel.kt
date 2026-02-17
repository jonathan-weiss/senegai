package senegai.codegen.renderer.model.ui

/**
 * Represents all Entities e.g. for the left navigation.
 */
data class UiModel(
    val uiEntitiesViews: List<UiEntityViewsModel>,
) {
    val uiEntities: List<UiEntityModel> = uiEntitiesViews.map { it.uiEntity }
    val uiItems: List<UiItemModel> = uiEntitiesViews.flatMap { it.uiEntity.entityItemModels }.distinct()
}
