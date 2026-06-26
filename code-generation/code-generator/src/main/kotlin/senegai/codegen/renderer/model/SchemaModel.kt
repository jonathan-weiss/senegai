package senegai.codegen.renderer.model

import senegai.codegen.renderer.model.be.BeModel
import senegai.codegen.renderer.model.ui.UiModel

data class SchemaModel(
    val uiModel: UiModel,
    val beModel: BeModel,
)
