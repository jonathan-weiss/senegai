package senegai.model.builders

import senegai.model.schema.EnumId
import senegai.model.schema.ItemId

@MainDslMarker
interface RootDsl {
    fun schema(builder: SchemaDsl.() -> Unit)
}

@MainDslMarker
interface SchemaDsl {
    fun enumType(
        enumId: EnumId,
        builder: EnumDsl.() -> Unit,
    )

    fun item(
        itemId: ItemId,
        builder: ItemDsl.() -> Unit,
    )

    /**
     * Declares the UI configuration of the item [itemId] that holds wherever that item is
     * shown, for example `uiItem(itemId = Items.ADDRESS) { displayAttributes { ... } }`.
     *
     * In contrast to [uiEntity], which configures one single editor, this configuration
     * belongs to the item itself and is therefore used by every UI component showing it.
     */
    fun uiItem(
        itemId: ItemId,
        builder: UiItemDsl.() -> Unit,
    )

    /**
     * Declares the frontend shell that bundles all Angular components of one editor
     * around the root item [rootItemId], which has to declare a primary key.
     * The [uiEntityName] names the directory and the component classes.
     */
    fun uiEntity(
        uiEntityName: String,
        rootItemId: ItemId,
        builder: UiEntityDsl.() -> Unit,
    )
}
