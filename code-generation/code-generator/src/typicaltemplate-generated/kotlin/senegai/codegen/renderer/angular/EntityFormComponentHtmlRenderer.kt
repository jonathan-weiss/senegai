/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityFormComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object EntityFormComponentHtmlRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |<div class="edit-form-container">
          |    <mat-card>
          |        <mat-card-header>
          |            <mat-card-title>{{ ${model.entityNameLowercase} ? 'Edit ${model.entityName}' : 'New ${model.entityName}' }}</mat-card-title>
          |        </mat-card-header>
          |
          |        <mat-card-content>
          |            <form [formGroup]="${model.entityNameLowercase}Form" (ngSubmit)="onSubmit()">
          |                <app-${model.entityNameDashCase}-form-part [${model.entityNameLowercase}Form]="${model.entityNameLowercase}Form" />
          |
          |                <div class="form-actions">
          |                    <button mat-button type="button" (click)="onCancel()">
          |                        Cancel
          |                    </button>
          |                    <button mat-raised-button color="primary" type="submit" [disabled]="!${model.entityNameLowercase}Form.valid">
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

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameDashCase}/${model.entityNameDashCase}-form/${model.entityNameDashCase}-form/${model.entityNameDashCase}-form.component.html"
    }
}