/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemBORenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumBO.kt`
 * - path: `senegai/server/service/bo/SilvaOptionumBO.kt`
 */
object ItemBORenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.service.bo
          |
          |
          |/**
          | * Business object for the [${model.itemName.pascalCase}BO] item.
          | */
          |data class ${model.itemName.pascalCase}BO(
          |${ model.attributes.joinToString("") { attribute ->  """    val ${attribute.attributeName.camelCase}: ${attribute.kotlinAttributeType},
              |""" } })
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/service/bo/${model.itemName.pascalCase}BO.kt"
    }
}