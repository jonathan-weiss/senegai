package senegai.codegen.sourceamazing

import org.codeblessing.sourceamazing.builder.api.BuilderApi
import org.codeblessing.sourceamazing.schema.api.ConceptIdentifier
import org.codeblessing.sourceamazing.schema.api.SchemaApi
import org.codeblessing.sourceamazing.schema.api.toConceptName
import senegai.codegen.definitions.CodegenData
import senegai.codegen.schema.ItemAttributeData
import senegai.codegen.schema.ItemData
import senegai.codegen.schema.ItemsData
import senegai.codegen.renderer.model.ItemAttribute
import senegai.codegen.renderer.model.ItemModel
import senegai.codegen.renderer.model.ItemsModel
import senegai.codegen.sourceamazing.builders.RootBuilder

object DefinitionDataCollection {

    fun collectDefinitionData(): ItemsModel {
        return mapItems(collectItemsData())
    }

    private fun collectItemsData(): ItemsData {
        return SchemaApi.withSchema(ItemsData::class) { schemaContext ->
            val rootInstanceId = ConceptIdentifier.ofRandom(ItemsModel::class.toConceptName())
            BuilderApi.withBuilder(
                schemaContext = schemaContext,
                builderClass = RootBuilder::class,
            ) { builder ->
                val itemsBuilder = builder.createRootInstance(rootInstanceId)
                CodegenData.collectItemData(itemsBuilder)
            }

            rootInstanceId
        }
    }

    private fun mapItems(items: ItemsData): ItemsModel {
        return ItemsModel(
            allItems = items.items.map { mapItem(it) },
        )
    }

    private fun mapItem(item: ItemData): ItemModel {
        return ItemModel(
            itemName = item.itemName,
            attributes = item.attributes.map { mapItemAttribute(it) },
        )
    }

    private fun mapItemAttribute(itemAttribute: ItemAttributeData): ItemAttribute {
        return ItemAttribute(
            attributeName = itemAttribute.attributeName,
            cardinality = itemAttribute.cardinality,
            type = itemAttribute.type,
        )
    }
}
