package senegai.codegen.renderer.model.ui.entityform.blocks

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiItemModel

sealed interface UiEntityFormItemAwareBlockModel: UiEntityFormBlockModel {
    val entity: UiEntityModel
    val item: UiItemModel
}
