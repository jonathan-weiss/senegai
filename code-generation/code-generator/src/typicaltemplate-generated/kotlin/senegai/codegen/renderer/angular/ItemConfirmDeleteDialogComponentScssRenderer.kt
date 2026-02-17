/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template ItemConfirmDeleteDialogComponentScssRenderer filled up
 * with the content of the passed models.
 */
object ItemConfirmDeleteDialogComponentScssRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameLowercase}/${model.entityNameLowercase}-confirm-delete-dialog/${model.entityNameLowercase}-confirm-delete-dialog.component.scss"
    }
}