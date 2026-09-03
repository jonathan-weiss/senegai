/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemByIdsCriteriaWTORenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumByIdsCriteriaWTO.kt`
 * - path: `senegai/server/restapi/wto/SilvaOptionumByIdsCriteriaWTO.kt`
 */
object ItemByIdsCriteriaWTORenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.restapi.wto
          |
          |import java.util.UUID
          |
          |data class ${model.itemName.pascalCase}ByIdsCriteriaWTO(
          |    val ${model.primaryKeyAttribute.attributeName.camelCase}List: List<UUID> = emptyList(),
          |)
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/restapi/wto/${model.itemName.pascalCase}ByIdsCriteriaWTO.kt"
    }
}