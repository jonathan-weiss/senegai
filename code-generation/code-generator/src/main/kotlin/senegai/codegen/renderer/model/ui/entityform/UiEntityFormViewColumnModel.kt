package senegai.codegen.renderer.model.ui.entityform

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormBlockModel

data class UiEntityFormViewColumnModel(
 private val entity: UiEntityModel,
 val blocks: List<UiEntityFormBlockModel>
)
