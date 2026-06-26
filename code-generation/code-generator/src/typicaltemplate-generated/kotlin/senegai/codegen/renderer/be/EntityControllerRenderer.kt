/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

/**
 * Generate the content for the template `EntityControllerRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `OpusMagnumController.kt`
 * - path: `senegai/server/restapi/opusmagnum/OpusMagnumController.kt`
 */
object EntityControllerRenderer : BeEntityRenderer {

    override fun renderTemplate(model: BeEntityModel): String {
        return """
          |package senegai.server.restapi.${model.entityName.lowerCase}
          |
          |import org.springframework.web.bind.annotation.*
          |import senegai.server.restapi.wto.${model.entityRootItem.itemName.pascalCase}WTO
          |import senegai.server.restapi.wto.mapper.${model.entityRootItem.itemName.pascalCase}Mapper
          |import senegai.server.restapi.wto.mapper.${model.entityRootItem.itemName.pascalCase}Mapper.toBo
          |import senegai.server.restapi.wto.mapper.${model.entityRootItem.itemName.pascalCase}Mapper.toWto
          |import senegai.server.service.${model.entityName.lowerCase}.${model.entityName.pascalCase}Service
          |
          |/**
          | * REST endpoints of the ${model.entityName.pascalCase} business context. Served under `/api/${model.entityName.kebabCase}`
          | * (the `/api` prefix is added by `WebConfig`) and consumed by the Angular
          | * `${model.entityName.pascalCase}Service`.
          | *
          | * The controller speaks WTOs to the outside world, maps them to BOs via
          | * [${model.entityRootItem.itemName.pascalCase}Mapper] and always calls the [${model.entityName.pascalCase}Service] with the whole
          | * `${model.entityRootItem.itemName.pascalCase}` aggregate.
          | */
          |@RestController
          |@RequestMapping("/${model.entityName.kebabCase}")
          |class ${model.entityName.pascalCase}Controller(
          |    private val ${model.entityName.camelCase}Service: ${model.entityName.pascalCase}Service,
          |) {
          |
          |    @GetMapping
          |    fun get${model.entityRootItem.itemName.pascalCase}List(): List<${model.entityRootItem.itemName.pascalCase}WTO> =
          |        ${model.entityName.camelCase}Service.get${model.entityRootItem.itemName.pascalCase}List().map { it.toWto() }
          |
          |    @GetMapping("/{${model.idAttribute.attributeName.camelCase}}")
          |    fun get${model.entityRootItem.itemName.pascalCase}ById(@PathVariable ${model.idAttribute.attributeName.camelCase}: String): ${model.entityRootItem.itemName.pascalCase}WTO? =
          |        ${model.entityName.camelCase}Service.get${model.entityRootItem.itemName.pascalCase}ById(${model.idAttribute.attributeName.camelCase})?.toWto()
          |
          |    @PostMapping
          |    fun create${model.entityRootItem.itemName.pascalCase}(@RequestBody ${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): ${model.entityRootItem.itemName.pascalCase}WTO {
          |        val created = ${model.entityName.camelCase}Service.create${model.entityRootItem.itemName.pascalCase}(${model.entityRootItem.itemName.camelCase}.toBo())
          |        return created.toWto()
          |    }
          |
          |    @PutMapping("/{${model.idAttribute.attributeName.camelCase}}")
          |    fun update${model.entityRootItem.itemName.pascalCase}(
          |        @PathVariable ${model.idAttribute.attributeName.camelCase}: String,
          |        @RequestBody ${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO,
          |    ): ${model.entityRootItem.itemName.pascalCase}WTO {
          |        val toUpdate = ${model.entityRootItem.itemName.camelCase}.toBo().copy(${model.idAttribute.attributeName.camelCase} = ${model.idAttribute.attributeName.camelCase})
          |        return ${model.entityName.camelCase}Service.update${model.entityRootItem.itemName.pascalCase}(toUpdate).toWto()
          |    }
          |
          |    @DeleteMapping("/{${model.idAttribute.attributeName.camelCase}}")
          |    fun delete${model.entityRootItem.itemName.pascalCase}(@PathVariable ${model.idAttribute.attributeName.camelCase}: String) =
          |        ${model.entityName.camelCase}Service.delete${model.entityRootItem.itemName.pascalCase}(${model.idAttribute.attributeName.camelCase})
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEntityModel): String {
      return "senegai/server/restapi/${model.entityName.lowerCase}/${model.entityName.pascalCase}Controller.kt"
    }
}