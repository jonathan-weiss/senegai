/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

/**
 * Generate the content for the template `EntitySearchCriteriaBORenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumSearchCriteriaBO.kt`
 * - path: `senegai/server/service/bo/SilvaOptionumSearchCriteriaBO.kt`
 */
object EntitySearchCriteriaBORenderer : BeEntityRenderer {

    override fun renderTemplate(model: BeEntityModel): String {
        return """
          |package senegai.server.service.bo
          |
          |/**
          | * Business object for the search criteria of the ${model.entityRootItem.itemName.pascalCase} aggregates.
          | *
          | * Holds a single free text [query]; a blank query matches every [${model.entityRootItem.itemName.pascalCase}BO].
          | */
          |data class ${model.entityRootItem.itemName.pascalCase}SearchCriteriaBO(
          |    val query: String,
          |)
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEntityModel): String {
      return "senegai/server/service/bo/${model.entityRootItem.itemName.pascalCase}SearchCriteriaBO.kt"
    }
}