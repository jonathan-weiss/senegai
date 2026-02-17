/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template ItemFormPartComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object ItemFormPartComponentHtmlRenderer : UiItemRenderer {

    override fun renderTemplate(model: UiItemModel): String {
        return """
          |<div [formGroup]="${model.itemNameLowercase}Form">
          |
          |<div>
          |    <div class="form-row">
          |        <app-field-wrapper label="ID">
          |            <mat-form-field appearance="fill">
          |                <input matInput [formControl]="idControl" readonly>
          |                <mat-hint>ID cannot be modified</mat-hint>
          |            </mat-form-field>
          |        </app-field-wrapper>
          |    </div>
          |</div>
          |
          |<mat-tab-group dynamicHeight>
          |    <mat-tab label="Main">
          |        <div class="column-layout">
          |            <div class="column">
          |                                ${ model.attributes.joinToString("") { attribute ->  """
              |                <div class="form-row">
              |                    <app-field-wrapper label="${attribute.attributeName}">
              |                        <app-text-input [textFormControl]="${attribute.attributeName}Control" label="${attribute.attributeName}" placeholder="Enter ${attribute.attributeName}" [validatorTranslations]="${attribute.attributeName}ValidatorNames" />
              |                    </app-field-wrapper>
              |                </div>
          """ } }            </div>
          |            <div class="column">            </div>
          |        </div>
          |    </mat-tab></mat-tab-group>
          |</div>
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part.component.html"
    }
}