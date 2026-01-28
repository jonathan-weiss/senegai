package senegai.codegen.renderer.model.ui

data class UiEntityEditorViewTabModel(
 val entity: UiEntityModel,
 val tabName: String,
 val columns: List<UiEntityEditorViewColumnModel>
)
