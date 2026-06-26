/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEnumModel

/**
 * Generate the content for the template `EnumExampleDataCreatorRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `AppellatioComisExampleDataCreator.kt`
 * - path: `senegai/server/exampledata/opusmagnum/AppellatioComisExampleDataCreator.kt`
 */
object EnumExampleDataCreatorRenderer : BeEnumRenderer {

    override fun renderTemplate(model: BeEnumModel): String {
        return """
          |package senegai.server.exampledata.${model.entityName.lowerCase}
          |
          |import org.springframework.stereotype.Component
          |import senegai.server.service.${model.entityName.lowerCase}.bo.${model.enumName.pascalCase}
          |
          |/**
          | * Creates example data for the [${model.enumName.pascalCase}] business enum.
          | */
          |@Component
          |class ${model.enumName.pascalCase}ExampleDataCreator {
          |
          |    /** A single representative example value. */
          |    fun create(): ${model.enumName.pascalCase} = ${model.enumName.pascalCase}.${model.enumValues.first().screamingSnakeCase}
          |
          |    /** All enum values as example data. */
          |    fun createList(): List<${model.enumName.pascalCase}> = ${model.enumName.pascalCase}.entries.toList()
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEnumModel): String {
      return "senegai/server/exampledata/${model.entityName.lowerCase}/${model.enumName.pascalCase}ExampleDataCreator.kt"
    }
}