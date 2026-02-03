package senegai.codegen.renderer.model.ui.entityform

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormBlockModel

class UiEntityFormViewModel(
    val entity: UiEntityModel,
    val essentialBlocks: List<UiEntityFormBlockModel>,
    val tabs: List<UiEntityFormViewTabModel>
)
