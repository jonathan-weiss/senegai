package senegai.codegen.renderer.model.ui.entityform

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormBlockModel

data class UiEntityFormViewTabModel(
    val entity: UiEntityModel,
    val tabName: String,
    val columns: List<UiEntityFormViewColumnModel>,
) : BlockHolder {
    override fun allBlocks(): List<UiEntityFormBlockModel> {
        return columns.flatMap { it.allBlocks() }
    }
}
