package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.model.builders.UiBlockEditorDsl
import senegai.model.builders.UiEditorForNestedItemDsl
import senegai.model.schema.UiEntityEditorColumn
import senegai.model.schema.UiEntityEditorEntityNestedItemConfiguration

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = UiEntityEditorEntityNestedItemConfiguration::class, alias = "nestedItemConfiguration")
interface UiEditorConfigForNestedEntityItemBuilder: UiEditorForNestedItemDsl {

    @BuilderMethod
    @NewClazzModel(clazz = UiEntityEditorColumn::class, alias = "uiColumn")
    @SetClazzModelOfAlias(alias = "nestedItemConfiguration", clazzProperty = "noTab", referencedAlias = "uiColumn")
    fun columnInternal(
        @InjectBuilder builder: UiBlockBuilder.() -> Unit
    )

    override fun column(builder: UiBlockEditorDsl.() -> Unit) {
        columnInternal(builder)
    }
}
