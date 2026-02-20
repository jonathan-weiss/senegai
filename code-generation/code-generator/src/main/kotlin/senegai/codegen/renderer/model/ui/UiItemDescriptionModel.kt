package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.ItemId

data class UiItemDescriptionModel(
    val itemId: ItemId,
    val itemName: NameCase,
)
