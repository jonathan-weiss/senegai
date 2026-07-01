/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEnumModel

/**
 * Generate the content for the template `EnumWTORenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `AppellatioComisEnum.kt`
 * - path: `senegai/server/restapi/wto/AppellatioComisEnum.kt`
 */
object EnumWTORenderer : BeEnumRenderer {

    override fun renderTemplate(model: BeEnumModel): String {
        return """
          |package senegai.server.restapi.wto
          |
          |/**
          | * WTO (Web Transfer Object) enum mirroring the Angular `${model.enumName.pascalCase}Enum`.
          | */
          |enum class ${model.enumName.pascalCase}Enum {
          |${ model.enumValues.joinToString("") { enumValue ->  """    ${enumValue.screamingSnakeCase},
              |""" } }}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEnumModel): String {
      return "senegai/server/restapi/wto/${model.enumName.pascalCase}Enum.kt"
    }
}