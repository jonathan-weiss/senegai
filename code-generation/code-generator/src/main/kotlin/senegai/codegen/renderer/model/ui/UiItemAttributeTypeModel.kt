package senegai.codegen.renderer.model.ui

import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EnumId
import senegai.codegen.schema.EnumType

sealed interface UiItemAttributeTypeModel

data class BuiltInTypeUiItemAttributeTypeModel(
    val builtInType: BuiltInType,
) : UiItemAttributeTypeModel

data class ItemUiItemAttributeTypeModel(
    val item: UiItemDescriptionModel,
) : UiItemAttributeTypeModel

data class EnumUiItemAttributeTypeModel(
    val enumModel: UiEnumModel,
) : UiItemAttributeTypeModel {
    val enumId: EnumId = enumModel.enumId
}
