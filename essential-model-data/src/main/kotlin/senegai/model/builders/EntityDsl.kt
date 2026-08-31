package senegai.model.builders

import senegai.model.schema.BuiltInType
import senegai.model.schema.EnumId
import senegai.model.schema.ExampleDataCategory
import senegai.model.schema.ItemId

@MainDslMarker
interface EnumDsl {
    fun enumValue(name: String)
}

@MainDslMarker
interface ItemDsl {
    fun attribute(
        name: String,
        type: BuiltInType,
        nullable: Boolean = false,
        multiple: Boolean = false,
        customValidation: Boolean = false,
        exampleDataCategory: ExampleDataCategory? = null,
    )

    fun attribute(
        name: String,
        itemId: ItemId,
        nullable: Boolean = false,
        multiple: Boolean = false,
        customValidation: Boolean = false,
    )

    fun attribute(
        name: String,
        enumId: EnumId,
        nullable: Boolean = false,
        multiple: Boolean = false,
        customValidation: Boolean = false,
    )
}
