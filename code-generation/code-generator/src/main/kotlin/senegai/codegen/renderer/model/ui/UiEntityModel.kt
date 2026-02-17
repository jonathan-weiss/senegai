package senegai.codegen.renderer.model.ui

data class UiEntityModel(
    val rootItemModel: UiItemModel
) {
    val entityItemModels: List<UiItemModel> = listOf(rootItemModel) // TODO implement recursive

    val entityName: String = rootItemModel.itemName
    val entityNameLowercase: String = rootItemModel.itemName.lowercase()
    val entityNameForAngularFile: String = rootItemModel.itemName.lowercase()  // TODO uses dashes case

    val attributes: List<UiItemAttributeModel> = rootItemModel.attributes
}
