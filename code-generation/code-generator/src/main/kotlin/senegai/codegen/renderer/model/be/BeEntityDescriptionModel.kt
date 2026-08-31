package senegai.codegen.renderer.model.be

import senegai.codegen.renderer.model.NameCase
import senegai.model.schema.EntityId

data class BeEntityDescriptionModel(
    val entityId: EntityId,
    val entityName: NameCase,
)
