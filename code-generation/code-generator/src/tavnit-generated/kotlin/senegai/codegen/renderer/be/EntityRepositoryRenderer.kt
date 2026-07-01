/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

/**
 * Generate the content for the template `EntityRepositoryRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `OpusMagnumRepository.kt`
 * - path: `senegai/server/service/opusmagnum/OpusMagnumRepository.kt`
 */
object EntityRepositoryRenderer : BeEntityRenderer {

    override fun renderTemplate(model: BeEntityModel): String {
        return """
          |package senegai.server.service.${model.entityName.lowerCase}
          |
          |import senegai.server.service.bo.${model.entityRootItem.itemName.pascalCase}BO
          |
          |/**
          | * Port for persisting the ${model.entityName.pascalCase} root object [${model.entityRootItem.itemName.pascalCase}BO]. The implementation
          | * lives in the persistence module, so the service (business) layer stays independent of
          | * any persistence technology.
          | *
          | * Always operates on the whole [${model.entityRootItem.itemName.pascalCase}BO] aggregate, never on nested items.
          | */
          |interface ${model.entityName.pascalCase}Repository {
          |
          |    fun findAll(): List<${model.entityRootItem.itemName.pascalCase}BO>
          |
          |    fun findById(${model.idAttribute.attributeName.camelCase}: String): ${model.entityRootItem.itemName.pascalCase}BO?
          |
          |    /** Inserts or replaces the given [${model.entityRootItem.itemName.camelCase}] (matched by [${model.entityRootItem.itemName.pascalCase}BO.${model.idAttribute.attributeName.camelCase}]). */
          |    fun save(${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}BO): ${model.entityRootItem.itemName.pascalCase}BO
          |
          |    fun deleteById(${model.idAttribute.attributeName.camelCase}: String)
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEntityModel): String {
      return "senegai/server/service/${model.entityName.lowerCase}/${model.entityName.pascalCase}Repository.kt"
    }
}