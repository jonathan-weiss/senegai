/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityReferenceFieldComponentScssRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `entitas-relata-membrum-relatum-reference-field.component.scss`
 * - path: `entitas-relata/entitas-relata-membrum-relatum-reference-field/entitas-relata-membrum-relatum-reference-field.component.scss`
 */
object EntityReferenceFieldComponentScssRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |:host {
          |    display: block;
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-field/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-field.component.scss"
    }
}