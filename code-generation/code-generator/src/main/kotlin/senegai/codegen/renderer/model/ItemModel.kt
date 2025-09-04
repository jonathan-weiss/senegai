package senegai.codegen.renderer.model

data class ItemModel(
    val itemName: String,
    val attributes: List<ItemAttribute>,
) {
    val itemNameLowercase: String = itemName.lowercase()
    val itemNameForAngularFile: String = itemName.lowercase()  // TODO uses dashes case
}
