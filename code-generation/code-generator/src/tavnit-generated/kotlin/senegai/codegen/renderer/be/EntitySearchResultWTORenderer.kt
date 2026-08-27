/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

/**
 * Generate the content for the template `EntitySearchResultWTORenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumSearchResultWTO.kt`
 * - path: `senegai/server/restapi/wto/SilvaOptionumSearchResultWTO.kt`
 */
object EntitySearchResultWTORenderer : BeEntityRenderer {

    override fun renderTemplate(model: BeEntityModel): String {
        return """
          |package senegai.server.restapi.wto
          |
          |data class ${model.entityRootItem.itemName.pascalCase}SearchResultWTO(
          |    val ${model.entityRootItem.itemName.camelCase}List: List<${model.entityRootItem.itemName.pascalCase}WTO>,
          |)
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEntityModel): String {
      return "senegai/server/restapi/wto/${model.entityRootItem.itemName.pascalCase}SearchResultWTO.kt"
    }
}