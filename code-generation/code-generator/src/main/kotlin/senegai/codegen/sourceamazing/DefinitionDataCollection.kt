package senegai.codegen.sourceamazing

import org.codeblessing.sourceamazing.builder.api.BuilderApi
import org.codeblessing.sourceamazing.schema.api.SchemaApi
import senegai.codegen.definitions.CodegenData
import senegai.codegen.schema.ItemAttributeData
import senegai.codegen.schema.ItemData
import senegai.codegen.schema.SchemaData
import senegai.codegen.renderer.model.ItemAttribute
import senegai.codegen.renderer.model.ItemModel
import senegai.codegen.renderer.model.ItemsModel
import senegai.codegen.renderer.model.SchemaModel
import senegai.codegen.sourceamazing.builders.RootBuilder

object DefinitionDataCollection {

    fun collectDefinitionData(): SchemaModel {
        val schemaData = collectSchemaData()
        return SchemaModel(
            itemsModel = mapItems(schemaData.items),
        )
    }

    private fun collectSchemaData(): SchemaData {
        return SchemaApi.withSchema(SchemaData::class) { schemaContext ->
            BuilderApi.withBuilder(
                schemaContext = schemaContext,
                builderClass = RootBuilder::class,
            ) { builder ->
                val itemsBuilder = builder.createRootInstance()
                CodegenData.collectItemData(itemsBuilder)
            }
        }
    }

    private fun mapItems(items: List<ItemData>): ItemsModel {
        return ItemsModel(
            allItems = items.map { mapItem(it) },
        )
    }

    private fun mapItem(item: ItemData): ItemModel {
        return ItemModel(
            itemName = item.itemId.itemName,
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
