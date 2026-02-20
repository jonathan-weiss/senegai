package senegai.codegen.renderer.model.ui.entityform.blocks

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiItemAttributeModel
import senegai.codegen.renderer.model.ui.UiItemModel

data class UiEntityFormItemAttributeBlockModel(
    val entity: UiEntityModel,
    val item: UiItemModel,
    val attribute: UiItemAttributeModel,
) : UiEntityFormBlockModel
