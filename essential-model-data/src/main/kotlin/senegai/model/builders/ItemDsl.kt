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
    /**
     * Declares the attribute of this item that identifies it (like a primary key in the
     * database), for example `primaryKey(attributeName = "contactId")`.
     *
     * The attribute has to be declared on this item as well and is ALWAYS of type
     * `BuiltInType.UUID`. Only an item with a primary key can be searched and referenced,
     * and only for such an item the whole REST/service/persistence stack is generated.
     */
    fun primaryKey(attributeName: String)

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

    /**
     * Declares an attribute that references the item [itemId], for example
     * `reference(name = "myReferenceToAddress", itemId = Items.ADDRESS)`.
     *
     * In contrast to `attribute(name, itemId)` (which nests the item instance itself),
     * such an attribute stores only the identifying attribute of the referenced item,
     * which is always a `BuiltInType.UUID`. The referenced item therefore has to declare
     * a [primaryKey].
     */
    fun reference(
        name: String,
        itemId: ItemId,
        nullable: Boolean = false,
        multiple: Boolean = false,
        customValidation: Boolean = false,
    )
}
