package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEnumModel

interface UiEnumRenderer {
    fun renderTemplate(model: UiEnumModel): String
    fun filePath(model: UiEnumModel): String
}
