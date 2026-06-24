package senegai.codegen.renderer.model.ui

import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EnumId

sealed interface UiItemAttributeTypeModel

data class BuiltInTypeUiItemAttributeTypeModel(
    val builtInType: BuiltInType,
) : UiItemAttributeTypeModel

data class ItemUiItemAttributeTypeModel(
    val entity: UiEntityDescriptionModel,
    val item: UiItemDescriptionModel,
) : UiItemAttributeTypeModel

data class EnumUiItemAttributeTypeModel(
    val enum: UiEnumModel,
) : UiItemAttributeTypeModel {
    val enumId: EnumId = enum.enumId
}
