package senegai.codegen.builders

import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.ItemId

interface SchemaBuilder {

    fun createNewItem(
        itemId: ItemId,
        builder: ItemBuilder.() -> Unit,
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
        builder: ItemBuilder.() -> Unit,
    )
}

