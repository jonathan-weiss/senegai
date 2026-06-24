/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEnumModel

/**
 * Generate the content for the template `EnumDefinitionTypescriptRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `appellatio-comis.enum.ts`
 * - path: `wto/appellatio-comis.enum.ts`
 */
object EnumDefinitionTypescriptRenderer : UiEnumRenderer {

    override fun renderTemplate(model: UiEnumModel): String {
        return """
          |export enum ${model.enumName.pascalCase}Enum {
          |${ model.enumValues.joinToString("") { enumValue ->  """    ${enumValue.screamingSnakeCase} = '${enumValue.screamingSnakeCase}',
              |""" } }}
          |
          |export const ${model.enumName.pascalCase}EnumValues: ReadonlyArray<${model.enumName.pascalCase}Enum> = [
          |${ model.enumValues.joinToString("") { enumValue ->  """    ${model.enumName.pascalCase}Enum.${enumValue.screamingSnakeCase},
              |""" } }]
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEnumModel): String {
      return "wto/${model.enumName.kebabCase}.enum.ts"
    }
}