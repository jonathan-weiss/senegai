/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemByIdsCriteriaMapperRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumByIdsCriteriaMapper.kt`
 * - path: `senegai/server/restapi/wto/mapper/SilvaOptionumByIdsCriteriaMapper.kt`
 */
object ItemByIdsCriteriaMapperRenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.restapi.wto.mapper
          |
          |import senegai.server.restapi.wto.${model.itemName.pascalCase}ByIdsCriteriaWTO
          |import senegai.server.service.bo.${model.itemName.pascalCase}ByIdsCriteriaBO
          |
          |object ${model.itemName.pascalCase}ByIdsCriteriaMapper {
          |
          |    fun ${model.itemName.pascalCase}ByIdsCriteriaWTO.toBo(): ${model.itemName.pascalCase}ByIdsCriteriaBO = ${model.itemName.pascalCase}ByIdsCriteriaBO(
          |        ${model.primaryKeyAttribute.attributeName.camelCase}List = ${model.primaryKeyAttribute.attributeName.camelCase}List,
          |    )
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/restapi/wto/mapper/${model.itemName.pascalCase}ByIdsCriteriaMapper.kt"
    }
}