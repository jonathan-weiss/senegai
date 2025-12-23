package senegai.codegen.builders

import senegai.codegen.schema.BuiltInType

interface ItemsBuilder {

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

}

