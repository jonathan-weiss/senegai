package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedClazzModelFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.InjectBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.NewClazzModel
import org.codeblessing.sourceamazing.builder.api.annotations.SetClazzModelOfAlias
import org.codeblessing.sourceamazing.builder.api.annotations.SetAsValue
import senegai.codegen.builders.UiEntityDsl
import senegai.codegen.builders.UiViewsDsl
import senegai.codegen.schema.EntityId
import senegai.codegen.schema.UiEntity
import senegai.codegen.schema.UiEntityEditorView

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = UiEntity::class, alias = "uiEntity")
interface UiEntityBuilder: UiEntityDsl {

    @BuilderMethod
    fun viewsInternal(@InjectBuilder builder: UiEntityViewsBuilder.() -> Unit)

    override fun views(builder: UiViewsDsl.() -> Unit) {
        viewsInternal(builder)
    }
}
