/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEnumModel

/**
 * Generate the content for the template `EnumI18nComponentTypescriptRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `appellatio-i18n.component.ts`
 * - path: `enum/appellatio-i18n/appellatio-i18n.component.ts`
 */
object EnumI18nComponentTypescriptRenderer : UiEnumRenderer {

    override fun renderTemplate(model: UiEnumModel): String {
        return """
          |import {Component, Input} from '@angular/core';
          |import {${model.enumName.pascalCase}Enum} from "@app/wto/${model.enumName.camelCase}.enum";
          |
          |@Component({
          |    selector: 'app-${model.enumName.camelCase}-i18n',
          |    templateUrl: './${model.enumName.camelCase}-i18n.component.html',
          |    styleUrls: ['./${model.enumName.camelCase}-i18n.component.scss'],
          |    standalone: true,
          |})
          |export class ${model.enumName.pascalCase}I18nComponent {
          |    @Input({ required: true }) enumValue!: ${model.enumName.pascalCase}Enum;
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEnumModel): String {
      return "enum/${model.enumName.camelCase}-i18n/${model.enumName.camelCase}-i18n.component.ts"
    }
}