/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityRoutableEditComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-routable-edit.component.html`
 * - path: `opus-magnum/opus-magnum-routable-edit/opus-magnum-routable-edit.component.html`
 */
object EntityRoutableEditComponentHtmlRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |@if (selected${model.entityName.pascalCase}) {
          |    <app-${model.entityName.kebabCase}-form [${model.entityRootItem.itemName.camelCase}]="selected${model.entityName.pascalCase}" />
          |} @else {
          |   Loading...
          |}
          |
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-routable-edit/${model.entityName.kebabCase}-routable-edit.component.html"
    }
}