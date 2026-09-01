/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityReferenceFieldComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `entitas-relata-membrum-relatum-reference-field.component.html`
 * - path: `entitas-relata/entitas-relata-membrum-relatum-reference-field/entitas-relata-membrum-relatum-reference-field.component.html`
 */
object EntityReferenceFieldComponentHtmlRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |<app-${model.entityName.kebabCase}-typeahead
          |        label="${model.entityName.pascalCase}"
          |        [selectionLabel]="selectionLabel()"
          |        [disabled]="${model.entityRootItem.itemName.camelCase}ReferenceFormControl.disabled"
          |        (${model.entityRootItem.itemName.camelCase}Selected)="on${model.entityRootItem.itemName.pascalCase}Selected(${"$"}event)" />
          |<app-field-error-messages [control]="${model.entityRootItem.itemName.camelCase}ReferenceFormControl"
          |                          [validatorTranslations]="validatorTranslations" />
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-field/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-field.component.html"
    }
}