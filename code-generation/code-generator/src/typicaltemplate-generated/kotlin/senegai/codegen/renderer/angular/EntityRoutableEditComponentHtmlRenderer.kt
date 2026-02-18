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
          |@if (selected${model.entityName}) {
          |    <app-${model.entityNameDashCase}-form [${model.entityNameLowercase}]="selected${model.entityName}" />
          |} @else {
          |   Loading...
          |}
          |
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameDashCase}/${model.entityNameDashCase}-routable-edit/${model.entityNameDashCase}-routable-edit.component.html"
    }
}