package senegai.codegen.renderer.model.ui

data class UiEntityEditorViewSectionModel(
  val entity: UiEntityModel,
  val sectionName: String,
  val blocks: List<UiEntityEditorViewSectionBlockModel>
)
