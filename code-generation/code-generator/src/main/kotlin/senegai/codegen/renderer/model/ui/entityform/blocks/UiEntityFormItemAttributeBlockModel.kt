package senegai.codegen.renderer.model.ui.entityform.blocks

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiItemModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiFormAttributeType

data class UiEntityFormItemAttributeBlockModel(
    val entity: UiEntityModel,
    val item: UiItemModel,
    val attributeName: String,
    val type: UiFormAttributeType,
): UiEntityFormBlockModel
