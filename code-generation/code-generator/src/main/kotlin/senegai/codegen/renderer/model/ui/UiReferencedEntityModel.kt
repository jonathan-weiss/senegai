package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.model.schema.EntityId

/**
 * The entity an attribute of an item points to, with everything the templates need to
 * search it, resolve it and render it by its display attributes instead of the bare UUID.
 */
data class UiReferencedEntityModel(
    val entityId: EntityId,
    val entityName: NameCase,
    /** The root item of the referenced entity, i.e. what a reference resolves to. */
    val rootItem: UiItemDescriptionModel,
    /** The attribute of the [rootItem] that holds the referenced UUID. */
    val idAttributeName: NameCase,
)
