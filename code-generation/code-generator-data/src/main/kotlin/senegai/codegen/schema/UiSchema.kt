package senegai.codegen.schema

data class UiEntity(
    val entity: Entity,
    val editorView: UiEntityEditorView
)


data class UiEntityEditorView(
    val tabs: List<UiEntityEditorTab>,
)

data class UiEntityEditorTab(
    val tabName: String,
    val columns: List<UiEntityEditorColumn>
)

data class UiEntityEditorColumn(
    val sections: List<UiEntityEditorSection>,
)

data class UiEntityEditorSection(
    val sectionName: String,
    val components: List<UiEntityComponent>
)

sealed interface UiEntityComponent

data class UiEntityAttributeComponent(
    val entityAttributeName: String,
): UiEntityComponent
