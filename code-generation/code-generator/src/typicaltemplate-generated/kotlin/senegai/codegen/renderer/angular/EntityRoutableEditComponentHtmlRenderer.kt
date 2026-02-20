/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityRoutableEditComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object EntityRoutableEditComponentHtmlRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |@if (selected${model.entityName.pascalCase}) {
          |    <app-${model.entityName.kebabCase}-form [${model.entityName.camelCase}]="selected${model.entityName.pascalCase}" />
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