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
import senegai.codegen.schema.ItemId
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
                    .map {
                        val entity = schemaData.entities.single { entity -> it.entity.entityId == entity.entityId }
                        val allNestedItemIds = HierarchicalItemSearch.findAllItemNames(entity.item, schemaData.items)
                        mapUiEntityViewsModel(it, allNestedItemIds, schemaData.allUiItemModels())
                    }
            )
        )
    }

    private fun SchemaData.allUiItemModels(): List<UiItemModel> {
        return items.map { item -> mapUiItemModel(item) }
    }

    private fun mapUiItemModel(item: Item): UiItemModel {
        return UiItemModel(
            itemId = item.itemId,
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

    private fun mapUiEntityViewsModel(uiEntity: UiEntity, entityItemModelIds: Set<ItemId>, allUiItemModels: List<UiItemModel>): UiEntityViewsModel {
        val uiEntityModel = UiEntityModel(
            entityRootItem = allUiItemModels.single { it.itemId == uiEntity.entity.item.itemId },
            entityItemModels = allUiItemModels.filter { it.itemId in entityItemModelIds },
        )

        val uiEntityItems =uiEntity.editorView.itemConfiguration.map { itemConfiguration ->
            val itemModel = when (itemConfiguration) {
                is UiEntityEditorEntityConfiguration -> uiEntityModel.entityRootItem
                is UiEntityEditorEntityNestedItemConfiguration -> requireNotNull(uiEntityModel.entityItemModels.firstOrNull { it.itemName === itemConfiguration.itemId.itemName }) {
                    "No item found with item id '${itemConfiguration.itemId.itemName}' within items ${uiEntityModel.entityItemModels.map { it.itemName }}"
                }
            }

            val noTab = itemConfiguration.noTab.map { mapUiEntityColumn(uiEntityModel = uiEntityModel, uiItemModel = itemModel, column = it) }

            val tabs = when (itemConfiguration) {
                is UiEntityEditorEntityConfiguration -> itemConfiguration.tabs.map { mapUiEntityTab(uiEntityModel = uiEntityModel, uiItemModel = itemModel, tab = it) }
                is UiEntityEditorEntityNestedItemConfiguration -> emptyList()
            }

            UiEntityFormViewItemModel(
                entity = uiEntityModel,
                item = itemModel,
                noTab = noTab,
                tabs = tabs,
            )
        }

        return UiEntityViewsModel(
            uiEntity = uiEntityModel,
            formView = UiEntityFormViewModel(
                entity = uiEntityModel,
                entityItems = uiEntityItems,
            )
        )
    }

    private fun mapUiEntityTab(uiEntityModel: UiEntityModel, uiItemModel: UiItemModel, tab: UiEntityEditorTab): UiEntityFormViewTabModel {
        return UiEntityFormViewTabModel(
            entity = uiEntityModel,
            tabName = tab.tabName,
            columns = tab.columns.map { mapUiEntityColumn(uiEntityModel = uiEntityModel, uiItemModel = uiItemModel, column = it) })
    }

    private fun mapUiEntityColumn(uiEntityModel: UiEntityModel, uiItemModel: UiItemModel, column: UiEntityEditorColumn): UiEntityFormViewColumnModel {
        return UiEntityFormViewColumnModel(
            entity = uiEntityModel,
            blocks = column.blocks.map { mapUiEntityBlock(uiEntityModel = uiEntityModel, uiItemModel = uiItemModel, block = it) },
        )
    }

    private fun mapUiEntityBlock(uiEntityModel: UiEntityModel, uiItemModel: UiItemModel, block: UiBlock): UiEntityFormBlockModel {
        return when (block) {
            is UiItemAttributeBlock -> UiEntityFormItemAttributeBlockModel(
                entity = uiEntityModel,
                attributeName = block.attributeName,
                item = uiItemModel,
                type = UiFormAttributeType.NON_NULLABLE_SINGLE_VALUE, // TODO good for the moment
            )
            is UiSectionBlock -> UiEntityFormNamedSectionSplitBlockModel(block.sectionName)
            is UiTextBlock -> UiEntityFormTextBlockModel(block.textName)
        }
    }
}
