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
          |    /** A single representative example value. */
          |    fun create(): ${model.enumName.pascalCase} = randomEnumValueDataGenerator.generateData(${model.enumName.pascalCase}::class)
          |
          |    /** All enum values as example data. */
          |    fun createList(): List<${model.enumName.pascalCase}> = randomEnumValueDataGenerator.generateDataList(${model.enumName.pascalCase}::class)
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEnumModel): String {
      return "senegai/server/exampledata/bo/${model.enumName.pascalCase}ExampleDataCreator.kt"
    }
}