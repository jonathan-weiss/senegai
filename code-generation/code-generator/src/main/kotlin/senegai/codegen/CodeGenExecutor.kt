package senegai.codegen

import senegai.codegen.renderer.BackendRendering
import senegai.codegen.renderer.ClientRendering
import senegai.codegen.renderer.converter.RendererModelConverter
import senegai.codegen.renderer.model.SchemaModel
import senegai.codegen.schema.SchemaData
import senegai.codegen.sourceamazing.DefinitionDataCollection
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val cliArgs = createCommandLineArguments(args) ?: exitProcess(0)

    val schemaModel = convertToSchemaModel(fetchSchemaData())
    ClientRendering.renderClientFiles(
        pathToGeneratedAngularFiles = cliArgs.directoryForAngularGeneratedSource,
        uiModel = schemaModel.uiModel
    )
    BackendRendering.renderBackendFiles(
        pathToGeneratedBackendRestFiles = cliArgs.directoryForRestGeneratedSource,
        pathToGeneratedBackendServiceFiles = cliArgs.directoryForServiceGeneratedSource,
        pathToGeneratedBackendPersistenceFiles = cliArgs.directoryForPersistenceGeneratedSource,
        pathToGeneratedBackendExampleDataFiles = cliArgs.directoryForExampleDataGeneratedSource,
        beModel = schemaModel.beModel,
    )
}

internal fun fetchSchemaData(): SchemaData = DefinitionDataCollection.collectSchemaData()

internal fun convertToSchemaModel(schemaData: SchemaData): SchemaModel = RendererModelConverter.convertSchemaDataToSchemaModel(schemaData)
