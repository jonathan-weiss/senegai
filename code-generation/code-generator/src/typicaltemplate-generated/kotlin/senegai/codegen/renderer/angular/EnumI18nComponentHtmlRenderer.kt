/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEnumModel

/**
 * Generate the content for the template `EnumI18nComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `appellatio-i18n.component.html`
 * - path: `enum/appellatio-i18n/appellatio-i18n.component.html`
 */
object EnumI18nComponentHtmlRenderer : UiEnumRenderer {

    override fun renderTemplate(model: UiEnumModel): String {
        return """
          |<ng-container>{{ enumValue }}</ng-container>
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEnumModel): String {
      return "enum/${model.enumName.camelCase}-i18n/${model.enumName.camelCase}-i18n.component.html"
    }
}