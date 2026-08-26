package senegai.codegen.renderer.model.be

import senegai.codegen.renderer.model.NameCase

data class BeEntityModel(
    val entityName: NameCase,
    val entityRootItem: BeItemModel,
    /** The attribute of the [entityRootItem] that identifies the entity. */
    val idAttribute: BeAttributeModel,
    val entityItemModels: List<BeItemModel>,
    val entityEnumTypes: List<BeEnumModel>,
)
