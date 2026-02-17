package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

interface UiEntitiesRenderer {
    fun renderTemplate(models: List<UiEntityModel>): String
    fun filePath(models: List<UiEntityModel>): String
}
