/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

/**
 * Generate the content for the template `EntityServiceRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `OpusMagnumService.kt`
 * - path: `senegai/server/service/opusmagnum/OpusMagnumService.kt`
 */
object EntityServiceRenderer : BeEntityRenderer {

    override fun renderTemplate(model: BeEntityModel): String {
        return """
          |package senegai.server.service.${model.entityName.lowerCase}
          |
          |import org.springframework.stereotype.Service
          |import senegai.server.service.bo.${model.entityRootItem.itemName.pascalCase}BO
          |import java.util.UUID
          |
          |/**
          | * Business service of the ${model.entityName.pascalCase} business context. It is called by the REST layer
          | * with [${model.entityRootItem.itemName.pascalCase}BO] business objects (never WTOs) and delegates persistence to the
          | * [${model.entityName.pascalCase}Repository] port.
          | *
          | * Every operation works on the whole [${model.entityRootItem.itemName.pascalCase}BO] aggregate as a single unit.
          | */
          |@Service
          |class ${model.entityName.pascalCase}Service(
          |    private val ${model.entityName.camelCase}Repository: ${model.entityName.pascalCase}Repository,
          |) {
          |
          |    fun get${model.entityRootItem.itemName.pascalCase}List(): List<${model.entityRootItem.itemName.pascalCase}BO> = ${model.entityName.camelCase}Repository.findAll()
          |
          |    fun get${model.entityRootItem.itemName.pascalCase}ById(${model.idAttribute.attributeName.camelCase}: String): ${model.entityRootItem.itemName.pascalCase}BO? =
          |        ${model.entityName.camelCase}Repository.findById(${model.idAttribute.attributeName.camelCase})
          |
          |    fun create${model.entityRootItem.itemName.pascalCase}(${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}BO): ${model.entityRootItem.itemName.pascalCase}BO {
          |        val toCreate = ${model.entityRootItem.itemName.camelCase}.copy(${model.idAttribute.attributeName.camelCase} = UUID.randomUUID().toString())
          |        return ${model.entityName.camelCase}Repository.save(toCreate)
          |    }
          |
          |    fun update${model.entityRootItem.itemName.pascalCase}(${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}BO): ${model.entityRootItem.itemName.pascalCase}BO =
          |        ${model.entityName.camelCase}Repository.save(${model.entityRootItem.itemName.camelCase})
          |
          |    fun delete${model.entityRootItem.itemName.pascalCase}(${model.idAttribute.attributeName.camelCase}: String) = ${model.entityName.camelCase}Repository.deleteById(${model.idAttribute.attributeName.camelCase})
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEntityModel): String {
      return "senegai/server/service/${model.entityName.lowerCase}/${model.entityName.pascalCase}Service.kt"
    }
}