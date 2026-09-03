package senegai.codegen.validation

import senegai.model.schema.Item
import senegai.model.schema.SchemaData
import senegai.model.schema.UiEntity
import senegai.model.schema.UiEntityEditorColumn
import senegai.model.schema.UiEntityEditorEntityNestedItemConfiguration
import senegai.model.schema.UiEntityEditorItemConfiguration
import senegai.model.schema.UiEntityEditorRootItemConfiguration
import senegai.model.schema.UiItemAttributeBlock

/**
 * An attribute block of an editor shows only an attribute of the item it is configured
 * for, which is the root item of the ui entity or the nested item of the configuration
 * the block belongs to.
 */
class AttributeBlockAttributeExistenceValidator : SchemaDataAspectValidator {
    override fun validate(schemaData: SchemaData, path: ValidationPath) {
        schemaData.uiEntities.forEachIndexed { entityIndex, uiEntity ->
            val editorViewPath = path
                .child("uiEntities", entityIndex, uiEntity.uiEntityName)
                .child("editorView")

            uiEntity.editorView.itemConfiguration.forEachIndexed { configurationIndex, configuration ->
                val configurationPath = editorViewPath.child(
                    property = "itemConfiguration",
                    index = configurationIndex,
                    name = configuration.configuredItemName(uiEntity),
                )
                val configuredItem = configuration.configuredItem(uiEntity, schemaData, configurationPath)

                validateColumns(configuration.noTab, configuredItem, configurationPath.child("noTab"))

                if (configuration is UiEntityEditorRootItemConfiguration) {
                    configuration.tabs.forEachIndexed { tabIndex, tab ->
                        validateColumns(
                            columns = tab.columns,
                            configuredItem = configuredItem,
                            path = configurationPath.child("tabs", tabIndex, tab.tabTranslationKey),
                        )
                    }
                }
            }
        }
    }

    private fun UiEntityEditorItemConfiguration.configuredItemName(uiEntity: UiEntity): String = when (this) {
        is UiEntityEditorRootItemConfiguration -> uiEntity.rootItem.itemName
        is UiEntityEditorEntityNestedItemConfiguration -> itemId.itemName
    }

    private fun UiEntityEditorItemConfiguration.configuredItem(
        uiEntity: UiEntity,
        schemaData: SchemaData,
        path: ValidationPath,
    ): Item = when (this) {
        is UiEntityEditorRootItemConfiguration -> uiEntity.rootItem
        is UiEntityEditorEntityNestedItemConfiguration -> schemaData.items.firstOrNull { it.itemId == itemId }
            ?: validationError(
                path,
                "The editor is configured for the nested item '${itemId.itemName}', but no such item is " +
                        "declared in the schema. Available are ${schemaData.items.map { it.itemName }}.",
            )
    }

    private fun validateColumns(columns: List<UiEntityEditorColumn>, configuredItem: Item, path: ValidationPath) {
        columns.forEachIndexed { columnIndex, column ->
            val columnPath = path.child("columns", columnIndex)
            column.blocks.forEachIndexed { blockIndex, block ->
                if (block !is UiItemAttributeBlock) return@forEachIndexed
                if (configuredItem.attributes.none { it.attributeName == block.attributeName }) {
                    validationError(
                        columnPath.child("blocks", blockIndex, block.attributeName),
                        "The attribute block shows the attribute '${block.attributeName}', but the item " +
                                "'${configuredItem.itemName}' it is configured for has no such attribute. " +
                                "Available are ${configuredItem.attributes.map { it.attributeName }}.",
                    )
                }
            }
        }
    }
}
