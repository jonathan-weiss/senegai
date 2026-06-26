package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

interface BeEntitiesRenderer {
    fun renderTemplate(models: List<BeEntityModel>): String
    fun filePath(models: List<BeEntityModel>): String
}
