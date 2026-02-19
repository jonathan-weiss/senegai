package senegai.codegen.renderer.model.ui.entityform

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiItemModel

data class UiEntityFormViewItemModel(
    val entity: UiEntityModel,
    val item: UiItemModel,
    val noTab: List<UiEntityFormViewColumnModel>,
    val tabs: List<UiEntityFormViewTabModel>
)
