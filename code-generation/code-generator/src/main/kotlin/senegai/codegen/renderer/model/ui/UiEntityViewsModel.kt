package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewModel

/**
 * Represents one entity ("author" directory)
 */
class UiEntityViewsModel(
    val uiEntity: UiEntityModel,
    val formView: UiEntityFormViewModel,
    // TODO hier eine Liste aller Formulare,
    // TODO hier eine Liste aller (nested) Komponenten, jeweils mit dem Block-Konzept
) {
    /**
     * To build all model classes as flat list (therefore item names must be unique within the entity)
     */
    val entityItemModels = uiEntity.entityItemModels
}
