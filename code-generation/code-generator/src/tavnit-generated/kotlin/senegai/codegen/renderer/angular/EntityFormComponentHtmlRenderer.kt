/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityFormComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-form.component.html`
 * - path: `opus-magnum/opus-magnum-form/opus-magnum-form/opus-magnum-form.component.html`
 */
object EntityFormComponentHtmlRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |<div class="edit-form-container">
          |    <mat-card>
          |        <mat-card-header>
          |            <mat-card-title>{{ ${model.entityRootItem.itemName.camelCase} ? 'Edit ${model.entityName.pascalCase}' : 'New ${model.entityName.pascalCase}' }}</mat-card-title>
          |        </mat-card-header>
          |
          |        <mat-card-content>
          |            <form [formGroup]="${model.entityRootItem.itemName.camelCase}Form" (ngSubmit)="onSubmit()">
          |                <app-${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-form-part [${model.entityRootItem.itemName.camelCase}Form]="${model.entityRootItem.itemName.camelCase}Form" />
          |
          |                <div class="form-actions">
          |                    <button mat-button type="button" (click)="onCancel()">
          |                        Cancel
          |                    </button>
          |                    <button mat-raised-button color="primary" type="submit" [disabled]="!${model.entityRootItem.itemName.camelCase}Form.valid">
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
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-form/${model.entityName.kebabCase}-form/${model.entityName.kebabCase}-form.component.html"
    }
}