package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedClazzModelFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.NewClazzModel
import org.codeblessing.sourceamazing.builder.api.annotations.SetClazzModelOfAlias
import org.codeblessing.sourceamazing.builder.api.annotations.SetAsValue
import senegai.codegen.builders.EnumDsl
import senegai.codegen.builders.UiSectionDsl
import senegai.codegen.schema.EnumType
import senegai.codegen.schema.UiEntityAttributeBlock
import senegai.codegen.schema.UiEntityEditorSection

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = UiEntityEditorSection::class, alias = "uiSection")
interface UiSectionBuilder: UiSectionDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityAttributeBlock::class, alias = "uiEntityAttributeBlock")
    @SetClazzModelOfAlias(alias = "uiSection", clazzProperty = "blocks", referencedAlias = "uiEntityAttributeBlock")
    override fun entityAttribute(
        @SetAsValue("uiEntityAttributeBlock", "entityAttributeName")
        attributeName: String,
    )
}
