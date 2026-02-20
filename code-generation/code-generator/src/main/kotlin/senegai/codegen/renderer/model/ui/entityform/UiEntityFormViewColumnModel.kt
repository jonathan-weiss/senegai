package senegai.codegen.renderer.model.ui.entityform

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormNamedSectionSplitBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormTextBlockModel

data class UiEntityFormViewColumnModel(
    private val entity: UiEntityModel,
    val blocks: List<UiEntityFormBlockModel>
): BlockHolder {
    override fun allBlocks(): List<UiEntityFormBlockModel> {
        return blocks
    }
}
