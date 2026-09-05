package senegai.codegen

import senegai.codegen.renderer.BackendRendering
import senegai.codegen.renderer.ClientRendering
import senegai.codegen.renderer.DatabaseRendering
import senegai.codegen.renderer.converter.RendererModelConverter
import senegai.codegen.renderer.model.SchemaModel
import senegai.model.schema.SchemaData
import senegai.codegen.sourceamazing.DefinitionDataCollection
import senegai.codegen.validation.SchemaDataValidator
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val cliArgs = createCommandLineArguments(args) ?: exitProcess(0)

    val schemaData = fetchSchemaData()
    validateSchemaData(schemaData)
    val schemaModel = convertToSchemaModel(schemaData)
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

    DatabaseRendering.renderDatabaseFiles(
        pathToGeneratedDatabaseMigrationScripts = cliArgs.directoryForDatabaseMigrationGeneratedSource,
        dbModel = schemaModel.dbModel,
    )
}

internal fun fetchSchemaData(): SchemaData = DefinitionDataCollection.collectSchemaData()

internal fun validateSchemaData(schemaData: SchemaData) = SchemaDataValidator().validate(schemaData)

internal fun convertToSchemaModel(schemaData: SchemaData): SchemaModel = RendererModelConverter.convertSchemaDataToSchemaModel(schemaData)
