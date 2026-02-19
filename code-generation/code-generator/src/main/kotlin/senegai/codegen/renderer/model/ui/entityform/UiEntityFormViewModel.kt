package senegai.codegen.renderer.model.ui.entityform

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormBlockModel

data class UiEntityFormViewModel(
    val entity: UiEntityModel,
    val entityItems: List<UiEntityFormViewItemModel>,
) {
    @Deprecated("use entityItems instead.", ReplaceWith("entityItems"))
    val entityItemModels = entity.entityItemModels
}
