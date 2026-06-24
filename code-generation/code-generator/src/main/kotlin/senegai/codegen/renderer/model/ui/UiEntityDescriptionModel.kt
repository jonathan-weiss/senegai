package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.EntityId

data class UiEntityDescriptionModel(
    val entityId: EntityId,
    val entityName: NameCase,
)
