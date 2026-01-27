package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.codegen.builders.UiEntityDsl
import senegai.codegen.schema.*

@Builder
@ExpectedFromSuperiorBuilder(clazz = SchemaData::class, alias = "schema")
interface SchemaBuilder: senegai.codegen.builders.SchemaDsl {

    // **************
    // Entity
    // **************

    @BuilderMethod
    @NewClazzModel(clazz = Entity::class, alias = "entity")
    @LinkClazzModel(alias = "schema", clazzProperty = "entities", referencedAlias = "entity")
    override fun entity(
        @SetClazzModelId(alias = "entity")
        @SetValue(alias = "entity", clazzProperty = "entityId")
        entityId: EntityId,
        @SetReference(alias = "entity", clazzProperty = "item")
        entityRootItemId: ItemId,
    )

    // **************
    // Item
    // **************

    @BuilderMethod
    @NewClazzModel(clazz = Item::class, alias = "item")
    @LinkClazzModel(alias = "schema", clazzProperty = "items", referencedAlias = "item")
    fun createNewItemInternal(
        @SetClazzModelId(alias = "item")
        @SetValue(alias = "item", clazzProperty = "itemId")
        itemId: ItemId,
        @InjectBuilder builder: ItemBuilder.() -> Unit,
    )

    override fun item(itemId: ItemId, builder: senegai.codegen.builders.ItemDsl.() -> Unit) {
        // cast from senegai.codegen.builders.XyzBuilder to our XyzBuilder
        createNewItemInternal(itemId, builder)
    }

    // **************
    // Enum
    // **************

    @BuilderMethod
    @NewClazzModel(clazz = EnumType::class, alias = "enum")
    @LinkClazzModel(alias = "schema", clazzProperty = "enums", referencedAlias = "enum")
    fun createNewEnumTypeInternal(
        @SetClazzModelId(alias = "enum")
        @SetValue(alias = "enum", clazzProperty = "enumId")
        enumId: EnumId,
        @InjectBuilder builder: EnumBuilder.() -> Unit
    )

    override fun enumType(enumId: EnumId, builder: senegai.codegen.builders.EnumDsl.() -> Unit) {
        // cast from senegai.codegen.builders.XyzBuilder to our XyzBuilder
        createNewEnumTypeInternal(enumId, builder)
    }


    // **************
    // UI Entity
    // **************

    @BuilderMethod
    @NewClazzModel(clazz = UiEntity::class, alias = "uiEntity")
    @LinkClazzModel(alias = "schema", clazzProperty = "uiEntities", referencedAlias = "uiEntity")
    fun uiEntityInternal(
        @SetReference(alias = "uiEntity", clazzProperty = "entity")
        entityId: EntityId,
        @InjectBuilder builder: UiEntityBuilder.() -> Unit
    )

    override fun uiEntity(entityId: EntityId, builder: UiEntityDsl.() -> Unit) {
        uiEntityInternal(entityId, builder)
    }

}
