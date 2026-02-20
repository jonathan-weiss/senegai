package senegai.codegen.builders

import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EnumId
import senegai.codegen.schema.ItemId

@MainDslMarker
interface EnumDsl {

    fun enumValue(
        name: String,
    )
}

@MainDslMarker
interface ItemDsl {

    fun attribute(
        name: String,
        type: BuiltInType,
        nullable: Boolean = false,
    )

    fun attribute(
        name: String,
        itemId: ItemId,
        nullable: Boolean = false,
    )

    fun attribute(
        name: String,
        enumId: EnumId,
        nullable: Boolean = false,
    )
}

