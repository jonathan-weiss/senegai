/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemByIdsCriteriaBORenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumByIdsCriteriaBO.kt`
 * - path: `senegai/server/service/bo/SilvaOptionumByIdsCriteriaBO.kt`
 */
object ItemByIdsCriteriaBORenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.service.bo
          |
          |import java.util.UUID
          |
          |/**
          | * Business object for resolving a whole set of references to ${model.itemName.pascalCase} aggregates at once.
          | *
          | * Holds the identifiers a reference to this item is stored as; unknown ones are simply not
          | * part of the result.
          | */
          |data class ${model.itemName.pascalCase}ByIdsCriteriaBO(
          |    val ${model.primaryKeyAttribute.attributeName.camelCase}List: List<UUID>,
          |)
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/service/bo/${model.itemName.pascalCase}ByIdsCriteriaBO.kt"
    }
}