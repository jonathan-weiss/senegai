/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemByIdsResultWTORenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumByIdsResultWTO.kt`
 * - path: `senegai/server/restapi/wto/SilvaOptionumByIdsResultWTO.kt`
 */
object ItemByIdsResultWTORenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.restapi.wto
          |
          |data class ${model.itemName.pascalCase}ByIdsResultWTO(
          |    val ${model.itemName.camelCase}List: List<${model.itemName.pascalCase}WTO>,
          |)
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/restapi/wto/${model.itemName.pascalCase}ByIdsResultWTO.kt"
    }
}