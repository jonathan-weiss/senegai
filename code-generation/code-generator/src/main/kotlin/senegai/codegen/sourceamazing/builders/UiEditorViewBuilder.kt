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
import senegai.codegen.schema.UiEntity
import senegai.codegen.schema.UiEntityEditorSection
import senegai.codegen.schema.UiEntityEditorTab
import senegai.codegen.schema.UiEntityEditorView

@Builder
@ExpectedFromSuperiorBuilder(clazz = UiEntityEditorView::class, alias = "uiEditor")
interface UiEditorViewBuilder: UiEditorDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorTab::class, alias = "uiTab")
    @LinkClazzModel(alias = "uiEditor", clazzProperty = "tabs", referencedAlias = "uiTab")
    fun tabInternal(
        @SetValue(alias = "uiTab", clazzProperty = "tabName")
        tabName: String,
        @InjectBuilder builder: UiTabBuilder.() -> Unit
    )

    override fun tab(tabName: String, builder: UiTabDsl.() -> Unit) {
        tabInternal(tabName, builder)
    }
}
