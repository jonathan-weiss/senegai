package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.model.schema.BuiltInType

data class UiEntityModel(
    val entityName: NameCase,
    val entityRootItem: UiItemModel,
    /** The attribute of the [entityRootItem] that identifies the entity. It is always a UUID. */
    val idAttribute: UiAttributeModel,
    val entityItemModels: List<UiItemModel>,
    val entityEnumTypes: List<UiEnumModel>,
) {
    val searchResultAttributes: List<UiAttributeModel> = entityRootItem.attributes

    /**
     * The attributes that identify one instance of this entity for a human reader, shown
     * next to the [idAttribute] wherever a reference to this entity is rendered. A reference
     * is stored as a bare UUID, which tells the user nothing, so every place that shows one
     * resolves it to the whole root item and renders these attributes instead.
     *
     * Derived for now: every single-valued text attribute of the [entityRootItem]. Picking
     * them explicitly in the essential model data is a separate step.
     */
    val displayAttributes: List<UiAttributeModel> = entityRootItem.attributes
        .filterIsInstance<BuiltInTypeUiAttributeModel>()
        .filter { !it.isEntityReference && !it.isList && it.builtInType == BuiltInType.STRING }
}
