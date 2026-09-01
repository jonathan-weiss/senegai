/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

/**
 * Generate the content for the template `EntityByIdsResultWTORenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumByIdsResultWTO.kt`
 * - path: `senegai/server/restapi/wto/SilvaOptionumByIdsResultWTO.kt`
 */
object EntityByIdsResultWTORenderer : BeEntityRenderer {

    override fun renderTemplate(model: BeEntityModel): String {
        return """
          |package senegai.server.restapi.wto
          |
          |data class ${model.entityRootItem.itemName.pascalCase}ByIdsResultWTO(
          |    val ${model.entityRootItem.itemName.camelCase}List: List<${model.entityRootItem.itemName.pascalCase}WTO>,
          |)
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEntityModel): String {
      return "senegai/server/restapi/wto/${model.entityRootItem.itemName.pascalCase}ByIdsResultWTO.kt"
    }
}