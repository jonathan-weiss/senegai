package senegai.codegen.schema

data class UiEntity(
    val entity: Entity,
    val editorView: UiEntityEditorView
)

data class UiEntityEditorView(
    val itemConfiguration: List<UiEntityEditorItemConfiguration>,
)

sealed interface UiEntityEditorItemConfiguration

data class UiEntityEditorEntityConfiguration(
    val noTab: List<UiEntityEditorColumn>,
    val tabs: List<UiEntityEditorTab>,
): UiEntityEditorItemConfiguration

data class UiEntityEditorEntityNestedItemConfiguration(
    val itemId: ItemId,
    val noTab: List<UiEntityEditorColumn>,
): UiEntityEditorItemConfiguration

data class UiEntityEditorTab(
    val tabName: String,
    val columns: List<UiEntityEditorColumn>
)

data class UiEntityEditorColumn(
    val blocks: List<UiBlock>
)

sealed interface UiBlock

data class UiItemAttributeBlock(
    val attributeName: String,
): UiBlock

data class UiSectionBlock(
    val sectionName: String,
): UiBlock

data class UiTextBlock(
    val textName: String,
): UiBlock
