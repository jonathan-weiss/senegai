package senegai.codegen.renderer.model.ui.entityform

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiItemModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormNamedSectionSplitBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormTextBlockModel

data class UiEntityFormViewItemModel(
    val entity: UiEntityModel,
    val item: UiItemModel,
    val noTab: List<UiEntityFormViewColumnModel>,
    val tabs: List<UiEntityFormViewTabModel>
) : BlockHolder {

    /**
     * True as soon as the form part renders a label that has to be translated
     * with transloco (tab labels, section splitters and text blocks).
     */
    fun containsTranslatedLabels(): Boolean {
        return tabs.isNotEmpty() || containsNamedSectionSplitBlocks() || containsTextBlocks()
    }

    fun containsTextBlocks(): Boolean {
        return allBlocks().filterIsInstance<UiEntityFormTextBlockModel>().isNotEmpty()
    }

    fun containsNamedSectionSplitBlocks(): Boolean {
        return allBlocks().filterIsInstance<UiEntityFormNamedSectionSplitBlockModel>().isNotEmpty()
    }

    override fun allBlocks(): List<UiEntityFormBlockModel> {
        return noTab.flatMap { it.allBlocks() } + tabs.flatMap { it.allBlocks() }
    }
}
