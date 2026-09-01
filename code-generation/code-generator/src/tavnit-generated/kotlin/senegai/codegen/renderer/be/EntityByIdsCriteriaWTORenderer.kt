/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

/**
 * Generate the content for the template `EntityByIdsCriteriaWTORenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumByIdsCriteriaWTO.kt`
 * - path: `senegai/server/restapi/wto/SilvaOptionumByIdsCriteriaWTO.kt`
 */
object EntityByIdsCriteriaWTORenderer : BeEntityRenderer {

    override fun renderTemplate(model: BeEntityModel): String {
        return """
          |package senegai.server.restapi.wto
          |
          |import java.util.UUID
          |
          |data class ${model.entityRootItem.itemName.pascalCase}ByIdsCriteriaWTO(
          |    val ${model.idAttribute.attributeName.camelCase}List: List<UUID> = emptyList(),
          |)
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEntityModel): String {
      return "senegai/server/restapi/wto/${model.entityRootItem.itemName.pascalCase}ByIdsCriteriaWTO.kt"
    }
}