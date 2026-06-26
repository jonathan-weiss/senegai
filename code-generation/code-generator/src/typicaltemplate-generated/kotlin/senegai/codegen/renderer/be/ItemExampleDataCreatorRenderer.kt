/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemExampleDataCreatorRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `SilvaOptionumExampleDataCreator.kt`
 * - path: `senegai/server/exampledata/bo/SilvaOptionumExampleDataCreator.kt`
 */
object ItemExampleDataCreatorRenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.exampledata.bo
          |
          |import org.springframework.stereotype.Component
          |import senegai.server.service.bo.${model.itemName.pascalCase}BO
          |
          |/**
          | * Creates example data for the business object [${model.itemName.pascalCase}BO].
          | *
          | * Delegates the creation of nested objects to the dedicated example data creators of the
          | * respective business objects.
          | */
          |@Component
          |class ${model.itemName.pascalCase}ExampleDataCreator(
          |${ model.directlyNestedItems.joinToString("") { nestedItem ->  """    private val ${nestedItem.itemName.camelCase}ExampleDataCreator: ${nestedItem.itemName.pascalCase}ExampleDataCreator,
              |""" } }${ model.usedEnums.joinToString("") { usedEnum ->  """    private val ${usedEnum.enumName.camelCase}ExampleDataCreator: ${usedEnum.enumName.pascalCase}ExampleDataCreator,
              |""" } }) {
          |
          |    /** A single fully populated example aggregate. */
          |    fun create(): ${model.itemName.pascalCase}BO = ${model.itemName.pascalCase}BO(
          |${ model.builtInAttributes.joinToString("") { builtInAttribute ->  """        ${builtInAttribute.attributeName.camelCase} = ${builtInAttribute.kotlinExampleValue},
              |""" } }${ model.attributesWithItemType.joinToString("") { itemAttribute ->  """        ${itemAttribute.attributeName.camelCase} = ${itemAttribute.referencedItem.itemName.camelCase}ExampleDataCreator.${itemAttribute.exampleDataCreatorCall},
              |""" } }${ model.attributesWithEnumType.joinToString("") { enumAttribute ->  """        ${enumAttribute.attributeName.camelCase} = ${enumAttribute.enum.enumName.camelCase}ExampleDataCreator.${enumAttribute.exampleDataCreatorCall},
              |""" } }    )
          |
          |    /** A list of distinct example aggregates. */
          |    fun createList(): List<${model.itemName.pascalCase}BO> = listOf(
          |        create(),
          |        create(),
          |        create(),
          |    )
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/exampledata/bo/${model.itemName.pascalCase}ExampleDataCreator.kt"
    }
}