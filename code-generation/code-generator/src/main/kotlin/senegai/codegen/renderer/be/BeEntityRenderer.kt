package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

interface BeEntityRenderer {
    fun renderTemplate(model: BeEntityModel): String
    fun filePath(model: BeEntityModel): String
}
