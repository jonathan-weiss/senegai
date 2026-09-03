package senegai.model.schema

/**
 * A [UiEntity] exists for the frontend only: it is the shell that bundles all
 * Angular components of one editor around a root [Item] with a primary key.
 * The backend has no notion of it.
 */
data class UiEntity(
    val uiEntityName: String,
    val rootItem: Item,
    val editorView: UiEntityEditorView
)

data class UiEntityEditorView(
    val itemConfiguration: List<UiEntityEditorItemConfiguration>,
)

sealed interface UiEntityEditorItemConfiguration {
    val noTab: List<UiEntityEditorColumn>
}

data class UiEntityEditorRootItemConfiguration(
    override val noTab: List<UiEntityEditorColumn>,
    val tabs: List<UiEntityEditorTab>,
): UiEntityEditorItemConfiguration

data class UiEntityEditorEntityNestedItemConfiguration(
    val itemId: ItemId,
    override val noTab: List<UiEntityEditorColumn>,
): UiEntityEditorItemConfiguration

data class UiEntityEditorTab(
    val tabTranslationKey: String,
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
    val sectionTranslationKey: String,
): UiBlock

data class UiTextBlock(
    val textTranslationKey: String,
): UiBlock
