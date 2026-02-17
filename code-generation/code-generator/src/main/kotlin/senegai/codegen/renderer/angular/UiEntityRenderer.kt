package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

interface UiEntityRenderer {
    fun renderTemplate(model: UiEntityModel): String
    fun filePath(model: UiEntityModel): String
}
