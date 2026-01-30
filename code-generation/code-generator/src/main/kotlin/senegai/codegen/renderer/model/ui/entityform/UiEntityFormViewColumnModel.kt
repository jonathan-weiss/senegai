package senegai.codegen.renderer.model.ui.entityform

import senegai.codegen.renderer.model.ui.UiEntityModel

data class UiEntityFormViewColumnModel(
 private val entity: UiEntityModel,
 val blocks: List<UiEntityFormBlockModel>
)
