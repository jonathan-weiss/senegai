package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemsModel

interface ItemsRenderer {
    fun renderTemplate(model: ItemsModel): String
    fun filePath(model: ItemsModel): String
}
