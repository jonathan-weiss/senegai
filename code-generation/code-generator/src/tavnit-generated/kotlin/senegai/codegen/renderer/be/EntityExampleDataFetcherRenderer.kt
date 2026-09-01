/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEntityModel

/**
 * Generate the content for the template `EntityExampleDataFetcherRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `OpusMagnumExampleDataFetcher.kt`
 * - path: `senegai/server/exampledata/opusmagnum/OpusMagnumExampleDataFetcher.kt`
 */
object EntityExampleDataFetcherRenderer : BeEntityRenderer {

    override fun renderTemplate(model: BeEntityModel): String {
        return """
          |package senegai.server.exampledata.${model.entityName.lowerCase}
          |
          |import org.springframework.stereotype.Component
          |import senegai.server.exampledata.DataContext
          |import senegai.server.exampledata.framework.datafaker.FakerHelper
          |import senegai.server.service.bo.${model.entityRootItem.itemName.pascalCase}BO
          |import senegai.server.service.${model.entityName.lowerCase}.${model.entityName.pascalCase}Repository
          |import java.util.UUID
          |
          |/**
          | * Fetches already persisted ${model.entityName.pascalCase} example data so that other example data creators can
          | * reference it.
          | *
          | * Unlike the example data creators, this does not create anything: it reads the
          | * [${model.entityRootItem.itemName.pascalCase}BO] instances that the [${model.entityName.pascalCase}ExampleDataCreator] has written before,
          | * so that a reference is always a valid one. It therefore only returns something once
          | * ${model.entityName.pascalCase} example data has been created.
          | */
          |@Component
          |class ${model.entityName.pascalCase}ExampleDataFetcher(
          |    private val ${model.entityName.camelCase}Repository: ${model.entityName.pascalCase}Repository,
          |) {
          |
          |    /**
          |     * A random subset of the ${model.idAttribute.attributeName.camelCase} of the existing [${model.entityRootItem.itemName.pascalCase}BO] instances, or an
          |     * empty list if none exist yet.
          |     */
          |    fun fetchRandomKeysList(dataContext: DataContext): List<UUID> =
          |        FakerHelper.manyOfRandom(
          |            dataContext = dataContext,
          |            array = ${model.entityName.camelCase}Repository.findAll().toTypedArray(),
          |            size = FakerHelper.referenceListRandomSize(dataContext),
          |        ).map { it.${model.idAttribute.attributeName.camelCase} }
          |
          |    /**
          |     * The ${model.idAttribute.attributeName.camelCase} of one random existing [${model.entityRootItem.itemName.pascalCase}BO].
          |     *
          |     * A mandatory reference needs a value even where nothing can be referenced yet, for example
          |     * where an entity references itself or where the referenced entity has not created its
          |     * example data yet. Such a reference gets [UNRESOLVABLE_KEY], which resolves to nothing and
          |     * is therefore shown with the fallback of the display attributes.
          |     */
          |    fun fetchRandomKey(dataContext: DataContext): UUID {
          |        val ${model.entityRootItem.itemName.camelCase}List = ${model.entityName.camelCase}Repository.findAll()
          |        if (${model.entityRootItem.itemName.camelCase}List.isEmpty()) {
          |            return UNRESOLVABLE_KEY
          |        }
          |        return FakerHelper.oneRandomOf(
          |            dataContext = dataContext,
          |            array = ${model.entityRootItem.itemName.camelCase}List.toTypedArray(),
          |        ).${model.idAttribute.attributeName.camelCase}
          |    }
          |
          |    private companion object {
          |        /** The nil UUID, which no entity is ever identified by. */
          |        private val UNRESOLVABLE_KEY: UUID = UUID(0L, 0L)
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeEntityModel): String {
      return "senegai/server/exampledata/${model.entityName.lowerCase}/${model.entityName.pascalCase}ExampleDataFetcher.kt"
    }
}