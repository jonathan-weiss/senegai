package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.model.schema.EntityId

data class UiEntityDescriptionModel(
    val entityId: EntityId,
    val entityName: NameCase,
)
