/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

/**
 * Generate the content for the template `EntityByIdsCriteriaMapperRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumByIdsCriteriaMapper.kt`
 * - path: `senegai/server/restapi/wto/mapper/SilvaOptionumByIdsCriteriaMapper.kt`
 */
object EntityByIdsCriteriaMapperRenderer : BeEntityRenderer {

    override fun renderTemplate(model: BeEntityModel): String {
        return """
          |package senegai.server.restapi.wto.mapper
          |
          |import senegai.server.restapi.wto.${model.entityRootItem.itemName.pascalCase}ByIdsCriteriaWTO
          |import senegai.server.service.bo.${model.entityRootItem.itemName.pascalCase}ByIdsCriteriaBO
          |
          |object ${model.entityRootItem.itemName.pascalCase}ByIdsCriteriaMapper {
          |
          |    fun ${model.entityRootItem.itemName.pascalCase}ByIdsCriteriaWTO.toBo(): ${model.entityRootItem.itemName.pascalCase}ByIdsCriteriaBO = ${model.entityRootItem.itemName.pascalCase}ByIdsCriteriaBO(
          |        ${model.idAttribute.attributeName.camelCase}List = ${model.idAttribute.attributeName.camelCase}List,
          |    )
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEntityModel): String {
      return "senegai/server/restapi/wto/mapper/${model.entityRootItem.itemName.pascalCase}ByIdsCriteriaMapper.kt"
    }
}