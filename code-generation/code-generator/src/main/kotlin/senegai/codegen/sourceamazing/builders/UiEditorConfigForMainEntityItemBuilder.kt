package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.model.builders.UiBlockEditorDsl
import senegai.model.builders.UiEditorForMainItemDsl
import senegai.model.builders.UiTabDsl
import senegai.model.schema.UiEntityEditorColumn
import senegai.model.schema.UiEntityEditorRootItemConfiguration
import senegai.model.schema.UiEntityEditorTab

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = UiEntityEditorRootItemConfiguration::class, alias = "mainItemConfiguration")
interface UiEditorConfigForMainEntityItemBuilder: UiEditorForMainItemDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorTab::class, alias = "uiTab")
    @SetClazzModelOfAlias(alias = "mainItemConfiguration", clazzProperty = "tabs", referencedAlias = "uiTab")
    fun tabInternal(
        @SetAsValue(alias = "uiTab", clazzProperty = "tabTranslationKey")
        tabTranslationKey: String,
        @InjectBuilder builder: UiTabBuilder.() -> Unit
    )

    override fun tab(tabTranslationKey: String, builder: UiTabDsl.() -> Unit) {
        tabInternal(tabTranslationKey, builder)
    }

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorColumn::class, alias = "uiColumn")
    @SetClazzModelOfAlias(alias = "mainItemConfiguration", clazzProperty = "noTab", referencedAlias = "uiColumn")
    fun columnInternal(
        @InjectBuilder builder: UiBlockBuilder.() -> Unit
    )

    override fun column(builder: UiBlockEditorDsl.() -> Unit) {
        columnInternal(builder)
    }
}
