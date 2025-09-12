/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemFormComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object ItemFormComponentHtmlRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |<div class="edit-form-container">
          |    <mat-card>
          |        <mat-card-header>
          |            <mat-card-title i18n>{{ ${model.itemNameLowercase} ? 'Edit ${model.itemName}' : 'New ${model.itemName}' }}</mat-card-title>
          |        </mat-card-header>
          |
          |        <mat-card-content>
          |            <form [formGroup]="${model.itemNameLowercase}Form" (ngSubmit)="onSubmit()">
          |                <app-${model.itemNameLowercase}-form-part [${model.itemNameLowercase}Form]="${model.itemNameLowercase}Form" />
          |
          |                <div class="form-actions">
          |                    <button mat-button type="button" (click)="onCancel()">
          |                        Cancel
          |                    </button>
          |                    <button mat-raised-button color="primary" type="submit" [disabled]="!${model.itemNameLowercase}Form.valid">
          |                        Save
          |                    </button>
          |                </div>
          |            </form>
          |        </mat-card-content>
          |    </mat-card>
          |</div>
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form.component.html"
    }
}