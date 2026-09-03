/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemExampleDataPopulatorRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumExampleDataPopulator.kt`
 * - path: `senegai/server/exampledata/silvaoptionum/SilvaOptionumExampleDataPopulator.kt`
 */
object ItemExampleDataPopulatorRenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.exampledata.${model.itemName.lowerCase}
          |
          |import org.springframework.stereotype.Component
          |import senegai.server.exampledata.DataContext
          |import senegai.server.exampledata.ExampleDataCreator
          |import senegai.server.exampledata.bo.${model.itemName.pascalCase}ExampleDataCreator
          |import senegai.server.exampledata.framework.datafaker.FakerHelper
          |import senegai.server.service.bo.${model.itemName.pascalCase}BO
          |import senegai.server.service.${model.itemName.lowerCase}.${model.itemName.pascalCase}Repository
          |
          |/**
          | * Orchestrates the creation of ${model.itemName.pascalCase} example data.
          | *
          | * Builds a list of [${model.itemName.pascalCase}BO] aggregates by delegating to the per-business-object
          | * example data creators and persists the result through the [${model.itemName.pascalCase}Repository] port.
          | */
          |@Component
          |class ${model.itemName.pascalCase}ExampleDataPopulator(
          |    private val ${model.itemName.camelCase}ExampleDataCreator: ${model.itemName.pascalCase}ExampleDataCreator,
          |    private val ${model.itemName.camelCase}Repository: ${model.itemName.pascalCase}Repository,
          |): ExampleDataCreator {
          |
          |    /**
          |     * Creates the example [${model.itemName.pascalCase}BO] aggregates, writes each of them to the
          |     * persistence via the [${model.itemName.pascalCase}Repository] and returns the persisted list.
          |     */
          |    override fun createExampleData(dataContext: DataContext) {
          |        ${model.itemName.camelCase}ExampleDataCreator.createList(dataContext, FakerHelper.itemListRandomSize(dataContext))
          |            .forEach { ${model.itemName.camelCase}Repository.save(it) }
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/exampledata/${model.itemName.lowerCase}/${model.itemName.pascalCase}ExampleDataPopulator.kt"
    }
}