package senegai.codegen.renderer.model.ui.entityform

import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormBlockModel

interface BlockHolder {

    fun allBlocks(): List<UiEntityFormBlockModel>

}
