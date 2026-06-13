package senegai.codegen.renderer.model.ui.entityform

import senegai.codegen.renderer.model.ui.BuiltInTypeUiItemAttributeTypeModel
import senegai.codegen.renderer.model.ui.UiItemAttributeModel

data class AttributeAndBuiltInTypeDescriptionModel(
    val attribute: UiItemAttributeModel,
    val type: BuiltInTypeUiItemAttributeTypeModel,
)
