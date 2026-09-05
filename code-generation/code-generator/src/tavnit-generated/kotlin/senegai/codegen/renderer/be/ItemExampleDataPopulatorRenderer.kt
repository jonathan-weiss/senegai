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
          |     * Creates the example [${model.itemName.pascalCase}BO] aggregates and writes each of them to the
          |     * persistence via the [${model.itemName.pascalCase}Repository].
          |     *
          |     * Where the persistence hands out primary keys, an aggregate is stored under a key it hands
          |     * out rather than the one the example data creator generated, so that every example
          |     * aggregate gets a key of its own. An item whose key is supplied by the caller instead
          |     * keeps the generated one.
          |     */
          |    override fun createExampleData(dataContext: DataContext) {
          |        val created = ${model.itemName.camelCase}ExampleDataCreator.createList(dataContext, FakerHelper.itemListRandomSize(dataContext))
          |${ if(model.hasGeneratedPrimaryKey) { """        created.forEach { ${model.itemName.camelCase}Repository.save(it.copy(${model.primaryKeyAttribute.attributeName.camelCase} = ${model.itemName.camelCase}Repository.nextId())) }
              |""" } else { """        created.forEach { ${model.itemName.camelCase}Repository.save(it) }
              |""" } }    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/exampledata/${model.itemName.lowerCase}/${model.itemName.pascalCase}ExampleDataPopulator.kt"
    }
}