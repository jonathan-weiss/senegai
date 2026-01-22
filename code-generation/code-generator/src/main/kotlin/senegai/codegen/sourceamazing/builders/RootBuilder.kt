package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedAliasFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.RedeclareAliasForNestedBuilder
import senegai.codegen.schema.SchemaData

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = SchemaData::class, conceptAlias = "root")
interface RootBuilder {

    @BuilderMethod
    @RedeclareAliasForNestedBuilder(conceptAlias = "root", newConceptAlias = "schema")
    fun createRootInstance(): SchemaBuilder
}
