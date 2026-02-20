package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.ItemId

data class UiItemModel(
    val itemId: ItemId,
    val itemName: NameCase,
    val attributes: List<UiItemAttributeModel>,
) {
    val itemNameDashCase: String = itemName.lowercaseDashCase
    val itemNameLowercase: String = itemName.decapitalizedCamelCase
}
