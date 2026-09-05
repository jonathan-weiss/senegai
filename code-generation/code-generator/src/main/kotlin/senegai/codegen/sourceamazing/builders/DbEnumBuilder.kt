package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedClazzModelFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.NewClazzModel
import org.codeblessing.sourceamazing.builder.api.annotations.SetAsValue
import org.codeblessing.sourceamazing.builder.api.annotations.SetClazzModelOfAlias
import senegai.model.builders.DbEnumDsl
import senegai.model.schema.DbEnum
import senegai.model.schema.DbEnumValue

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = DbEnum::class, alias = "dbEnum")
interface DbEnumBuilder: DbEnumDsl {

    @BuilderMethod
    override fun enumTypeName(
        @SetAsValue(alias = "dbEnum", clazzProperty = "enumTypeName")
        name: String,
    )

    @BuilderMethod
    @NewClazzModel(clazz = DbEnumValue::class, alias = "dbEnumValue")
    @SetClazzModelOfAlias(alias = "dbEnum", clazzProperty = "values", referencedAlias = "dbEnumValue")
    override fun enumValue(
        @SetAsValue(alias = "dbEnumValue", clazzProperty = "enumValue")
        name: String,
        @SetAsValue(alias = "dbEnumValue", clazzProperty = "databaseValue")
        databaseValue: String,
    )
}
