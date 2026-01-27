package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.SetValue
import senegai.codegen.schema.EnumType

@Builder
@ExpectedFromSuperiorBuilder(clazz = EnumType::class, alias = "enum")
interface EnumBuilder: senegai.codegen.builders.EnumDsl {

    @BuilderMethod
    override fun enumValue(
        @SetValue(alias = "enum", clazzProperty = "enumValues")
        name: String
    )
}
