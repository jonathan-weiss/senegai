/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

/**
 * Generate the content for the template `EntitySearchCriteriaWTORenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumSearchCriteriaWTO.kt`
 * - path: `senegai/server/restapi/wto/SilvaOptionumSearchCriteriaWTO.kt`
 */
object EntitySearchCriteriaWTORenderer : BeEntityRenderer {

    override fun renderTemplate(model: BeEntityModel): String {
        return """
          |package senegai.server.restapi.wto
          |
          |data class ${model.entityRootItem.itemName.pascalCase}SearchCriteriaWTO(
          |    val query: String = "",
          |)
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEntityModel): String {
      return "senegai/server/restapi/wto/${model.entityRootItem.itemName.pascalCase}SearchCriteriaWTO.kt"
    }
}