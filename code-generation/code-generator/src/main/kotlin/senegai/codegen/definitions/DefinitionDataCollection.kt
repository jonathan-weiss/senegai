package senegai.codegen.definitions

import org.codeblessing.sourceamazing.builder.api.BuilderApi
import org.codeblessing.sourceamazing.schema.api.SchemaApi
import org.codeblessing.sourceamazing.xmlschema.api.XmlSchemaApi
import senegai.codegen.renderer.model.ItemAttribute
import senegai.codegen.renderer.model.ItemModel
import senegai.codegen.renderer.model.ItemsModel
import senegai.codegen.schema.CodegenSchema
import senegai.codegen.schema.ItemsBuilder
import java.nio.file.Path

object DefinitionDataCollection {

    fun collectDefinitionData(pathToXmlDefinitionFile: Path): ItemsModel {
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
