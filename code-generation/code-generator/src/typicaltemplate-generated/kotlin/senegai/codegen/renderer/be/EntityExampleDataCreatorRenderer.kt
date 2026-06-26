/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

/**
 * Generate the content for the template `EntityExampleDataCreatorRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `OpusMagnumExampleDataCreator.kt`
 * - path: `senegai/server/exampledata/opusmagnum/OpusMagnumExampleDataCreator.kt`
 */
object EntityExampleDataCreatorRenderer : BeEntityRenderer {

    override fun renderTemplate(model: BeEntityModel): String {
        return """
          |package senegai.server.exampledata.${model.entityName.lowerCase}
          |
          |import org.springframework.stereotype.Component
          |import senegai.server.exampledata.bo.${model.entityRootItem.itemName.pascalCase}ExampleDataCreator
          |import senegai.server.service.${model.entityName.lowerCase}.${model.entityName.pascalCase}Repository
          |import senegai.server.service.bo.${model.entityRootItem.itemName.pascalCase}BO
          |
          |/**
          | * Orchestrates the creation of ${model.entityName.pascalCase} example data.
          | *
          | * Builds a list of [${model.entityRootItem.itemName.pascalCase}BO] aggregates by delegating to the per-business-object
          | * example data creators and persists the result through the [${model.entityName.pascalCase}Repository] port.
          | */
          |@Component
          |class ${model.entityName.pascalCase}ExampleDataCreator(
          |    private val ${model.entityRootItem.itemName.camelCase}ExampleDataCreator: ${model.entityRootItem.itemName.pascalCase}ExampleDataCreator,
          |    private val ${model.entityName.camelCase}Repository: ${model.entityName.pascalCase}Repository,
          |) {
          |
          |    /**
          |     * Creates the example [${model.entityRootItem.itemName.pascalCase}BO] aggregates, writes each of them to the
          |     * persistence via the [${model.entityName.pascalCase}Repository] and returns the persisted list.
          |     */
          |    fun createExampleData(): List<${model.entityRootItem.itemName.pascalCase}BO> =
          |        ${model.entityRootItem.itemName.camelCase}ExampleDataCreator.createList()
          |            .map { ${model.entityName.camelCase}Repository.save(it) }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEntityModel): String {
      return "senegai/server/exampledata/${model.entityName.lowerCase}/${model.entityName.pascalCase}ExampleDataCreator.kt"
    }
}