package senegai.codegen.sourceamazing.builders

import org.codeblessing.sourceamazing.builder.api.annotations.*
import senegai.model.builders.UiEntityDsl
import senegai.model.builders.EnumDsl
import senegai.model.builders.ItemDsl
import senegai.model.builders.SchemaDsl
import senegai.model.schema.EnumId
import senegai.model.schema.EnumType
import senegai.model.schema.Item
import senegai.model.schema.ItemId
import senegai.model.schema.SchemaData
import senegai.model.schema.UiEntity

@Builder
@ExpectedClazzModelFromSuperiorBuilder(clazz = SchemaData::class, alias = "schema")
interface SchemaBuilder: SchemaDsl {

    // **************
    // Item
    // **************

    @BuilderMethod
    @NewClazzModel(clazz = Item::class, alias = "item")
    @SetClazzModelOfAlias(alias = "schema", clazzProperty = "items", referencedAlias = "item")
    fun createNewItemInternal(
        @SetAsClazzModelId(alias = "item")
        @SetAsValue(alias = "item", clazzProperty = "itemId")
        itemId: ItemId,
        @InjectBuilder builder: ItemBuilder.() -> Unit,
    )

    override fun item(itemId: ItemId, builder: ItemDsl.() -> Unit) {
        // cast from senegai.codegen.builders.XyzBuilder to our XyzBuilder
        createNewItemInternal(itemId, builder)
    }

    // **************
    // Enum
    // **************

    @BuilderMethod
    @NewClazzModel(clazz = EnumType::class, alias = "enum")
    @SetClazzModelOfAlias(alias = "schema", clazzProperty = "enums", referencedAlias = "enum")
    fun createNewEnumTypeInternal(
        @SetAsClazzModelId(alias = "enum")
        @SetAsValue(alias = "enum", clazzProperty = "enumId")
        enumId: EnumId,
        @InjectBuilder builder: EnumBuilder.() -> Unit
    )

    override fun enumType(enumId: EnumId, builder: EnumDsl.() -> Unit) {
        // cast from senegai.codegen.builders.XyzBuilder to our XyzBuilder
        createNewEnumTypeInternal(enumId, builder)
    }


    // **************
    // UI Entity
    // **************

    @BuilderMethod
    @NewClazzModel(clazz = UiEntity::class, alias = "uiEntity")
    @SetClazzModelOfAlias(alias = "schema", clazzProperty = "uiEntities", referencedAlias = "uiEntity")
    fun uiEntityInternal(
        @SetAsClazzModelId(alias = "uiEntity")
        @SetAsValue(alias = "uiEntity", clazzProperty = "uiEntityName")
        uiEntityName: String,
        @SetClazzModelOfId(alias = "uiEntity", clazzProperty = "rootItem")
        rootItemId: ItemId,
        @InjectBuilder builder: UiEntityBuilder.() -> Unit
    )

    override fun uiEntity(uiEntityName: String, rootItemId: ItemId, builder: UiEntityDsl.() -> Unit) {
        uiEntityInternal(uiEntityName, rootItemId, builder)
    }

}
