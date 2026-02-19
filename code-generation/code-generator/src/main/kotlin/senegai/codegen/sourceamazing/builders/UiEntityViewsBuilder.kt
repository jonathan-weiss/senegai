package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.codegen.builders.UiEditorDsl
import senegai.codegen.builders.UiViewsDsl
import senegai.codegen.schema.UiEntity
import senegai.codegen.schema.UiEntityEditorView

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = UiEntity::class, alias = "uiEntity")
interface UiEntityViewsBuilder: UiViewsDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorView::class, alias = "uiEditor")
    @SetClazzModelOfAlias(alias = "uiEntity", clazzProperty = "editorView", referencedAlias = "uiEditor")
    fun editorInternal(
        @InjectBuilder builder: UiEditorViewBuilder.() -> Unit
    )

    override fun editor(builder: UiEditorDsl.() -> Unit) {
        editorInternal(builder)
    }
}
