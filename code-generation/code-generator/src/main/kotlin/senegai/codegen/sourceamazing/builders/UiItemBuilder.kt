package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedClazzModelFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.InjectBuilder
import senegai.model.builders.UiDisplayAttributesDsl
import senegai.model.builders.UiEditorForNestedItemDsl
import senegai.model.builders.UiItemDsl
import senegai.model.schema.UiItem

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = UiItem::class, alias = "uiItem")
interface UiItemBuilder: UiItemDsl {

    @BuilderMethod
    fun displayAttributesInternal(
        @InjectBuilder builder: UiItemDisplayAttributesBuilder.() -> Unit
    )

    override fun displayAttributes(builder: UiDisplayAttributesDsl.() -> Unit) {
        displayAttributesInternal(builder)
    }

    @BuilderMethod
    fun configureEditorForNestedItemDefaultInternal(
        @InjectBuilder builder: UiItemDefaultNestedItemEditorBuilder.() -> Unit
    )

    override fun configureEditorForNestedItemDefault(builder: UiEditorForNestedItemDsl.() -> Unit) {
        configureEditorForNestedItemDefaultInternal(builder)
    }
}
