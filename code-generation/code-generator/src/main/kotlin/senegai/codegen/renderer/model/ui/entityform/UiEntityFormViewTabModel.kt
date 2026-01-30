package senegai.codegen.renderer.model.ui.entityform

import senegai.codegen.renderer.model.ui.UiEntityModel

data class UiEntityFormViewTabModel(
 val entity: UiEntityModel,
 val tabName: String,
 val noColumnBlocks: List<UiEntityFormBlockModel>,
 val columns: List<UiEntityFormViewColumnModel>,
)
