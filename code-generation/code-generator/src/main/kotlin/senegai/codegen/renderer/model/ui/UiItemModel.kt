package senegai.codegen.renderer.model.ui

data class UiItemModel(
    val itemName: String,
    val attributes: List<UiItemAttributeModel>,
) {
    val itemNameLowercase: String = itemName.lowercase()
    val itemNameForAngularFile: String = itemName.lowercase()  // TODO uses dashes case
}
