/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

/**
 * Generate the content for the template `EntitySearchCriteriaMapperRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumSearchCriteriaMapper.kt`
 * - path: `senegai/server/restapi/wto/mapper/SilvaOptionumSearchCriteriaMapper.kt`
 */
object EntitySearchCriteriaMapperRenderer : BeEntityRenderer {

    override fun renderTemplate(model: BeEntityModel): String {
        return """
          |package senegai.server.restapi.wto.mapper
          |
          |import senegai.server.restapi.wto.${model.entityRootItem.itemName.pascalCase}SearchCriteriaWTO
          |import senegai.server.service.bo.${model.entityRootItem.itemName.pascalCase}SearchCriteriaBO
          |
          |object ${model.entityRootItem.itemName.pascalCase}SearchCriteriaMapper {
          |
          |    fun ${model.entityRootItem.itemName.pascalCase}SearchCriteriaWTO.toBo(): ${model.entityRootItem.itemName.pascalCase}SearchCriteriaBO = ${model.entityRootItem.itemName.pascalCase}SearchCriteriaBO(
          |        query = query,
          |    )
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEntityModel): String {
      return "senegai/server/restapi/wto/mapper/${model.entityRootItem.itemName.pascalCase}SearchCriteriaMapper.kt"
    }
}