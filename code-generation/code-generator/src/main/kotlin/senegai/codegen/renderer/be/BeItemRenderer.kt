package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

interface BeItemRenderer {
    fun renderTemplate(model: BeItemModel): String
    fun filePath(model: BeItemModel): String
}
