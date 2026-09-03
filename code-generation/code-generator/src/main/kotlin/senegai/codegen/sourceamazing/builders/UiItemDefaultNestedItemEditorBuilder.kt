package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.model.builders.UiBlockEditorDsl
import senegai.model.builders.UiEditorForNestedItemDsl
import senegai.model.schema.UiEntityEditorColumn
import senegai.model.schema.UiItem

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = UiItem::class, alias = "uiItem")
interface UiItemDefaultNestedItemEditorBuilder: UiEditorForNestedItemDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorColumn::class, alias = "uiColumn")
    @SetClazzModelOfAlias(alias = "uiItem", clazzProperty = "defaultNestedItemEditor", referencedAlias = "uiColumn")
    fun columnInternal(
        @InjectBuilder builder: UiBlockBuilder.() -> Unit
    )

    override fun column(builder: UiBlockEditorDsl.() -> Unit) {
        columnInternal(builder)
    }
}
