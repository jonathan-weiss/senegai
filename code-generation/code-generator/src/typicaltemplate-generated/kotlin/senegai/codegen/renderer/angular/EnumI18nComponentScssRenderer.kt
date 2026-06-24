/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEnumModel

/**
 * Generate the content for the template `EnumI18nComponentScssRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `appellatio-comis-i18n.component.scss`
 * - path: `enum/appellatio-comis-i18n/appellatio-comis-i18n.component.scss`
 */
object EnumI18nComponentScssRenderer : UiEnumRenderer {

    override fun renderTemplate(model: UiEnumModel): String {
        return """
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEnumModel): String {
      return "enum/${model.enumName.kebabCase}-i18n/${model.enumName.kebabCase}-i18n.component.scss"
    }
}