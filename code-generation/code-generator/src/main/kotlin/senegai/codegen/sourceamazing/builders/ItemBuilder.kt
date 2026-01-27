package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.codegen.schema.*

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = Item::class, alias = "item")
interface ItemBuilder: senegai.codegen.builders.ItemDsl {

    @BuilderMethod
    @NewClazzModel(clazz = ItemAttribute::class, alias = "itemAttribute")
    @SetClazzModelOfAlias(alias = "item", clazzProperty = "attributes", referencedAlias = "itemAttribute")
    @SetFixedEnumValue(alias = "itemAttribute", clazzProperty = "cardinality", value = "EXACTLY_ONE")
    override fun attribute(
        @SetAsValue(alias = "itemAttribute", clazzProperty = "attributeName")
        name: String,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "type")
        type: BuiltInType,
    )

    @BuilderMethod
    @NewClazzModel(clazz = ItemAttribute::class, alias = "itemAttribute")
    @SetClazzModelOfAlias(alias = "item", clazzProperty = "attributes", referencedAlias = "itemAttribute")
    @SetFixedEnumValue(alias = "itemAttribute", clazzProperty = "cardinality", value = "EXACTLY_ONE")
    override fun attribute(
        @SetAsValue(alias = "itemAttribute", clazzProperty = "attributeName")
        name: String,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "type")
        itemId: ItemId,
    )

    @BuilderMethod
    @NewClazzModel(clazz = ItemAttribute::class, alias = "itemAttribute")
    @SetClazzModelOfAlias(alias = "item", clazzProperty = "attributes", referencedAlias = "itemAttribute")
    @SetFixedEnumValue(alias = "itemAttribute", clazzProperty = "cardinality", value = "EXACTLY_ONE")
    override fun attribute(
        @SetAsValue(alias = "itemAttribute", clazzProperty = "attributeName")
        name: String,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "type")
        enumId: EnumId,
    )
}

