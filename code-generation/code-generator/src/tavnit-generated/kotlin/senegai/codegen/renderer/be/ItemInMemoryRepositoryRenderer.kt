/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemInMemoryRepositoryRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `InMemorySilvaOptionumRepository.kt`
 * - path: `senegai/server/persistence/silvaoptionum/InMemorySilvaOptionumRepository.kt`
 */
object ItemInMemoryRepositoryRenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.persistence.${model.itemName.lowerCase}
          |
          |import org.springframework.stereotype.Repository
          |import senegai.server.service.${model.itemName.lowerCase}.${model.itemName.pascalCase}Repository
          |import senegai.server.service.bo.${model.itemName.pascalCase}BO
          |import senegai.server.service.bo.${model.itemName.pascalCase}ByIdsCriteriaBO
          |import senegai.server.service.bo.${model.itemName.pascalCase}SearchCriteriaBO
          |import java.util.concurrent.ConcurrentHashMap
          |${ if(model.hasUuidPrimaryKey) { """import java.util.${model.primaryKeyAttribute.kotlinAttributeType}
              |""" } else { """""" } }
          |/**
          | * Simple in-memory implementation of the [${model.itemName.pascalCase}Repository] port defined in the
          | * service module. Holds the [${model.itemName.pascalCase}BO] aggregates in memory only; a real
          | * persistence framework can replace this later without touching the service layer.
          | */
          |@Repository
          |class InMemory${model.itemName.pascalCase}Repository : ${model.itemName.pascalCase}Repository {
          |
          |    private val store = ConcurrentHashMap<${model.primaryKeyAttribute.kotlinAttributeType}, ${model.itemName.pascalCase}BO>()
          |
          |    override fun findAll(): List<${model.itemName.pascalCase}BO> = store.values.toList()
          |
          |    override fun findById(${model.primaryKeyAttribute.attributeName.camelCase}: ${model.primaryKeyAttribute.kotlinAttributeType}): ${model.itemName.pascalCase}BO? = store[${model.primaryKeyAttribute.attributeName.camelCase}]
          |
          |    override fun findByIds(criteria: ${model.itemName.pascalCase}ByIdsCriteriaBO): List<${model.itemName.pascalCase}BO> =
          |        criteria.${model.primaryKeyAttribute.attributeName.camelCase}List.mapNotNull { store[it] }
          |
          |    override fun search(searchCriteria: ${model.itemName.pascalCase}SearchCriteriaBO): List<${model.itemName.pascalCase}BO> =
          |        store.values.filter { it.toString().contains(searchCriteria.query, ignoreCase = true) }
          |
          |    override fun save(${model.itemName.camelCase}: ${model.itemName.pascalCase}BO): ${model.itemName.pascalCase}BO {
          |        store[${model.itemName.camelCase}.${model.primaryKeyAttribute.attributeName.camelCase}] = ${model.itemName.camelCase}
          |        return ${model.itemName.camelCase}
          |    }
          |
          |    override fun deleteById(${model.primaryKeyAttribute.attributeName.camelCase}: ${model.primaryKeyAttribute.kotlinAttributeType}) {
          |        store.remove(${model.primaryKeyAttribute.attributeName.camelCase})
          |    }
          |
          |${ if(model.hasGeneratedPrimaryKey) { """    override fun nextId(): ${model.primaryKeyAttribute.kotlinAttributeType} = ${model.nextPrimaryKeyValueExpression}
              |""" } else { """""" } }}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/persistence/${model.itemName.lowerCase}/InMemory${model.itemName.pascalCase}Repository.kt"
    }
}