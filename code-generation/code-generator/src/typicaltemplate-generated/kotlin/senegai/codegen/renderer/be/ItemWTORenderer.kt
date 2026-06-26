/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemWTORenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumWTO.kt`
 * - path: `senegai/server/restapi/wto/SilvaOptionumWTO.kt`
 */
object ItemWTORenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.restapi.wto
          |
          |
          |/**
          | * Root WTO (Web Transfer Object), mirroring the Angular `${model.itemName.pascalCase}WTO` interface
          | * field by field so it serializes 1:1 for the client.
          | */
          |data class ${model.itemName.pascalCase}WTO(
          |${ model.attributes.joinToString("") { attribute ->  """    val ${attribute.attributeName.camelCase}: ${attribute.wtoAttributeType},
              |""" } })
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/restapi/wto/${model.itemName.pascalCase}WTO.kt"
    }
}