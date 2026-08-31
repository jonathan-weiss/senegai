package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.model.builders.UiEditorDsl
import senegai.model.builders.UiEditorForMainItemDsl
import senegai.model.builders.UiEditorForNestedItemDsl
import senegai.model.schema.ItemId
import senegai.model.schema.UiEntityEditorEntityConfiguration
import senegai.model.schema.UiEntityEditorEntityNestedItemConfiguration
import senegai.model.schema.UiEntityEditorView

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
    fun configureEditorForNestedEntityItemInternal(
        @SetAsValue(alias = "nestedItemConfiguration", clazzProperty = "itemId")
        itemId: ItemId,
        @InjectBuilder builder: UiEditorConfigForNestedEntityItemBuilder.() -> Unit
    )

    override fun configureNestedEntityItem(itemId: ItemId, builder: UiEditorForNestedItemDsl.() -> Unit) {
        configureEditorForNestedEntityItemInternal(itemId, builder)
    }
}
