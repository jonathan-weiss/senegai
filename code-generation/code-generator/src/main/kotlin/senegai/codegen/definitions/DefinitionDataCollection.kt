package senegai.codegen.definitions

import org.codeblessing.sourceamazing.builder.api.BuilderApi
import org.codeblessing.sourceamazing.schema.api.SchemaApi
import org.codeblessing.sourceamazing.xmlschema.api.XmlSchemaApi
import senegai.codegen.manual.schema.ItemAttributeData
import senegai.codegen.manual.schema.ItemData
import senegai.codegen.manual.schema.ItemsData
import senegai.codegen.manual.ManualCodegenData
import senegai.codegen.renderer.model.ItemAttribute
import senegai.codegen.renderer.model.ItemModel
import senegai.codegen.renderer.model.ItemsModel
import senegai.codegen.schema.CodegenSchema
import senegai.codegen.schema.ItemsBuilder
import java.nio.file.Path

private const val USE_SOURCE_AMAZING: Boolean = false

object DefinitionDataCollection {

    fun collectDefinitionData(pathToXmlDefinitionFile: Path): ItemsModel {
        return if(USE_SOURCE_AMAZING) {
            collectDefinitionDataWithSourceamazing(pathToXmlDefinitionFile)
        } else {
            mapItems(ManualCodegenData.collectCodegenData())
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
        )
    }

    private fun collectDefinitionDataWithSourceamazing(pathToXmlDefinitionFile: Path): ItemsModel {
        val codegenSchema = SchemaApi.withSchema(CodegenSchema::class) { schemaContext ->
            XmlSchemaApi.createXsdSchemaAndReadXmlFile(schemaContext, pathToXmlDefinitionFile)
            BuilderApi.withBuilder(schemaContext, ItemsBuilder::class) { dataCollector ->
                CodegenData.collectItemData(dataCollector)
            }
        }


        SchemaInformationPrinter.printSchemaInformation(codegenSchema)

        val items = codegenSchema.getItems().map { item ->
            ItemModel(
                itemName = item.getItemName(),
                attributes = item.getItemAttributes().map { ItemAttribute(attributeName = it.getAttributeName()) }
            )
        }
        return ItemsModel(items)
    }

}
