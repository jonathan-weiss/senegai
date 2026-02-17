package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

interface UiItemRenderer {
    fun renderTemplate(model: UiItemModel): String
    fun filePath(model: UiItemModel): String
}
