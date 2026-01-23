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
    val blocks: List<UiEntityBlock>
)

sealed interface UiEntityBlock

data class UiEntityAttributeBlock(
    val entityAttributeName: String,
): UiEntityBlock
