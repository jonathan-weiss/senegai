/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEnumModel

/**
 * Generate the content for the template `EnumExampleDataCreatorRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `AppellatioComisExampleDataCreator.kt`
 * - path: `senegai/server/exampledata/bo/AppellatioComisExampleDataCreator.kt`
 */
object EnumExampleDataCreatorRenderer : BeEnumRenderer {

    override fun renderTemplate(model: BeEnumModel): String {
        return """
          |package senegai.server.exampledata.bo
          |
          |import org.springframework.stereotype.Component
          |import senegai.server.exampledata.DataContext
          |import senegai.server.exampledata.framework.datafaker.FakerHelper
          |import senegai.server.exampledata.framework.datagenerator.RandomEnumValueDataGenerator
          |import senegai.server.service.bo.${model.enumName.pascalCase}
          |
          |/**
          | * Creates example data for the [${model.enumName.pascalCase}] business enum.
          | */
          |@Component
          |class ${model.enumName.pascalCase}ExampleDataCreator(
          |    private val randomEnumValueDataGenerator: RandomEnumValueDataGenerator,
          |) {
          |
          |    fun create(dataContext: DataContext): ${model.enumName.pascalCase} =
          |        randomEnumValueDataGenerator.generateData(
          |            dataContext = dataContext,
          |            enumClass = ${model.enumName.pascalCase}::class,
          |        )
          |
          |    fun createList(dataContext: DataContext, size: Int): List<${model.enumName.pascalCase}> =
          |        randomEnumValueDataGenerator.generateDataList(
          |            dataContext = dataContext,
          |            enumClass = ${model.enumName.pascalCase}::class,
          |            size = size,
          |        )
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEnumModel): String {
      return "senegai/server/exampledata/bo/${model.enumName.pascalCase}ExampleDataCreator.kt"
    }
}