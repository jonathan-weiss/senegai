package senegai.codegen.renderer.model.ui.entityform

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiItemModel

data class UiEntityFormItemAttributeBlockModel(
    override val entity: UiEntityModel,
    override val item: UiItemModel,
    val attributeName: String,
): UiEntityFormItemAwareBlockModel
