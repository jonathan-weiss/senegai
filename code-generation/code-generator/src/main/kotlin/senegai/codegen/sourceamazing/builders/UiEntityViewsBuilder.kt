package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedClazzModelFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.InjectBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.NewClazzModel
import org.codeblessing.sourceamazing.builder.api.annotations.SetClazzModelOfAlias
import org.codeblessing.sourceamazing.builder.api.annotations.SetAsValue
import senegai.codegen.builders.UiColumnDsl
import senegai.codegen.builders.UiEditorDsl
import senegai.codegen.builders.UiTabDsl
import senegai.codegen.builders.UiViewsDsl
import senegai.codegen.schema.UiEntity
import senegai.codegen.schema.UiEntityEditorSection
import senegai.codegen.schema.UiEntityEditorTab
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
