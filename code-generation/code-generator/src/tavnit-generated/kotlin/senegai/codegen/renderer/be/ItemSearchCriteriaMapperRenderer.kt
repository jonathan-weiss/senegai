/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemSearchCriteriaMapperRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumSearchCriteriaMapper.kt`
 * - path: `senegai/server/restapi/wto/mapper/SilvaOptionumSearchCriteriaMapper.kt`
 */
object ItemSearchCriteriaMapperRenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.restapi.wto.mapper
          |
          |import senegai.server.restapi.wto.${model.itemName.pascalCase}SearchCriteriaWTO
          |import senegai.server.service.bo.${model.itemName.pascalCase}SearchCriteriaBO
          |
          |object ${model.itemName.pascalCase}SearchCriteriaMapper {
          |
          |    fun ${model.itemName.pascalCase}SearchCriteriaWTO.toBo(): ${model.itemName.pascalCase}SearchCriteriaBO = ${model.itemName.pascalCase}SearchCriteriaBO(
          |        query = query,
          |    )
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/restapi/wto/mapper/${model.itemName.pascalCase}SearchCriteriaMapper.kt"
    }
}