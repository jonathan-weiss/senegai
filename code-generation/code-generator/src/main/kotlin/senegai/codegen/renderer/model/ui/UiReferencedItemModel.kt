package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.model.schema.ItemId

/**
 * The item an attribute of another item points to, with everything the templates need to
 * import its reference components and resolve it. Only an item with a primary key can be
 * referenced.
 */
data class UiReferencedItemModel(
    val itemId: ItemId,
    val itemName: NameCase,
    /** The attribute of the referenced item that holds the referenced UUID. */
    val idAttributeName: NameCase,
)
