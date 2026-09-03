/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template `ItemReferenceFieldComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `membrum-relatum-reference-field.component.html`
 * - path: `reference/membrum-relatum-reference-field/membrum-relatum-reference-field.component.html`
 */
object ItemReferenceFieldComponentHtmlRenderer : UiItemRenderer {

    override fun renderTemplate(model: UiItemModel): String {
        return """
          |<app-${model.itemName.kebabCase}-typeahead
          |        label="${model.itemName.pascalCase}"
          |        [selectionLabel]="selectionLabel()"
          |        [disabled]="${model.itemName.camelCase}ReferenceFormControl.disabled"
          |        (${model.itemName.camelCase}Selected)="on${model.itemName.pascalCase}Selected(${"$"}event)" />
          |<app-field-error-messages [control]="${model.itemName.camelCase}ReferenceFormControl"
          |                          [validatorTranslations]="validatorTranslations" />
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiItemModel): String {
      return "reference/${model.itemName.kebabCase}-reference-field/${model.itemName.kebabCase}-reference-field.component.html"
    }
}