package senegai.codegen.renderer.model.be

import senegai.codegen.renderer.model.NameCase
import senegai.model.schema.ItemId

/**
 * The item an attribute of another item points to, with everything the templates need to
 * fetch it, e.g. to create example data that references a really existing instance.
 * Only an item with a primary key can be referenced.
 */
data class BeReferencedItemModel(
    val itemId: ItemId,
    val itemName: NameCase,
    /** The attribute of the referenced item that holds the referenced UUID. */
    val idAttributeName: NameCase,
)
