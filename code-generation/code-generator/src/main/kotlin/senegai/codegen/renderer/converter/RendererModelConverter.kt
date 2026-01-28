package senegai.codegen.renderer.converter

import senegai.codegen.renderer.model.ItemAttributeModel
import senegai.codegen.renderer.model.ItemModel
import senegai.codegen.renderer.model.ItemsModel
import senegai.codegen.renderer.model.SchemaModel
import senegai.codegen.renderer.model.ui.UiEditorViewModel
import senegai.codegen.renderer.model.ui.UiEntityAttributeModel
import senegai.codegen.renderer.model.ui.UiEntityEditorViewColumnModel
import senegai.codegen.renderer.model.ui.UiEntityEditorViewSectionBlockModel
import senegai.codegen.renderer.model.ui.UiEntityEditorViewSectionModel
import senegai.codegen.renderer.model.ui.UiEntityEditorViewTabModel
import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiEntityViewsModel
import senegai.codegen.renderer.model.ui.UiModel
import senegai.codegen.schema.Item
import senegai.codegen.schema.ItemAttribute
import senegai.codegen.schema.SchemaData
import senegai.codegen.schema.UiEntity
import senegai.codegen.schema.UiEntityAttributeBlock
import senegai.codegen.schema.UiEntityBlock
import senegai.codegen.schema.UiEntityEditorColumn
import senegai.codegen.schema.UiEntityEditorSection
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

    private fun mapUiEntityViewsModel(uiEntity: UiEntity): UiEntityViewsModel {
        val uiEntityModel = UiEntityModel()
        return UiEntityViewsModel(
            uiEntity = uiEntityModel,
            editorView = UiEditorViewModel(
                entity = uiEntityModel,
                tabs = uiEntity.editorView.tabs.map { mapUiEntityTab(uiEntityModel = uiEntityModel, tab = it) },
            )
        )
    }

    private fun mapUiEntityTab(uiEntityModel: UiEntityModel, tab: UiEntityEditorTab): UiEntityEditorViewTabModel {
        return UiEntityEditorViewTabModel(
            entity = uiEntityModel,
            tabName = tab.tabName,
            columns = tab.columns.map { mapUiEntityColumn(uiEntityModel = uiEntityModel, column = it) })
    }

    private fun mapUiEntityColumn(uiEntityModel: UiEntityModel, column: UiEntityEditorColumn): UiEntityEditorViewColumnModel {
        return UiEntityEditorViewColumnModel(
            entity = uiEntityModel,
            sections = column.sections.map { mapUiEntitySection(uiEntityModel = uiEntityModel, section = it) })
    }

    private fun mapUiEntitySection(uiEntityModel: UiEntityModel, section: UiEntityEditorSection): UiEntityEditorViewSectionModel {
        return UiEntityEditorViewSectionModel(
            entity = uiEntityModel,
            sectionName = section.sectionName,
            blocks = section.blocks.map { mapUiEntitySectionBlock(uiEntityModel = uiEntityModel, block = it) },
        )
    }

    private fun mapUiEntitySectionBlock(uiEntityModel: UiEntityModel, block: UiEntityBlock): UiEntityEditorViewSectionBlockModel {
        return when (block) {
            is UiEntityAttributeBlock -> UiEntityAttributeModel(
                entity = uiEntityModel,
                attributeName = block.entityAttributeName,
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
