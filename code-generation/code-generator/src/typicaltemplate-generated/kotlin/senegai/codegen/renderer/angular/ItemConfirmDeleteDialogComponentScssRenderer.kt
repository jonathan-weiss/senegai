/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemConfirmDeleteDialogComponentScssRenderer filled up
 * with the content of the passed models.
 */
object ItemConfirmDeleteDialogComponentScssRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-confirm-delete-dialog/${model.itemNameLowercase}-confirm-delete-dialog.component.scss"
    }
}