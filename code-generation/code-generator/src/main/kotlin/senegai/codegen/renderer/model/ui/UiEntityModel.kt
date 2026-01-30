package senegai.codegen.renderer.model.ui

data class UiEntityModel(
    val rootItemModel: UiItemModel
) {
    val entityItemModels: List<UiItemModel> = listOf(rootItemModel) // TODO implement recursive
}
