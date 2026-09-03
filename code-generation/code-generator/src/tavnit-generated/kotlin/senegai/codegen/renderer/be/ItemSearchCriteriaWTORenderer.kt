/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemSearchCriteriaWTORenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumSearchCriteriaWTO.kt`
 * - path: `senegai/server/restapi/wto/SilvaOptionumSearchCriteriaWTO.kt`
 */
object ItemSearchCriteriaWTORenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.restapi.wto
          |
          |data class ${model.itemName.pascalCase}SearchCriteriaWTO(
          |    val query: String = "",
          |)
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/restapi/wto/${model.itemName.pascalCase}SearchCriteriaWTO.kt"
    }
}