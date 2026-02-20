package senegai.codegen.renderer.model.ui.entityform

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormNamedSectionSplitBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormTextBlockModel

data class UiEntityFormViewModel(
    val entity: UiEntityModel,
    val entityItems: List<UiEntityFormViewItemModel>,
): BlockHolder {
    @Deprecated("use entityItems instead.", ReplaceWith("entityItems"))
    val entityItemModels = entity.entityItemModels

    override fun allBlocks(): List<UiEntityFormBlockModel> {
        return entityItems.flatMap { it.allBlocks() }
    }
}
