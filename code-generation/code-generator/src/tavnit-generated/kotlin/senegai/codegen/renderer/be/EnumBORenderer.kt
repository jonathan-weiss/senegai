/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEnumModel

/**
 * Generate the content for the template `EnumBORenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `AppellatioComis.kt`
 * - path: `senegai/server/service/bo/AppellatioComis.kt`
 */
object EnumBORenderer : BeEnumRenderer {

    override fun renderTemplate(model: BeEnumModel): String {
        return """
          |package senegai.server.service.bo
          |
          |/**
          | * Business enum [${model.enumName.pascalCase}]. Pure business representation,
          | * independent of the WTO layer used for transport.
          | */
          |enum class ${model.enumName.pascalCase} {
          |${ model.enumValues.joinToString("") { enumValue ->  """    ${enumValue.screamingSnakeCase},
              |""" } }}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEnumModel): String {
      return "senegai/server/service/bo/${model.enumName.pascalCase}.kt"
    }
}