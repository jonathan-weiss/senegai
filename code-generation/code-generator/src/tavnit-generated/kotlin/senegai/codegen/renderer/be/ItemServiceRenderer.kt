/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemServiceRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumService.kt`
 * - path: `senegai/server/service/silvaoptionum/SilvaOptionumService.kt`
 */
object ItemServiceRenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.service.${model.itemName.lowerCase}
          |
          |import org.springframework.stereotype.Service
          |import senegai.server.service.bo.${model.itemName.pascalCase}BO
          |import senegai.server.service.bo.${model.itemName.pascalCase}ByIdsCriteriaBO
          |import senegai.server.service.bo.${model.itemName.pascalCase}SearchCriteriaBO
          |${ if(model.hasUuidPrimaryKey) { """import java.util.${model.primaryKeyAttribute.kotlinAttributeType}
              |""" } else { """""" } }
          |/**
          | * Business service of the ${model.itemName.pascalCase} business context. It is called by the REST layer
          | * with [${model.itemName.pascalCase}BO] business objects (never WTOs) and delegates persistence to the
          | * [${model.itemName.pascalCase}Repository] port.
          | *
          | * Every operation works on the whole [${model.itemName.pascalCase}BO] aggregate as a single unit.
          | */
          |@Service
          |class ${model.itemName.pascalCase}Service(
          |    private val ${model.itemName.camelCase}Repository: ${model.itemName.pascalCase}Repository,
          |) {
          |
          |    fun get${model.itemName.pascalCase}List(): List<${model.itemName.pascalCase}BO> = ${model.itemName.camelCase}Repository.findAll()
          |
          |    fun get${model.itemName.pascalCase}ById(${model.primaryKeyAttribute.attributeName.camelCase}: ${model.primaryKeyAttribute.kotlinAttributeType}): ${model.itemName.pascalCase}BO? =
          |        ${model.itemName.camelCase}Repository.findById(${model.primaryKeyAttribute.attributeName.camelCase})
          |
          |    fun get${model.itemName.pascalCase}ListByIds(criteria: ${model.itemName.pascalCase}ByIdsCriteriaBO): List<${model.itemName.pascalCase}BO> =
          |        ${model.itemName.camelCase}Repository.findByIds(criteria)
          |
          |    fun search${model.itemName.pascalCase}List(searchCriteria: ${model.itemName.pascalCase}SearchCriteriaBO): List<${model.itemName.pascalCase}BO> =
          |        ${model.itemName.camelCase}Repository.search(searchCriteria)
          |
          |    fun create${model.itemName.pascalCase}(${model.itemName.camelCase}: ${model.itemName.pascalCase}BO): ${model.itemName.pascalCase}BO {
          |        val toCreate = ${model.itemName.camelCase}.copy(${model.primaryKeyAttribute.attributeName.camelCase} = ${model.itemName.camelCase}Repository.nextId())
          |        return ${model.itemName.camelCase}Repository.save(toCreate)
          |    }
          |
          |    fun update${model.itemName.pascalCase}(${model.itemName.camelCase}: ${model.itemName.pascalCase}BO): ${model.itemName.pascalCase}BO =
          |        ${model.itemName.camelCase}Repository.save(${model.itemName.camelCase})
          |
          |    fun delete${model.itemName.pascalCase}(${model.primaryKeyAttribute.attributeName.camelCase}: ${model.primaryKeyAttribute.kotlinAttributeType}) = ${model.itemName.camelCase}Repository.deleteById(${model.primaryKeyAttribute.attributeName.camelCase})
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/service/${model.itemName.lowerCase}/${model.itemName.pascalCase}Service.kt"
    }
}