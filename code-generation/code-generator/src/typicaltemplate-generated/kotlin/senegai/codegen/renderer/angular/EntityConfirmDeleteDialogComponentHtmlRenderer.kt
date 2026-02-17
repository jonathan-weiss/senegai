/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityConfirmDeleteDialogComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object EntityConfirmDeleteDialogComponentHtmlRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |<h2 mat-dialog-title>Delete ${model.entityName}</h2>
          |<mat-dialog-content><ng-container>Are you sure you want to delete</ng-container> <b>{{ data.firstname }} {{ data.lastname }}</b>?
          |</mat-dialog-content>
          |<mat-dialog-actions align="end">
          |    <button mat-button (click)="onCancel()">Cancel</button>
          |    <button mat-raised-button color="warn" (click)="onConfirm()">OK</button>
          |</mat-dialog-actions>
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameDashCase}/${model.entityNameDashCase}-confirm-delete-dialog/${model.entityNameDashCase}-confirm-delete-dialog.component.html"
    }
}