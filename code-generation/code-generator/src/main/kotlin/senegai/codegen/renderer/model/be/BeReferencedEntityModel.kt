package senegai.codegen.renderer.model.be

import senegai.codegen.renderer.model.NameCase
import senegai.model.schema.EntityId

/**
 * The entity an attribute of an item points to, with everything the templates need to
 * fetch it, e.g. to create example data that references a really existing instance.
 */
data class BeReferencedEntityModel(
    val entityId: EntityId,
    val entityName: NameCase,
    /** The root item of the referenced entity, i.e. what a reference resolves to. */
    val rootItem: BeItemDescriptionModel,
    /** The attribute of the [rootItem] that holds the referenced UUID. */
    val idAttributeName: NameCase,
)
