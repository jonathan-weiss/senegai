/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemMapperRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumMapper.kt`
 * - path: `senegai/server/restapi/wto/mapper/SilvaOptionumMapper.kt`
 */
object ItemMapperRenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.restapi.wto.mapper
          |
          |import senegai.server.restapi.wto.${model.itemName.pascalCase}WTO
          |${ model.usedEnums.joinToString("") { usedEnum ->  """import senegai.server.restapi.wto.mapper.${usedEnum.enumName.pascalCase}Mapper.toBo
              |import senegai.server.restapi.wto.mapper.${usedEnum.enumName.pascalCase}Mapper.toWto
              |""" } }${ model.directlyNestedItems.joinToString("") { nestedItem ->  """import senegai.server.restapi.wto.mapper.${nestedItem.itemName.pascalCase}Mapper.toBo
              |import senegai.server.restapi.wto.mapper.${nestedItem.itemName.pascalCase}Mapper.toWto
              |""" } }import senegai.server.service.bo.${model.itemName.pascalCase}BO
          |
          |/**
          | * Maps between the WTOs (transport layer) and BOs (business layer).
          | */
          |object ${model.itemName.pascalCase}Mapper {
          |
          |    fun ${model.itemName.pascalCase}WTO.toBo(): ${model.itemName.pascalCase}BO = ${model.itemName.pascalCase}BO(
          |${ model.attributes.joinToString("") { attribute ->  """        ${attribute.attributeName.camelCase} = ${attribute.attributeName.camelCase}${attribute.toBoMappingSuffix},
              |""" } }    )
          |
          |    fun ${model.itemName.pascalCase}BO.toWto(): ${model.itemName.pascalCase}WTO = ${model.itemName.pascalCase}WTO(
          |${ model.attributes.joinToString("") { attribute ->  """        ${attribute.attributeName.camelCase} = ${attribute.attributeName.camelCase}${attribute.toWtoMappingSuffix},
              |""" } }    )
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/restapi/wto/mapper/${model.itemName.pascalCase}Mapper.kt"
    }
}