/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

/**
 * Generate the content for the template `EntityInMemoryRepositoryRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `InMemoryOpusMagnumRepository.kt`
 * - path: `senegai/server/persistence/opusmagnum/InMemoryOpusMagnumRepository.kt`
 */
object EntityInMemoryRepositoryRenderer : BeEntityRenderer {

    override fun renderTemplate(model: BeEntityModel): String {
        return """
          |package senegai.server.persistence.${model.entityName.lowerCase}
          |
          |import org.springframework.stereotype.Repository
          |import senegai.server.service.${model.entityName.lowerCase}.${model.entityName.pascalCase}Repository
          |import senegai.server.service.bo.${model.entityRootItem.itemName.pascalCase}BO
          |import java.util.concurrent.ConcurrentHashMap
          |
          |/**
          | * Simple in-memory implementation of the [${model.entityName.pascalCase}Repository] port defined in the
          | * service module. Holds the [${model.entityRootItem.itemName.pascalCase}BO] aggregates in memory only; a real
          | * persistence framework can replace this later without touching the service layer.
          | */
          |@Repository
          |class InMemory${model.entityName.pascalCase}Repository : ${model.entityName.pascalCase}Repository {
          |
          |    private val store = ConcurrentHashMap<String, ${model.entityRootItem.itemName.pascalCase}BO>()
          |
          |    override fun findAll(): List<${model.entityRootItem.itemName.pascalCase}BO> = store.values.toList()
          |
          |    override fun findById(${model.idAttribute.attributeName.camelCase}: String): ${model.entityRootItem.itemName.pascalCase}BO? = store[${model.idAttribute.attributeName.camelCase}]
          |
          |    override fun save(${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}BO): ${model.entityRootItem.itemName.pascalCase}BO {
          |        store[${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase}] = ${model.entityRootItem.itemName.camelCase}
          |        return ${model.entityRootItem.itemName.camelCase}
          |    }
          |
          |    override fun deleteById(${model.idAttribute.attributeName.camelCase}: String) {
          |        store.remove(${model.idAttribute.attributeName.camelCase})
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEntityModel): String {
      return "senegai/server/persistence/${model.entityName.lowerCase}/InMemory${model.entityName.pascalCase}Repository.kt"
    }
}