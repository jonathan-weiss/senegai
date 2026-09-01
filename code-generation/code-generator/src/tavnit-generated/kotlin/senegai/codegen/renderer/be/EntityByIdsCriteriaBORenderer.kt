/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

/**
 * Generate the content for the template `EntityByIdsCriteriaBORenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumByIdsCriteriaBO.kt`
 * - path: `senegai/server/service/bo/SilvaOptionumByIdsCriteriaBO.kt`
 */
object EntityByIdsCriteriaBORenderer : BeEntityRenderer {

    override fun renderTemplate(model: BeEntityModel): String {
        return """
          |package senegai.server.service.bo
          |
          |import java.util.UUID
          |
          |/**
          | * Business object for resolving a whole set of references to ${model.entityRootItem.itemName.pascalCase} aggregates at once.
          | *
          | * Holds the identifiers a reference to this entity is stored as; unknown ones are simply not
          | * part of the result.
          | */
          |data class ${model.entityRootItem.itemName.pascalCase}ByIdsCriteriaBO(
          |    val ${model.idAttribute.attributeName.camelCase}List: List<UUID>,
          |)
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEntityModel): String {
      return "senegai/server/service/bo/${model.entityRootItem.itemName.pascalCase}ByIdsCriteriaBO.kt"
    }
}