/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityRoutableEditComponentScssRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-routable-edit.component.scss`
 * - path: `opus-magnum/opus-magnum-routable-edit/opus-magnum-routable-edit.component.scss`
 */
object EntityRoutableEditComponentScssRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-routable-edit/${model.entityName.kebabCase}-routable-edit.component.scss"
    }
}