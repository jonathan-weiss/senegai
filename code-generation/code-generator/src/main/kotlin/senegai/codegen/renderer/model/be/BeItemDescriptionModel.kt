package senegai.codegen.renderer.model.be

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.ItemId

data class BeItemDescriptionModel(
    val itemId: ItemId,
    val itemName: NameCase,
)
