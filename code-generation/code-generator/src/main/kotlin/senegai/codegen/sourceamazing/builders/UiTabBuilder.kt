package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.InjectBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.NewClazzModel
import org.codeblessing.sourceamazing.builder.api.annotations.LinkClazzModel
import org.codeblessing.sourceamazing.builder.api.annotations.SetValue
import senegai.codegen.builders.UiColumnDsl
import senegai.codegen.builders.UiTabDsl
import senegai.codegen.schema.UiEntityEditorColumn
import senegai.codegen.schema.UiEntityEditorSection
import senegai.codegen.schema.UiEntityEditorTab

@Builder
@ExpectedFromSuperiorBuilder(clazz = UiEntityEditorTab::class, alias = "uiTab")
interface UiTabBuilder: UiTabDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorColumn::class, alias = "uiColumn")
    @LinkClazzModel(alias = "uiTab", clazzProperty = "columns", referencedAlias = "uiColumn")
    fun columnInternal(
        @InjectBuilder builder: UiColumnBuilder.() -> Unit
    )

    override fun column(builder: UiColumnDsl.() -> Unit) {
        columnInternal(builder)
    }
}
