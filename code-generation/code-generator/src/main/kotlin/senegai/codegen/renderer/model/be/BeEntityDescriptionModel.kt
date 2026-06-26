package senegai.codegen.renderer.model.be

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.EntityId

data class BeEntityDescriptionModel(
    val entityId: EntityId,
    val entityName: NameCase,
)
