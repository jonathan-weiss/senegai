package senegai.codegen.sourceamazing

import org.codeblessing.sourceamazing.builder.api.BuilderApi
import org.codeblessing.sourceamazing.schema.api.SchemaApi
import senegai.codegen.CodegenData.collectData
import senegai.codegen.schema.SchemaData
import senegai.codegen.sourceamazing.builders.RootBuilder

object DefinitionDataCollection {

    fun collectSchemaData(): SchemaData {
        return SchemaApi.withSchema(SchemaData::class) { schemaContext ->
            BuilderApi.withBuilder(
                schemaContext = schemaContext,
                builderClass = RootBuilder::class,
            ) { builder ->
                builder.collectData()
            }
        }
    }
}
