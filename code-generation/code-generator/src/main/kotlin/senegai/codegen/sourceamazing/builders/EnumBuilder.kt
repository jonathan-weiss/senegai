package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedAliasFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.SetFacetValue
import senegai.codegen.schema.EnumType

@Builder
@ExpectedAliasFromSuperiorBuilder(concept = EnumType::class, conceptAlias = "enum")
interface EnumBuilder: senegai.codegen.builders.EnumBuilder {

    @BuilderMethod
    override fun enumValue(
        @SetFacetValue("enum", "enumValues")
        name: String
    )
}
