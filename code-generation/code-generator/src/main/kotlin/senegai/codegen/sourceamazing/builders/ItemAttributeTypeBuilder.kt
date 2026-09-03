package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.model.builders.ItemAttributeTypeDsl
import senegai.model.schema.BuiltInType
import senegai.model.schema.EnumId
import senegai.model.schema.ExampleDataCategory
import senegai.model.schema.ItemAttribute
import senegai.model.schema.ItemId
import senegai.model.schema.PrimaryKeyType

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = ItemAttribute::class, alias = "itemAttribute")
interface ItemAttributeTypeBuilder: ItemAttributeTypeDsl {

    // **************
    // Primary key
    // **************

    @BuilderMethod
    @SetFixedBooleanValue(
        alias = "itemAttribute",
        clazzProperty = "isPrimaryKey",
        value = true,
        modification = ClazzPropertyModification.REPLACE,
    )
    fun primaryKeyInternal(
        @SetAsValue(alias = "itemAttribute", clazzProperty = "type")
        type: BuiltInType,
    )

    override fun primaryKey(type: PrimaryKeyType) {
        // a primary key is a plain built-in type and has no options at all
        primaryKeyInternal(type = type.builtInType)
    }

    // **************
    // Built-in types
    // **************

    @BuilderMethod
    fun builtInTypeInternal(
        @SetAsValue(alias = "itemAttribute", clazzProperty = "type")
        type: BuiltInType,
        @SetAsValue(alias = "itemAttribute", clazzProperty = "exampleDataCategory")
        @IgnoreNullValue
        exampleDataCategory: ExampleDataCategory?,
    ): ItemAttributeOptionsBuilder

    override fun string(exampleDataCategory: ExampleDataCategory?): ItemAttributeOptionsBuilder =
        builtInTypeInternal(type = BuiltInType.STRING, exampleDataCategory = exampleDataCategory)

    override fun number(exampleDataCategory: ExampleDataCategory?): ItemAttributeOptionsBuilder =
        builtInTypeInternal(type = BuiltInType.NUMBER, exampleDataCategory = exampleDataCategory)

    override fun boolean(exampleDataCategory: ExampleDataCategory?): ItemAttributeOptionsBuilder =
        builtInTypeInternal(type = BuiltInType.BOOLEAN, exampleDataCategory = exampleDataCategory)

    override fun uuid(exampleDataCategory: ExampleDataCategory?): ItemAttributeOptionsBuilder =
        builtInTypeInternal(type = BuiltInType.UUID, exampleDataCategory = exampleDataCategory)

    // **************
    // Enum type
    // **************

    @BuilderMethod
    override fun enumType(
        @SetAsValue(alias = "itemAttribute", clazzProperty = "type")
        enumId: EnumId,
    ): ItemAttributeOptionsBuilder

    // **************
    // Nested item and reference
    // **************

    @BuilderMethod
    override fun nestedItem(
        @SetAsValue(alias = "itemAttribute", clazzProperty = "type")
        itemId: ItemId,
    ): ItemAttributeOptionsBuilder

    /**
     * A reference carries an [ItemId] as its type just like a nested item attribute,
     * therefore `isReference` is what tells the two apart.
     */
    @BuilderMethod
    @SetFixedBooleanValue(
        alias = "itemAttribute",
        clazzProperty = "isReference",
        value = true,
        modification = ClazzPropertyModification.REPLACE,
    )
    override fun reference(
        @SetAsValue(alias = "itemAttribute", clazzProperty = "type")
        itemId: ItemId,
    ): ItemAttributeOptionsBuilder
}
