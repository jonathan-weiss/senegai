package senegai.codegen.renderer.model.be

import senegai.codegen.renderer.model.NameCase
import senegai.model.schema.ItemId

data class BeItemDescriptionModel(
    val itemId: ItemId,
    val itemName: NameCase,
)
