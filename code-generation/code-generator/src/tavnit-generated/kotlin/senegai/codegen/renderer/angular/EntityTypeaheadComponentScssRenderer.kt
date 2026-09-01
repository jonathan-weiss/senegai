/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityTypeaheadComponentScssRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `entitas-relata-typeahead.component.scss`
 * - path: `entitas-relata/entitas-relata-typeahead/entitas-relata-typeahead.component.scss`
 */
object EntityTypeaheadComponentScssRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |.typeahead-field {
          |  width: 100%;
          |  min-width: 300px;
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-typeahead/${model.entityName.kebabCase}-typeahead.component.scss"
    }
}