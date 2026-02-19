package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.codegen.builders.UiBlockEditorDsl
import senegai.codegen.schema.UiEntityEditorColumn
import senegai.codegen.schema.UiItemAttributeBlock
import senegai.codegen.schema.UiSectionBlock
import senegai.codegen.schema.UiTextBlock

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = UiEntityEditorColumn::class, alias = "uiColumn")
interface UiBlockBuilder: UiBlockEditorDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiItemAttributeBlock::class, alias = "uiItemAttributeBlock")
    @SetClazzModelOfAlias(alias = "uiColumn", clazzProperty = "blocks", referencedAlias = "uiItemAttributeBlock")
    override fun attribute(
        @SetAsValue("uiItemAttributeBlock", "attributeName")
        attributeName: String,
    )

    @BuilderMethod
    @NewClazzModel(clazz = UiSectionBlock::class, alias = "uiSectionBlock")
    @SetClazzModelOfAlias(alias = "uiColumn", clazzProperty = "blocks", referencedAlias = "uiSectionBlock")
    override fun section(
        @SetAsValue("uiSectionBlock", "sectionName")
        sectionName: String,
    )

    @BuilderMethod
    @NewClazzModel(clazz = UiTextBlock::class, alias = "uiTextBlock")
    @SetClazzModelOfAlias(alias = "uiColumn", clazzProperty = "blocks", referencedAlias = "uiTextBlock")
    override fun text(
        @SetAsValue("uiTextBlock", "textName")
        text: String,
    )

}
