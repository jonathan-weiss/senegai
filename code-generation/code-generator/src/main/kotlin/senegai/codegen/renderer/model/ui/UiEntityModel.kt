package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EnumType

data class UiEntityModel(
    val entityName: NameCase,
    val entityRootItem: UiItemModel,
    val entityItemModels: List<UiItemModel>,
    val entityEnumTypes: List<UiEnumModel>,
) {
    val searchResultAttributes: List<UiItemAttributeModel> = entityRootItem.attributes
    val idAttribute: UiItemAttributeModel = entityRootItem.attributes.first() // TODO make configurable
}
