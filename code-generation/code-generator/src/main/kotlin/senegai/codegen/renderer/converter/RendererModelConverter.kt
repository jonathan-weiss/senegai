package senegai.codegen.renderer.converter

import senegai.codegen.renderer.model.SchemaModel
import senegai.codegen.renderer.model.ui.UiItemAttributeModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewColumnModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewTabModel
import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiEntityViewsModel
import senegai.codegen.renderer.model.ui.UiItemModel
import senegai.codegen.renderer.model.ui.UiModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormItemAttributeBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormNamedSectionSplitBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormTextBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiFormAttributeType
import senegai.codegen.schema.Item
import senegai.codegen.schema.ItemAttribute
import senegai.codegen.schema.SchemaData
import senegai.codegen.schema.UiEntity
import senegai.codegen.schema.UiItemAttributeBlock
import senegai.codegen.schema.UiBlock
import senegai.codegen.schema.UiEntityEditorColumn
import senegai.codegen.schema.UiEntityEditorEntityConfiguration
import senegai.codegen.schema.UiEntityEditorEntityNestedItemConfiguration
import senegai.codegen.schema.UiEntityEditorTab
import senegai.codegen.schema.UiSectionBlock
import senegai.codegen.schema.UiTextBlock

object RendererModelConverter {

    fun convertSchemaDataToSchemaModel(schemaData: SchemaData): SchemaModel {
        return SchemaModel(
            uiModel = UiModel(
                uiEntitiesViews = schemaData.uiEntities
                    .map { mapUiEntityViewsModel(it, schemaData.allUiItemModels()) }
            )
        )
    }

    private fun SchemaData.allUiItemModels(): List<UiItemModel> {
        return items.map { item -> mapUiItemModel(item) }
    }

    private fun mapUiItemModel(item: Item): UiItemModel {
        return UiItemModel(
            itemName = item.itemName,
            attributes = item.attributes.map { mapUiItemAttribute(it) }
        )
    }

    private fun mapUiItemAttribute(itemAttribute: ItemAttribute): UiItemAttributeModel {
        return UiItemAttributeModel(
            itemAttribute.attributeName,
            itemAttribute.cardinality,
            itemAttribute.type,
        )
    }

    private fun mapUiEntityViewsModel(uiEntity: UiEntity, allUiItemModels: List<UiItemModel>): UiEntityViewsModel {
        val uiEntityModel = UiEntityModel(
            entityRootItem = mapUiItemModel(uiEntity.entity.item),
            entityItemModels = allUiItemModels, // TODO filter only items that are nested from root item
        )

        val uiEntityItems =uiEntity.editorView.itemConfiguration.map { itemConfiguration ->
            when (itemConfiguration) {
                is UiEntityEditorEntityConfiguration -> UiEntityFormViewItemModel(
                    entity = uiEntityModel,
                    item = uiEntityModel.entityRootItem,
                    noTab = itemConfiguration.noTab.map { mapUiEntityColumn(uiEntityModel = uiEntityModel, column = it) },
                    tabs = itemConfiguration.tabs.map { mapUiEntityTab(uiEntityModel = uiEntityModel, tab = it) },
                )

                is UiEntityEditorEntityNestedItemConfiguration -> UiEntityFormViewItemModel(
                    entity = uiEntityModel,
                    item = requireNotNull(uiEntityModel.entityItemModels.firstOrNull { it.itemName === itemConfiguration.itemId.itemName }) {
                        "No item found with item id '${itemConfiguration.itemId.itemName}' within items ${uiEntityModel.entityItemModels.map { it.itemName }}"
                    },
                    noTab = itemConfiguration.noTab.map { mapUiEntityColumn(uiEntityModel = uiEntityModel, column = it) },
                    tabs = emptyList(),
                )

            }
        }

        return UiEntityViewsModel(
            uiEntity = uiEntityModel,
            formView = UiEntityFormViewModel(
                entity = uiEntityModel,
                entityItems = uiEntityItems,
            )
        )
    }

    private fun mapUiEntityTab(uiEntityModel: UiEntityModel, tab: UiEntityEditorTab): UiEntityFormViewTabModel {
        return UiEntityFormViewTabModel(
            entity = uiEntityModel,
            tabName = tab.tabName,
            columns = tab.columns.map { mapUiEntityColumn(uiEntityModel = uiEntityModel, column = it) })
    }

    private fun mapUiEntityColumn(uiEntityModel: UiEntityModel, column: UiEntityEditorColumn): UiEntityFormViewColumnModel {
        return UiEntityFormViewColumnModel(
            entity = uiEntityModel,
            blocks = column.blocks.map { mapUiEntitySectionBlock(uiEntityModel = uiEntityModel, block = it) },
        )
    }

    private fun mapUiEntitySectionBlock(uiEntityModel: UiEntityModel, block: UiBlock): UiEntityFormBlockModel {
        return when (block) {
            is UiItemAttributeBlock -> UiEntityFormItemAttributeBlockModel(
                entity = uiEntityModel,
                attributeName = block.attributeName,
                item = uiEntityModel.entityRootItem, // TODO only the root item is good enough for the moment
                type = UiFormAttributeType.NON_NULLABLE_SINGLE_VALUE, // TODO good for the moment
            )
            is UiSectionBlock -> UiEntityFormNamedSectionSplitBlockModel(block.sectionName)
            is UiTextBlock -> UiEntityFormTextBlockModel(block.textName)
        }
    }
}
