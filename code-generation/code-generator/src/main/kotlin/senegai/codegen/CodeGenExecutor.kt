package senegai.codegen

import senegai.codegen.renderer.converter.RendererModelConverter
import senegai.codegen.sourceamazing.DefinitionDataCollection
import senegai.codegen.renderer.Rendering
import senegai.codegen.renderer.model.SchemaModel
import senegai.codegen.schema.SchemaData
import java.nio.file.Paths
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if(args.size != 1) {
        println("Wrong arguments!")
        println("Use <path to generated angular files>")
        exitProcess(1)
    }

    val pathToGeneratedAngularFiles = Paths.get(args[0])

    val schemaModel = convertToSchemaModel(fetchSchemaData())
    Rendering.renderClientFiles(pathToGeneratedAngularFiles, schemaModel.itemsModel)
}

internal fun fetchSchemaData(): SchemaData {
    val schemaData = DefinitionDataCollection.collectSchemaData()
    println("SchemaData: $schemaData")
    return schemaData
}

internal fun convertToSchemaModel(schemaData: SchemaData): SchemaModel {
    val schemaModel = RendererModelConverter.convertSchemaDataToSchemaModel(schemaData)
    println("SchemaModel: $schemaModel")
    return schemaModel
}
