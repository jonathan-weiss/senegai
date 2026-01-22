package senegai.codegen.builders

import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EntityId
import senegai.codegen.schema.EnumId
import senegai.codegen.schema.ItemId

interface SchemaBuilder {

    fun createNewEntity(
        entityId: EntityId,
        itemId: ItemId,
    )

    fun createNewEnumType(
        enumId: EnumId,
        builder: EnumBuilder.() -> Unit,
    )

    fun createNewItem(
        itemId: ItemId,
        builder: ItemBuilder.() -> Unit,
    )
}

interface EnumBuilder {

    fun enumValue(
        name: String,
    )
}

interface ItemBuilder {

    fun attribute(
        name: String,
        type: BuiltInType
    )

    fun attribute(
        name: String,
        itemId: ItemId,
    )

    fun attribute(
        name: String,
        enumId: EnumId,
    )
}

