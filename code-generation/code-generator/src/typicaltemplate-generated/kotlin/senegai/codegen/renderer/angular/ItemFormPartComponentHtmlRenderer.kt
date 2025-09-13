/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemFormPartComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object ItemFormPartComponentHtmlRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |<div [formGroup]="${model.itemNameLowercase}Form">
          |    <div class="form-row">
          |        <app-field-wrapper label="ID">
          |            <mat-form-field appearance="fill">
          |                <input matInput [formControl]="idControl" readonly>
          |                <mat-hint>ID cannot be modified</mat-hint>
          |            </mat-form-field>
          |        </app-field-wrapper>
          |    </div>
          |    ${ model.attributes.joinToString("") { attribute ->  """
              |    <div class="form-row">
              |        <app-field-wrapper label="${attribute.attributeName}">
              |            <mat-form-field appearance="fill">
              |                <input matInput [formControl]="${attribute.attributeName}Control" placeholder="Enter ${attribute.attributeName}">
              |                @if (hasError('${attribute.attributeName}', 'required')) {
              |                    <mat-error>
              |                        ${attribute.attributeName} is required
              |                    </mat-error>
              |                }
              |                @if (hasError('${attribute.attributeName}', 'minlength')) {
              |                    <mat-error>
              |                        ${attribute.attributeName} must be at least 2 characters
              |                    </mat-error>
              |                }
              |            </mat-form-field>
              |        </app-field-wrapper>
              |    </div>
          """ } }
          |
          |</div>
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part.component.html"
    }
}