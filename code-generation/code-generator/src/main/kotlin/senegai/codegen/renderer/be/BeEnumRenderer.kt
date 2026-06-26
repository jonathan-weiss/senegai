package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEnumModel

interface BeEnumRenderer {
    fun renderTemplate(model: BeEnumModel): String
    fun filePath(model: BeEnumModel): String
}
