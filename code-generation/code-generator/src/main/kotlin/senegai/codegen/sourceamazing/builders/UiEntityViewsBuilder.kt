package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.InjectBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.NewClazzModel
import org.codeblessing.sourceamazing.builder.api.annotations.LinkClazzModel
import org.codeblessing.sourceamazing.builder.api.annotations.SetValue
import senegai.codegen.builders.UiColumnDsl
import senegai.codegen.builders.UiEditorDsl
import senegai.codegen.builders.UiTabDsl
import senegai.codegen.builders.UiViewsDsl
import senegai.codegen.schema.UiEntity
import senegai.codegen.schema.UiEntityEditorSection
import senegai.codegen.schema.UiEntityEditorTab
import senegai.codegen.schema.UiEntityEditorView

@Builder
@ExpectedFromSuperiorBuilder(clazz = UiEntity::class, alias = "uiEntity")
interface UiEntityViewsBuilder: UiViewsDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorView::class, alias = "uiEditor")
    @LinkClazzModel(alias = "uiEntity", clazzProperty = "editorView", referencedAlias = "uiEditor")
    fun editorInternal(
        @InjectBuilder builder: UiEditorViewBuilder.() -> Unit
    )

    override fun editor(builder: UiEditorDsl.() -> Unit) {
        editorInternal(builder)
    }
}
