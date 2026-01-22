package senegai.codegen.sourceamazing

import org.codeblessing.sourceamazing.builder.api.BuilderApi
import org.codeblessing.sourceamazing.schema.api.SchemaApi
import senegai.codegen.definitions.CodegenData
import senegai.codegen.definitions.CodegenData.collectItemData
import senegai.codegen.renderer.model.ItemAttributeModel
import senegai.codegen.schema.ItemAttribute
import senegai.codegen.schema.Item
import senegai.codegen.schema.SchemaData
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
                builder.collectItemData()
            }
        }
    }

    private fun mapItems(items: List<Item>): ItemsModel {
        return ItemsModel(
            allItems = items.map { mapItem(it) },
        )
    }

    private fun mapItem(item: Item): ItemModel {
        return ItemModel(
            itemName = item.itemName,
            attributes = item.attributes.map { mapItemAttribute(it) },
        )
    }

    private fun mapItemAttribute(itemAttribute: ItemAttribute): ItemAttributeModel {
        return ItemAttributeModel(
            attributeName = itemAttribute.attributeName,
            cardinality = itemAttribute.cardinality,
            type = itemAttribute.type,
        )
    }
}
