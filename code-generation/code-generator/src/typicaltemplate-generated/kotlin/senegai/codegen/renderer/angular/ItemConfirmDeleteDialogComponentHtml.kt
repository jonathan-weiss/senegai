/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemConfirmDeleteDialogComponentHtml filled up
 * with the content of the passed models.
 */
object ItemConfirmDeleteDialogComponentHtml {

    fun renderTemplate(model: ItemModel): String {
        return """
          |<h2 mat-dialog-title>Delete ${model.itemName}</h2>
          |<mat-dialog-content>Are you sure you want to delete <b>{{ data.firstname }} {{ data.lastname }}</b>?
          |</mat-dialog-content>
          |<mat-dialog-actions align="end">
          |    <button mat-button (click)="onCancel()">Cancel</button>
          |    <button mat-raised-button color="warn" (click)="onConfirm()">OK</button>
          |</mat-dialog-actions>
          |
        """.trimMargin(marginPrefix = "|")
    }
}