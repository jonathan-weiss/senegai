package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedClazzModelFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.NewClazzModel
import org.codeblessing.sourceamazing.builder.api.annotations.SetAsValue
import org.codeblessing.sourceamazing.builder.api.annotations.SetClazzModelOfAlias
import senegai.model.builders.DbItemDsl
import senegai.model.schema.DbColumn
import senegai.model.schema.DbItem

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = DbItem::class, alias = "dbItem")
interface DbItemBuilder: DbItemDsl {

    @BuilderMethod
    override fun tableName(
        @SetAsValue(alias = "dbItem", clazzProperty = "tableName")
        name: String,
    )

    @BuilderMethod
    @NewClazzModel(clazz = DbColumn::class, alias = "dbColumn")
    @SetClazzModelOfAlias(alias = "dbItem", clazzProperty = "columns", referencedAlias = "dbColumn")
    override fun column(
        @SetAsValue(alias = "dbColumn", clazzProperty = "attributeName")
        attributeName: String,
        @SetAsValue(alias = "dbColumn", clazzProperty = "columnName")
        columnName: String,
    )
}
