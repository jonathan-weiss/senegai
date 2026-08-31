package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.model.builders.ItemDsl
import senegai.model.schema.BuiltInType
import senegai.model.schema.EnumId
import senegai.model.schema.ExampleDataCategory
import senegai.model.schema.Item
import senegai.model.schema.ItemAttribute
import senegai.model.schema.ItemId

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = Item::class, alias = "item")
interface ItemBuilder: ItemDsl {

    @BuilderMethod
    @NewClazzModel(clazz = ItemAttribute::class, alias = "itemAttribute")
    @SetClazzModelOfAlias(alias = "item", clazzProperty = "attributes", referencedAlias = "itemAttribute")
    override fun attribute(
        @SetAsValue(alias = "itemAttribute", clazzProperty = "attributeName")
        name: String,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "type")
        type: BuiltInType,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "isNullable")
        nullable: Boolean,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "isMultiple")
        multiple: Boolean,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "customValidation")
        customValidation: Boolean,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "exampleDataCategory")
        @IgnoreNullValue
        exampleDataCategory: ExampleDataCategory?,
    )

    @BuilderMethod
    @NewClazzModel(clazz = ItemAttribute::class, alias = "itemAttribute")
    @SetClazzModelOfAlias(alias = "item", clazzProperty = "attributes", referencedAlias = "itemAttribute")
    override fun attribute(
        @SetAsValue(alias = "itemAttribute", clazzProperty = "attributeName")
        name: String,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "type")
        itemId: ItemId,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "isNullable")
        nullable: Boolean,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "isMultiple")
        multiple: Boolean,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "customValidation")
        customValidation: Boolean,
    )

    @BuilderMethod
    @NewClazzModel(clazz = ItemAttribute::class, alias = "itemAttribute")
    @SetClazzModelOfAlias(alias = "item", clazzProperty = "attributes", referencedAlias = "itemAttribute")
    override fun attribute(
        @SetAsValue(alias = "itemAttribute", clazzProperty = "attributeName")
        name: String,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "type")
        enumId: EnumId,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "isNullable")
        nullable: Boolean,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "isMultiple")
        multiple: Boolean,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "customValidation")
        customValidation: Boolean,
    )
}

