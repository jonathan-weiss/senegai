package senegai.codegen.renderer.converter

import senegai.codegen.renderer.model.ItemAttributeModel
import senegai.codegen.renderer.model.ItemModel
import senegai.codegen.renderer.model.ItemsModel
import senegai.codegen.renderer.model.SchemaModel
import senegai.codegen.renderer.model.ui.UiEntityAttributeModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewColumnModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormItemAwareBlockModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewTabModel
import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiEntityViewsModel
import senegai.codegen.renderer.model.ui.UiItemModel
import senegai.codegen.renderer.model.ui.UiModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormItemAttributeBlockModel
import senegai.codegen.schema.Item
import senegai.codegen.schema.ItemAttribute
import senegai.codegen.schema.SchemaData
import senegai.codegen.schema.UiEntity
import senegai.codegen.schema.UiEntityAttributeBlock
import senegai.codegen.schema.UiEntityBlock
import senegai.codegen.schema.UiEntityEditorColumn
import senegai.codegen.schema.UiEntityEditorTab

object RendererModelConverter {

    fun convertSchemaDataToSchemaModel(schemaData: SchemaData): SchemaModel {
        return SchemaModel(
            itemsModel = mapItems(schemaData.items),
            uiModel = UiModel(
                uiEntitiesViews = schemaData.uiEntities.map { mapUiEntityViewsModel(it) }
            )
        )
    }

    private fun mapUiItemModel(entity: UiEntity, item: Item): UiItemModel {
        return UiItemModel(
            itemName = item.itemName,
            attributes = emptyList()
        )
        // TODO here we have a circular dependency when creating UiEntityAttributeModel
        // item.attributes.map { UiEntityAttributeModel(entity, item, it.attributeName) },
    }

    private fun mapUiEntityViewsModel(uiEntity: UiEntity): UiEntityViewsModel {
        val uiEntityModel = UiEntityModel(
            rootItemModel = mapUiItemModel(uiEntity, uiEntity.entity.item)
        )
        return UiEntityViewsModel(
            uiEntity = uiEntityModel,
            formView = UiEntityFormViewModel(
                entity = uiEntityModel,
                essentialBlocks = emptyList(),
                tabs = uiEntity.editorView.tabs.map { mapUiEntityTab(uiEntityModel = uiEntityModel, tab = it) },
            )
        )
    }

    private fun mapUiEntityTab(uiEntityModel: UiEntityModel, tab: UiEntityEditorTab): UiEntityFormViewTabModel {
        return UiEntityFormViewTabModel(
            entity = uiEntityModel,
            tabName = tab.tabName,
            noColumnBlocks = emptyList(),
            columns = tab.columns.map { mapUiEntityColumn(uiEntityModel = uiEntityModel, column = it) })
    }

    private fun mapUiEntityColumn(uiEntityModel: UiEntityModel, column: UiEntityEditorColumn): UiEntityFormViewColumnModel {
        return UiEntityFormViewColumnModel(
            entity = uiEntityModel,
            blocks = column.sections
                .flatMap { section -> section.blocks}
                .map { mapUiEntitySectionBlock(uiEntityModel = uiEntityModel, block = it) },
        )
    }

    private fun mapUiEntitySectionBlock(uiEntityModel: UiEntityModel, block: UiEntityBlock): UiEntityFormItemAwareBlockModel {
        return when (block) {
            is UiEntityAttributeBlock -> UiEntityFormItemAttributeBlockModel(
                entity = uiEntityModel,
                attributeName = block.entityAttributeName,
                item = uiEntityModel.rootItemModel, // TODO only the root item is good enough for the moment
            )
        }
    }

    private fun mapItems(items: List<Item>): ItemsModel {
        return ItemsModel(
            allItems = items.map { mapItem(it) },
        )
    }

    private fun mapItem(item: Item): ItemModel {
        return ItemModel(
            itemName = item.itemName,
            attributes = item.attributes.map { mapItemAttribute(it) },
        )
    }

    private fun mapItemAttribute(itemAttribute: ItemAttribute): ItemAttributeModel {
        return ItemAttributeModel(
            attributeName = itemAttribute.attributeName,
            cardinality = itemAttribute.cardinality,
            type = itemAttribute.type,
        )
    }
}
