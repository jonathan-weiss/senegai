package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.model.builders.UiEditorDsl
import senegai.model.builders.UiEditorForMainItemDsl
import senegai.model.builders.UiEditorForNestedItemDsl
import senegai.model.schema.ItemId
import senegai.model.schema.UiEntityEditorRootItemConfiguration
import senegai.model.schema.UiEntityEditorEntityNestedItemConfiguration
import senegai.model.schema.UiEntityEditorView

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = UiEntityEditorView::class, alias = "uiEditor")
interface UiEditorViewBuilder: UiEditorDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorRootItemConfiguration::class, alias = "mainItemConfiguration")
    @SetClazzModelOfAlias(alias = "uiEditor", clazzProperty = "itemConfiguration", referencedAlias = "mainItemConfiguration")
    fun configureEditorForMainItemInternal(
        @InjectBuilder builder: UiEditorConfigForMainEntityItemBuilder.() -> Unit
    )

    override fun configureEditorForMainItem(builder: UiEditorForMainItemDsl.() -> Unit) {
        configureEditorForMainItemInternal(builder)
    }

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorEntityNestedItemConfiguration::class, alias = "nestedItemConfiguration")
    @SetClazzModelOfAlias(alias = "uiEditor", clazzProperty = "itemConfiguration", referencedAlias = "nestedItemConfiguration")
    fun configureEditorForNestedItemInternal(
        @SetAsValue(alias = "nestedItemConfiguration", clazzProperty = "itemId")
        itemId: ItemId,
        @InjectBuilder builder: UiEditorConfigForNestedEntityItemBuilder.() -> Unit
    )

    override fun configureEditorForNestedItem(itemId: ItemId, builder: UiEditorForNestedItemDsl.() -> Unit) {
        configureEditorForNestedItemInternal(itemId, builder)
    }
}
