package senegai.codegen.renderer.db

import senegai.codegen.renderer.model.db.DbEnumModel

interface DbEnumRenderer {
    fun renderTemplate(model: DbEnumModel): String
    fun filePath(model: DbEnumModel): String
}
