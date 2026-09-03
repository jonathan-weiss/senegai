package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.Builder
import org.codeblessing.sourceamazing.builder.api.annotations.BuilderMethod
import org.codeblessing.sourceamazing.builder.api.annotations.ExpectedClazzModelFromSuperiorBuilder
import org.codeblessing.sourceamazing.builder.api.annotations.SetAsValue
import senegai.model.builders.UiDisplayAttributesDsl
import senegai.model.schema.UiItem

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = UiItem::class, alias = "uiItem")
interface UiItemDisplayAttributesBuilder: UiDisplayAttributesDsl {

    @BuilderMethod
    override fun attribute(
        @SetAsValue(alias = "uiItem", clazzProperty = "displayAttributeNames")
        attributeName: String,
    )
}
