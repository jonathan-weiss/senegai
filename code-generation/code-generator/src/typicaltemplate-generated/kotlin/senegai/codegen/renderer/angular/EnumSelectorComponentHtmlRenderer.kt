/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEnumModel

/**
 * Generate the content for the template `EnumSelectorComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `appellatio-comis-selector.component.html`
 * - path: `enum/appellatio-comis-input-selection/appellatio-comis-selector.component.html`
 */
object EnumSelectorComponentHtmlRenderer : UiEnumRenderer {

    override fun renderTemplate(model: UiEnumModel): String {
        return """
          |<mat-form-field appearance="fill" style="width: 100%">
          |    @if (label != '') {
          |        <mat-label>{{ label }}</mat-label>
          |    }
          |    <mat-select [formControl]="enumFormControl">
          |        @for (${model.enumName.camelCase} of ${model.enumName.camelCase}List; track ${model.enumName.camelCase}) {
          |            <mat-option [value]="${model.enumName.camelCase}">
          |                <app-${model.enumName.kebabCase}-i18n [enumValue]="${model.enumName.camelCase}" />
          |            </mat-option>
          |        }
          |    </mat-select>
          |
          |    @for (validatorTranslation of validatorTranslations; track validatorTranslation) {
          |        @if (hasError(validatorTranslation.validatorName)) {
          |            <mat-error>{{ validatorTranslation.validatorTranslationKey | transloco: validatorTranslation.validatorTranslationParams }}</mat-error>
          |        }
          |    }
          |</mat-form-field>
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEnumModel): String {
      return "enum/${model.enumName.kebabCase}-input-selection/${model.enumName.kebabCase}-selector.component.html"
    }
}