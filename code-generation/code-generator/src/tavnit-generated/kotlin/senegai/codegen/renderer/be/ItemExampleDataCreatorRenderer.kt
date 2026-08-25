/*
 * This file is generated using tavnit.
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
          |import senegai.server.exampledata.DataContext
          |import senegai.server.exampledata.framework.datafaker.FakerHelper
          |import senegai.server.exampledata.framework.datagenerator.RandomBooleanDataGenerator
          |import senegai.server.exampledata.framework.datagenerator.RandomNumberDataGenerator
          |import senegai.server.exampledata.framework.datagenerator.RandomStringDataGenerator
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
              |""" } }${ model.exampleDataGeneratorConfigs.joinToString("") { exampleDataGeneratorConfig ->  """    private val ${exampleDataGeneratorConfig.generatorNamePrefix.camelCase}DataGenerator: ${exampleDataGeneratorConfig.generatorNamePrefix.pascalCase}DataGenerator,
              |""" } }) {
          |
          |    fun create(dataContext: DataContext): ${model.itemName.pascalCase}BO = ${model.itemName.pascalCase}BO(
          |${ model.builtInAttributes.joinToString("") { builtInAttribute ->  """${ if(builtInAttribute.isList) { """        ${builtInAttribute.attributeName.camelCase} = ${builtInAttribute.exampleDataGeneratorConfig.generatorNamePrefix.camelCase}DataGenerator.generateDataList(dataContext, size = FakerHelper.innerListRandomSize(dataContext)),
                  |""" } else { """        ${builtInAttribute.attributeName.camelCase} = ${builtInAttribute.exampleDataGeneratorConfig.generatorNamePrefix.camelCase}DataGenerator.generateData(dataContext),
                  |""" } }""" } }${ model.attributesWithItemType.joinToString("") { itemAttribute ->  """${ if(itemAttribute.isList) { """        ${itemAttribute.attributeName.camelCase} = ${itemAttribute.referencedItem.itemName.camelCase}ExampleDataCreator.createList(dataContext, FakerHelper.innerListRandomSize(dataContext)),
                  |""" } else { """        ${itemAttribute.attributeName.camelCase} = ${itemAttribute.referencedItem.itemName.camelCase}ExampleDataCreator.create(dataContext),
                  |""" } }
              |""" } }${ model.attributesWithEnumType.joinToString("") { enumAttribute ->  """${ if(enumAttribute.isList) { """        ${enumAttribute.attributeName.camelCase} = ${enumAttribute.enum.enumName.camelCase}ExampleDataCreator.createList(dataContext, FakerHelper.innerListRandomSize(dataContext)),
                  |""" } else { """        ${enumAttribute.attributeName.camelCase} = ${enumAttribute.enum.enumName.camelCase}ExampleDataCreator.create(dataContext),
                  |""" } }""" } }    )
          |
          |    fun createList(dataContext: DataContext, size: Int): List<${model.itemName.pascalCase}BO> =
          |        List( size = size) { create(dataContext) }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/exampledata/bo/${model.itemName.pascalCase}ExampleDataCreator.kt"
    }
}