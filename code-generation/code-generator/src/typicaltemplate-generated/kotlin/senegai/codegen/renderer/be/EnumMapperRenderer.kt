/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEnumModel

/**
 * Generate the content for the template `EnumMapperRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `AppellatioComisMapper.kt`
 * - path: `senegai/server/restapi/wto/mapper/AppellatioComisMapper.kt`
 */
object EnumMapperRenderer : BeEnumRenderer {

    override fun renderTemplate(model: BeEnumModel): String {
        return """
          |package senegai.server.restapi.wto.mapper
          |
          |import senegai.server.restapi.wto.${model.enumName.pascalCase}Enum
          |import senegai.server.service.bo.${model.enumName.pascalCase}
          |
          |/**
          | * Maps between the WTOs (transport layer) and BOs (business layer).
          | */
          |object ${model.enumName.pascalCase}Mapper {
          |
          |    fun ${model.enumName.pascalCase}Enum.toBo() = when (this) {
          |${ model.enumValues.joinToString("") { enumValue ->  """        ${model.enumName.pascalCase}Enum.${enumValue.screamingSnakeCase} -> ${model.enumName.pascalCase}.${enumValue.screamingSnakeCase}
              |""" } }    }
          |
          |    fun ${model.enumName.pascalCase}.toWto() = when (this) {
          |${ model.enumValues.joinToString("") { enumValue ->  """        ${model.enumName.pascalCase}.${enumValue.screamingSnakeCase} -> ${model.enumName.pascalCase}Enum.${enumValue.screamingSnakeCase}
              |""" } }    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEnumModel): String {
      return "senegai/server/restapi/wto/mapper/${model.enumName.pascalCase}Mapper.kt"
    }
}