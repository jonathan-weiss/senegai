package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.InjectBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.NewClazzModel
import org.codeblessing.sourceamazing.builder.api.annotations.LinkClazzModel
import org.codeblessing.sourceamazing.builder.api.annotations.SetValue
import senegai.codegen.builders.UiColumnDsl
import senegai.codegen.builders.UiSectionDsl
import senegai.codegen.schema.UiEntityEditorColumn
import senegai.codegen.schema.UiEntityEditorSection

@Builder
@ExpectedFromSuperiorBuilder(clazz = UiEntityEditorColumn::class, alias = "uiColumn")
interface UiColumnBuilder: UiColumnDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorSection::class, alias = "uiSection")
    @LinkClazzModel(alias = "uiColumn", clazzProperty = "sections", referencedAlias = "uiSection")
    fun sectionInternal(
        @SetValue(alias = "uiSection", clazzProperty = "sectionName")
        sectionName: String,
        @InjectBuilder builder: UiSectionBuilder.() -> Unit
    )

    override fun section(sectionName: String, builder: UiSectionDsl.() -> Unit) {
        sectionInternal(sectionName, builder)
    }
}
