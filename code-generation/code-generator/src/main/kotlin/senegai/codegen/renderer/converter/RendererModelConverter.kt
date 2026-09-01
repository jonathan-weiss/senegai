package senegai.codegen.renderer.converter

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.renderer.model.SchemaModel
import senegai.codegen.renderer.model.be.BeAttributeModel
import senegai.codegen.renderer.model.be.BeEntityDescriptionModel
import senegai.codegen.renderer.model.be.BeEntityModel
import senegai.codegen.renderer.model.be.BeEnumModel
import senegai.codegen.renderer.model.be.BeExampleDataGeneratorConfig
import senegai.codegen.renderer.model.be.BeItemDescriptionModel
import senegai.codegen.renderer.model.be.BeItemModel
import senegai.codegen.renderer.model.be.BeModel
import senegai.codegen.renderer.model.be.BuiltInTypeBeAttributeModel
import senegai.codegen.renderer.model.be.EntityReferenceBeAttributeModel
import senegai.codegen.renderer.model.be.EnumBeAttributeModel
import senegai.codegen.renderer.model.be.ItemBeIAttributeModel
import senegai.codegen.renderer.model.ui.BuiltInTypeUiAttributeModel
import senegai.codegen.renderer.model.ui.EntityReferenceUiAttributeModel
import senegai.codegen.renderer.model.ui.EnumUiAttributeModel
import senegai.codegen.renderer.model.ui.ItemUiIAttributeModel
import senegai.codegen.renderer.model.ui.UiEntityDescriptionModel
import senegai.codegen.renderer.model.ui.UiAttributeModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewColumnModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewTabModel
import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiEntityViewsModel
import senegai.codegen.renderer.model.ui.UiEnumModel
import senegai.codegen.renderer.model.ui.UiItemDescriptionModel
import senegai.codegen.renderer.model.ui.UiItemModel
import senegai.codegen.renderer.model.ui.UiModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormItemAttributeBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormNamedSectionSplitBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormTextBlockModel
import senegai.model.schema.BuiltInType
import senegai.model.schema.Entity
import senegai.model.schema.EntityId
import senegai.model.schema.EnumId
import senegai.model.schema.EnumType
import senegai.model.schema.ExampleDataCategory
import senegai.model.schema.Item
import senegai.model.schema.ItemAttribute
import senegai.model.schema.ItemId
import senegai.model.schema.SchemaData
import senegai.model.schema.UiEntity
import senegai.model.schema.UiItemAttributeBlock
import senegai.model.schema.UiBlock
import senegai.model.schema.UiEntityEditorColumn
import senegai.model.schema.UiEntityEditorEntityConfiguration
import senegai.model.schema.UiEntityEditorEntityNestedItemConfiguration
import senegai.model.schema.UiEntityEditorTab
import senegai.model.schema.UiSectionBlock
import senegai.model.schema.UiTextBlock

object RendererModelConverter {

    fun convertSchemaDataToSchemaModel(schemaData: SchemaData): SchemaModel {
        return SchemaModel(
            uiModel = UiModel(
                uiEntitiesViews = schemaData.uiEntities
                    .map {
                        val entity = schemaData.entities.single { entity -> it.entity.entityId == entity.entityId }
                        val allNestedItemIds = HierarchicalItemSearch.findAllItemNames(entity.item, schemaData.items)
                        mapUiEntityViewsModel(it, allNestedItemIds, schemaData.allUiItemModels(entity.toUiEntityDescriptionModel()), schemaData.enums)
                    }
            ),
            beModel = BeModel(
                entities = schemaData.entities
                    .map { entity ->
                        val allNestedItemIds = HierarchicalItemSearch.findAllItemNames(entity.item, schemaData.items)
                        mapBeEntityModel(entity, allNestedItemIds, schemaData.allBeItemModels(entity.toBeEntityDescriptionModel()), schemaData.enums)
                    }
            )
        )
    }

    private fun Entity.toUiEntityDescriptionModel(): UiEntityDescriptionModel {
        return UiEntityDescriptionModel(entityId, NameCase(entityName))
    }

    private fun SchemaData.allUiItemModels(entity: UiEntityDescriptionModel): List<UiItemModel> {
        return items.map { item -> mapUiItemModel(entity, item, enums, entities) }
    }

    private fun mapUiItemModel(entity: UiEntityDescriptionModel, item: Item, enums: List<EnumType>, entities: List<Entity>): UiItemModel {
        val itemDescription = toUiItemDescriptionModel(item.itemId)
        return UiItemModel(
            itemDescription = toUiItemDescriptionModel(item.itemId),
            attributes = item.attributes.map { mapUiItemAttribute(entity, itemDescription, it, enums, entities) }
        )
    }

    private fun toUiItemDescriptionModel(itemId: ItemId): UiItemDescriptionModel {
        return UiItemDescriptionModel(
            itemId = itemId,
            itemName = NameCase(itemId.itemName),
        )
    }

    private fun mapUiItemAttribute(
        entity: UiEntityDescriptionModel,
        item: UiItemDescriptionModel,
        itemAttribute: ItemAttribute,
        enums: List<EnumType>,
        entities: List<Entity>,
    ): UiAttributeModel {
        val itemAttributeType = itemAttribute.type
        val attributeName = NameCase(itemAttribute.attributeName)

        return when (itemAttributeType) {
            is BuiltInType -> BuiltInTypeUiAttributeModel(
                entity = entity,
                item = item,
                attributeName = attributeName,
                isNullable = itemAttribute.isNullable,
                isList = itemAttribute.isMultiple,
                customValidation = itemAttribute.customValidation,
                builtInType = itemAttributeType,
            )
            is EntityId -> EntityReferenceUiAttributeModel(
                entity = entity,
                item = item,
                attributeName = attributeName,
                isNullable = itemAttribute.isNullable,
                isList = itemAttribute.isMultiple,
                customValidation = itemAttribute.customValidation,
                referencedEntity = referencedEntity(itemAttributeType, entities).toUiEntityDescriptionModel(),
            )
            is EnumId -> {
                val enumType = enums.singleOrNull { it.enumId == itemAttributeType }
                    ?: throw NoSuchElementException("EnumType ${itemAttributeType.enumName} not found in schema enums")
                EnumUiAttributeModel(
                    entity = entity,
                    item = item,
                    attributeName = attributeName,
                    isNullable = itemAttribute.isNullable,
                    isList = itemAttribute.isMultiple,
                    customValidation = itemAttribute.customValidation,
                    enum = UiEnumModel(enumType),
                )
            }
            is ItemId -> ItemUiIAttributeModel(
                entity = entity,
                item = item,
                attributeName = attributeName,
                isNullable = itemAttribute.isNullable,
                isList = itemAttribute.isMultiple,
                customValidation = itemAttribute.customValidation,
                referencedItem = toUiItemDescriptionModel(itemAttributeType))
        }
    }

    /**
     * The entity an attribute of type [EntityId] refers to. The referenced entity must be
     * declared in the same schema, otherwise the reference could never be resolved.
     */
    private fun referencedEntity(entityId: EntityId, entities: List<Entity>): Entity {
        return entities.singleOrNull { it.entityId == entityId }
            ?: throw NoSuchElementException("Entity ${entityId.entityName} not found in schema entities")
    }

    private fun Entity.toBeEntityDescriptionModel(): BeEntityDescriptionModel {
        return BeEntityDescriptionModel(entityId, NameCase(entityName))
    }

    private fun SchemaData.allBeItemModels(entity: BeEntityDescriptionModel): List<BeItemModel> {
        return items.map { item -> mapBeItemModel(entity, item, enums, entities) }
    }

    private fun mapBeItemModel(entity: BeEntityDescriptionModel, item: Item, enums: List<EnumType>, entities: List<Entity>): BeItemModel {
        val itemDescription = toBeItemDescriptionModel(item.itemId)
        return BeItemModel(
            entityName = entity.entityName,
            itemDescription = itemDescription,
            attributes = item.attributes.map { mapBeItemAttribute(entity, itemDescription, it, enums, entities) }
        )
    }

    private fun toBeItemDescriptionModel(itemId: ItemId): BeItemDescriptionModel {
        return BeItemDescriptionModel(
            itemId = itemId,
            itemName = NameCase(itemId.itemName),
        )
    }

    private fun mapBeItemAttribute(
        entity: BeEntityDescriptionModel,
        item: BeItemDescriptionModel,
        itemAttribute: ItemAttribute,
        enums: List<EnumType>,
        entities: List<Entity>,
    ): BeAttributeModel {
        val itemAttributeType = itemAttribute.type
        val attributeName = NameCase(itemAttribute.attributeName)

        return when (itemAttributeType) {
            is BuiltInType -> BuiltInTypeBeAttributeModel(
                entity = entity,
                item = item,
                attributeName = attributeName,
                isNullable = itemAttribute.isNullable,
                isList = itemAttribute.isMultiple,
                customValidation = itemAttribute.customValidation,
                builtInType = itemAttributeType,
                exampleDataGeneratorConfig = toExampleDataGeneratorConfig(item, itemAttribute)
            )
            is EntityId -> EntityReferenceBeAttributeModel(
                entity = entity,
                item = item,
                attributeName = attributeName,
                isNullable = itemAttribute.isNullable,
                isList = itemAttribute.isMultiple,
                customValidation = itemAttribute.customValidation,
                referencedEntity = referencedEntity(itemAttributeType, entities).toBeEntityDescriptionModel(),
            )
            is EnumId -> {
                val enumType = enums.singleOrNull { it.enumId == itemAttributeType }
                    ?: throw NoSuchElementException("EnumType ${itemAttributeType.enumName} not found in schema enums")
                EnumBeAttributeModel(
                    entity = entity,
                    item = item,
                    attributeName = attributeName,
                    isNullable = itemAttribute.isNullable,
                    isList = itemAttribute.isMultiple,
                    customValidation = itemAttribute.customValidation,
                    enum = BeEnumModel(entity.entityName, enumType),
                )
            }
            is ItemId -> ItemBeIAttributeModel(
                entity = entity,
                item = item,
                attributeName = attributeName,
                isNullable = itemAttribute.isNullable,
                isList = itemAttribute.isMultiple,
                customValidation = itemAttribute.customValidation,
                referencedItem = toBeItemDescriptionModel(itemAttributeType))
        }
    }

    private fun toExampleDataGeneratorConfig(item: BeItemDescriptionModel, itemAttribute: ItemAttribute): BeExampleDataGeneratorConfig {
        val exampleDataCategory = itemAttribute.exampleDataCategory ?: defaultExampleDataCategory(itemAttribute)
        if(exampleDataCategory.supportedBuiltInType != itemAttribute.type) {
            throw IllegalArgumentException(
                "The attribute '${item.itemId.itemName}.${itemAttribute.attributeName}' has the built-in type ${itemAttribute.type} " +
                        "but the exampleDataCategory is only compatible to ${exampleDataCategory.supportedBuiltInType}"
            )
        }

        return BeExampleDataGeneratorConfig(
            generatorNamePrefix = NameCase(exampleDataCategory.generatorPrefixName),
            isNullable = itemAttribute.isNullable,
            numberOfEntries = if(itemAttribute.isMultiple) 3 else 1,
        )
    }

    private fun defaultExampleDataCategory(itemAttribute: ItemAttribute): ExampleDataCategory {
        val type = itemAttribute.type
        if(type is BuiltInType) {
            return when(type) {
                BuiltInType.STRING -> ExampleDataCategory.RANDOM_TEXT
                BuiltInType.NUMBER -> ExampleDataCategory.RANDOM_NUMBER
                BuiltInType.BOOLEAN -> ExampleDataCategory.RANDOM_BOOLEAN
                BuiltInType.UUID -> ExampleDataCategory.RANDOM_UUID
            }
        } else {
            throw IllegalStateException("ExampleDataCategory must be built-in type here")
        }
    }

    private fun mapBeEntityModel(entity: Entity, entityItemModelIds: Set<ItemId>, allBeItemModels: List<BeItemModel>, allEnumTypes: List<EnumType>): BeEntityModel {
        val entityRootItem = allBeItemModels.single { it.itemId == entity.item.itemId }
        return BeEntityModel(
            entityName = NameCase(entity.entityName),
            entityRootItem = entityRootItem,
            idAttribute = entityRootItem.attributes.single { it.attributeName.isEqual(entity.idAttributeName) },
            entityItemModels = allBeItemModels.filter { it.itemId in entityItemModelIds },
            entityEnumTypes = allEnumTypes.map { BeEnumModel(NameCase(entity.entityName), it) }, // TODO filter for only the enums used in this entity
        )
    }

    private fun mapUiEntityViewsModel(uiEntity: UiEntity, entityItemModelIds: Set<ItemId>, allUiItemModels: List<UiItemModel>, allEnumTypes: List<EnumType>): UiEntityViewsModel {
        val entityRootItem = allUiItemModels.single { it.itemId == uiEntity.entity.item.itemId }
        val uiEntityModel = UiEntityModel(
            entityName = NameCase(uiEntity.entity.entityName),
            entityRootItem = entityRootItem,
            idAttribute = entityRootItem.attributes.single { it.attributeName.isEqual(uiEntity.entity.idAttributeName) },
            entityItemModels = allUiItemModels.filter { it.itemId in entityItemModelIds },
            entityEnumTypes = allEnumTypes.map { UiEnumModel(it) }, // TODO filter for only the enums used in this entity
        )

        val uiEntityItems =uiEntity.editorView.itemConfiguration.map { itemConfiguration ->
            val itemModel = when (itemConfiguration) {
                is UiEntityEditorEntityConfiguration -> uiEntityModel.entityRootItem
                is UiEntityEditorEntityNestedItemConfiguration -> requireNotNull(uiEntityModel.entityItemModels.firstOrNull { it.itemName.isEqual(itemConfiguration.itemId.itemName) }) {
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
                item = uiItemModel,
                attribute = uiItemModel.attributes.single { it.attributeName.isEqual(block.attributeName) },
            )
            is UiSectionBlock -> UiEntityFormNamedSectionSplitBlockModel(block.sectionName)
            is UiTextBlock -> UiEntityFormTextBlockModel(block.textName)
        }
    }
}
