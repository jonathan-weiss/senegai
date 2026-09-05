/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemRepositoryRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumRepository.kt`
 * - path: `senegai/server/service/silvaoptionum/SilvaOptionumRepository.kt`
 */
object ItemRepositoryRenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.service.${model.itemName.lowerCase}
          |
          |import senegai.server.service.bo.${model.itemName.pascalCase}BO
          |import senegai.server.service.bo.${model.itemName.pascalCase}ByIdsCriteriaBO
          |import senegai.server.service.bo.${model.itemName.pascalCase}SearchCriteriaBO
          |${ if(model.hasUuidPrimaryKey) { """import java.util.${model.primaryKeyAttribute.kotlinAttributeType}
              |""" } else { """""" } }
          |/**
          | * Port for persisting the ${model.itemName.pascalCase} root object [${model.itemName.pascalCase}BO]. The implementation
          | * lives in the persistence module, so the service (business) layer stays independent of
          | * any persistence technology.
          | *
          | * Always operates on the whole [${model.itemName.pascalCase}BO] aggregate, never on nested items.
          | */
          |interface ${model.itemName.pascalCase}Repository {
          |
          |    fun findAll(): List<${model.itemName.pascalCase}BO>
          |
          |    fun findById(${model.primaryKeyAttribute.attributeName.camelCase}: ${model.primaryKeyAttribute.kotlinAttributeType}): ${model.itemName.pascalCase}BO?
          |
          |    fun findByIds(criteria: ${model.itemName.pascalCase}ByIdsCriteriaBO): List<${model.itemName.pascalCase}BO>
          |
          |    fun search(searchCriteria: ${model.itemName.pascalCase}SearchCriteriaBO): List<${model.itemName.pascalCase}BO>
          |
          |    fun save(${model.itemName.camelCase}: ${model.itemName.pascalCase}BO): ${model.itemName.pascalCase}BO
          |
          |    fun deleteById(${model.primaryKeyAttribute.attributeName.camelCase}: ${model.primaryKeyAttribute.kotlinAttributeType})
          |
          |${ if(model.hasGeneratedPrimaryKey) { """    /**
              |     * A primary key that no stored ${model.itemName.pascalCase} is identified by, for a ${model.itemName.pascalCase} that is
              |     * created. Handing out identity is the job of the persistence, like a sequence of a database.
              |     *
              |     * Only exists for an item whose key is handed out; a key of a textual type is supplied by
              |     * the caller instead.
              |     */
              |    fun nextId(): ${model.primaryKeyAttribute.kotlinAttributeType}
              |""" } else { """""" } }}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/service/${model.itemName.lowerCase}/${model.itemName.pascalCase}Repository.kt"
    }
}