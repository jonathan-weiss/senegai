package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.codegen.builders.UiEditorDsl
import senegai.codegen.builders.UiEditorForMainItemDsl
import senegai.codegen.builders.UiEditorForNestedItemDsl
import senegai.codegen.schema.ItemId
import senegai.codegen.schema.UiEntityEditorColumn
import senegai.codegen.schema.UiEntityEditorEntityConfiguration
import senegai.codegen.schema.UiEntityEditorEntityNestedItemConfiguration
import senegai.codegen.schema.UiEntityEditorView

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = UiEntityEditorView::class, alias = "uiEditor")
interface UiEditorViewBuilder: UiEditorDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorEntityConfiguration::class, alias = "mainItemConfiguration")
    @SetClazzModelOfAlias(alias = "uiEditor", clazzProperty = "itemConfiguration", referencedAlias = "mainItemConfiguration")
    fun configureEditorForEntityInternal(
        @InjectBuilder builder: UiEditorConfigForMainEntityItemBuilder.() -> Unit
    )

    override fun configureEditorForEntity(builder: UiEditorForMainItemDsl.() -> Unit) {
        configureEditorForEntityInternal(builder)
    }

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorEntityNestedItemConfiguration::class, alias = "nestedItemConfiguration")
    @SetClazzModelOfAlias(alias = "uiEditor", clazzProperty = "itemConfiguration", referencedAlias = "nestedItemConfiguration")
    @NewClazzModel(clazz = UiEntityEditorColumn::class, alias = "singleColumn")
    @SetClazzModelOfAlias(alias = "nestedItemConfiguration", clazzProperty = "noTab", referencedAlias = "singleColumn")
    fun configureEditorForNestedEntityItemInternal(
        @SetAsValue(alias = "nestedItemConfiguration", clazzProperty = "itemId")
        itemId: ItemId,
        @InjectBuilder builder: UiEditorConfigForNestedEntityItemBuilder.() -> Unit
    )

    override fun configureNestedEntityItem(itemId: ItemId, builder: UiEditorForNestedItemDsl.() -> Unit) {
        configureEditorForNestedEntityItemInternal(itemId, builder)
    }
}
