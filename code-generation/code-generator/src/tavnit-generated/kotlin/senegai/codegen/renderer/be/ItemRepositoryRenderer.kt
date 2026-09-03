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
          |import java.util.UUID
          |
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
          |    fun findById(${model.primaryKeyAttribute.attributeName.camelCase}: UUID): ${model.itemName.pascalCase}BO?
          |
          |    fun findByIds(criteria: ${model.itemName.pascalCase}ByIdsCriteriaBO): List<${model.itemName.pascalCase}BO>
          |
          |    fun search(searchCriteria: ${model.itemName.pascalCase}SearchCriteriaBO): List<${model.itemName.pascalCase}BO>
          |
          |    fun save(${model.itemName.camelCase}: ${model.itemName.pascalCase}BO): ${model.itemName.pascalCase}BO
          |
          |    fun deleteById(${model.primaryKeyAttribute.attributeName.camelCase}: UUID)
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/service/${model.itemName.lowerCase}/${model.itemName.pascalCase}Repository.kt"
    }
}