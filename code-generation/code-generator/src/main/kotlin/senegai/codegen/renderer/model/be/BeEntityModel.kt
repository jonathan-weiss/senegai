package senegai.codegen.renderer.model.be

import senegai.codegen.renderer.model.NameCase

data class BeEntityModel(
    val entityName: NameCase,
    val entityRootItem: BeItemModel,
    val entityItemModels: List<BeItemModel>,
    val entityEnumTypes: List<BeEnumModel>,
) {
    val idAttribute: BeAttributeModel = entityRootItem.attributes.first() // TODO make configurable
}
