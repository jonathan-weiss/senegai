package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.codegen.builders.UiBlockEditorDsl
import senegai.codegen.builders.UiEditorForMainItemDsl
import senegai.codegen.builders.UiTabDsl
import senegai.codegen.schema.UiEntityEditorColumn
import senegai.codegen.schema.UiEntityEditorEntityConfiguration
import senegai.codegen.schema.UiEntityEditorTab

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = UiEntityEditorEntityConfiguration::class, alias = "mainItemConfiguration")
interface UiEditorConfigForMainEntityItemBuilder: UiEditorForMainItemDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorTab::class, alias = "uiTab")
    @SetClazzModelOfAlias(alias = "mainItemConfiguration", clazzProperty = "tabs", referencedAlias = "uiTab")
    fun tabInternal(
        @SetAsValue(alias = "uiTab", clazzProperty = "tabName")
        tabName: String,
        @InjectBuilder builder: UiTabBuilder.() -> Unit
    )

    override fun tab(tabName: String, builder: UiTabDsl.() -> Unit) {
        tabInternal(tabName, builder)
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
