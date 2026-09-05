package senegai.codegen.renderer.db

import senegai.codegen.renderer.model.db.DbTableModel

interface DbItemRenderer {
    fun renderTemplate(model: DbTableModel): String
    fun filePath(model: DbTableModel): String
}
