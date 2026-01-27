package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.InjectBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.RedeclareAliasForNestedBuilder
import senegai.codegen.schema.SchemaData

@Builder
@ExpectedFromSuperiorBuilder(clazz = SchemaData::class, alias = "root")
interface RootBuilder: senegai.codegen.builders.RootDsl {

    @BuilderMethod
    @RedeclareAliasForNestedBuilder(alias = "root", newAlias = "schema")
    fun createSchemaInternal(
        @InjectBuilder builder: SchemaBuilder.() -> Unit
    )



    override fun schema(
        builder: senegai.codegen.builders.SchemaDsl.() -> Unit
    ) {
        createSchemaInternal(builder)
    }
}
