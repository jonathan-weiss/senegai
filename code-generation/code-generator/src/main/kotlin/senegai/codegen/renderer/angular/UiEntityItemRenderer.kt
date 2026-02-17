package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiItemModel

interface UiEntityItemRenderer {
    fun renderTemplate(entity: UiEntityModel, model: UiItemModel): String
    fun filePath(entity: UiEntityModel, model: UiItemModel): String
}
