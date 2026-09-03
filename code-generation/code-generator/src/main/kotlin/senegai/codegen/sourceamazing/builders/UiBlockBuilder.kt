package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.model.builders.UiBlockEditorDsl
import senegai.model.schema.UiEntityEditorColumn
import senegai.model.schema.UiItemAttributeBlock
import senegai.model.schema.UiSectionBlock
import senegai.model.schema.UiTextBlock

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
        @SetAsValue("uiSectionBlock", "sectionTranslationKey")
        sectionTranslationKey: String,
    )

    @BuilderMethod
    @NewClazzModel(clazz = UiTextBlock::class, alias = "uiTextBlock")
    @SetClazzModelOfAlias(alias = "uiColumn", clazzProperty = "blocks", referencedAlias = "uiTextBlock")
    override fun text(
        @SetAsValue("uiTextBlock", "textTranslationKey")
        textTranslationKey: String,
    )

}
