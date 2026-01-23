package senegai.codegen.renderer.converter

import senegai.codegen.renderer.model.ItemAttributeModel
import senegai.codegen.renderer.model.ItemModel
import senegai.codegen.renderer.model.ItemsModel
import senegai.codegen.renderer.model.SchemaModel
import senegai.codegen.schema.Item
import senegai.codegen.schema.ItemAttribute
import senegai.codegen.schema.SchemaData

object RendererModelConverter {

    fun convertSchemaDataToSchemaModel(schemaData: SchemaData): SchemaModel {
        return SchemaModel(
            itemsModel = mapItems(schemaData.items),
        )
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
