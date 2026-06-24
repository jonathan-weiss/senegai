/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEnumModel

/**
 * Generate the content for the template `EnumListFormFieldTableComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `single-appellatio-comis-form-field-table.component.html`
 * - path: `enum/appellatio-comis-input-selection/single-appellatio-comis-form-field-table.component.html`
 */
object EnumListFormFieldTableComponentHtmlRenderer : UiEnumRenderer {

    override fun renderTemplate(model: UiEnumModel): String {
        return """
          |<app-single-form-field-table [formArray]="formArray" [columnHeader]="columnHeader" [createControl]="createControl">
          |    <ng-template let-control>
          |        <app-${model.enumName.kebabCase}-selector [enumFormControl]="control" [validatorTranslations]="validatorTranslations" />
          |    </ng-template>
          |</app-single-form-field-table>
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEnumModel): String {
      return "enum/${model.enumName.kebabCase}-input-selection/single-${model.enumName.kebabCase}-form-field-table.component.html"
    }
}