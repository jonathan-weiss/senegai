/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEnumModel

/**
 * Generate the content for the template `EnumSelectorComponentScssRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `appellatio-selector.component.scss`
 * - path: `enum/appellatio-input-selection/appellatio-selector.component.scss`
 */
object EnumSelectorComponentScssRenderer : UiEnumRenderer {

    override fun renderTemplate(model: UiEnumModel): String {
        return """
          |:host {
          |    display: block;
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEnumModel): String {
      return "enum/${model.enumName.camelCase}-input-selection/${model.enumName.camelCase}-selector.component.scss"
    }
}