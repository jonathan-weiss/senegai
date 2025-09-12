package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

interface ItemRenderer {
    fun renderTemplate(model: ItemModel): String
    fun filePath(model: ItemModel): String
}
