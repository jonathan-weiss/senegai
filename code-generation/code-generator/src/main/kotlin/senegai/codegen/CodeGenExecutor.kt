package senegai.codegen

import senegai.codegen.renderer.Rendering
import senegai.codegen.renderer.converter.RendererModelConverter
import senegai.codegen.renderer.model.SchemaModel
import senegai.codegen.schema.SchemaData
import senegai.codegen.sourceamazing.DefinitionDataCollection
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val cliArgs = createCommandLineArguments(args) ?: exitProcess(0)

    val schemaModel = convertToSchemaModel(fetchSchemaData())
    Rendering.renderClientFiles(cliArgs.directoryForAngularGeneratedSource, schemaModel.uiModel)
}

internal fun fetchSchemaData(): SchemaData = DefinitionDataCollection.collectSchemaData()

internal fun convertToSchemaModel(schemaData: SchemaData): SchemaModel = RendererModelConverter.convertSchemaDataToSchemaModel(schemaData)
