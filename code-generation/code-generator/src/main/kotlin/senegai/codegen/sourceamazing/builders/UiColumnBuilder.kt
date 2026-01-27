package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedClazzModelFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.InjectBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.NewClazzModel
import org.codeblessing.sourceamazing.builder.api.annotations.SetClazzModelOfAlias
import org.codeblessing.sourceamazing.builder.api.annotations.SetAsValue
import senegai.codegen.builders.UiColumnDsl
import senegai.codegen.builders.UiSectionDsl
import senegai.codegen.schema.UiEntityEditorColumn
import senegai.codegen.schema.UiEntityEditorSection

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = UiEntityEditorColumn::class, alias = "uiColumn")
interface UiColumnBuilder: UiColumnDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorSection::class, alias = "uiSection")
    @SetClazzModelOfAlias(alias = "uiColumn", clazzProperty = "sections", referencedAlias = "uiSection")
    fun sectionInternal(
        @SetAsValue(alias = "uiSection", clazzProperty = "sectionName")
        sectionName: String,
        @InjectBuilder builder: UiSectionBuilder.() -> Unit
    )

    override fun section(sectionName: String, builder: UiSectionDsl.() -> Unit) {
        sectionInternal(sectionName, builder)
    }
}
