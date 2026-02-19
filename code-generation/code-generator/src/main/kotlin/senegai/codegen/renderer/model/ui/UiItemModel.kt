package senegai.codegen.renderer.model.ui

import senegai.codegen.schema.ItemId

data class UiItemModel(
    val itemId: ItemId,
    val itemName: String,
    val attributes: List<UiItemAttributeModel>,
) {
    val itemNameLowercase: String = itemName.lowercase()
    val itemNameForAngularFile: String = itemName.lowercase()  // TODO uses dashes case
}
