package senegai.codegen.renderer.converter

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.renderer.model.SchemaModel
import senegai.codegen.renderer.model.be.BeAttributeModel
import senegai.codegen.renderer.model.be.BeEnumModel
import senegai.codegen.renderer.model.be.BeExampleDataGeneratorConfig
import senegai.codegen.renderer.model.be.BeItemDescriptionModel
import senegai.codegen.renderer.model.be.BeItemModel
import senegai.codegen.renderer.model.be.BeModel
import senegai.codegen.renderer.model.be.BeReferencedItemModel
import senegai.codegen.renderer.model.be.BuiltInTypeBeAttributeModel
import senegai.codegen.renderer.model.be.EnumBeAttributeModel
import senegai.codegen.renderer.model.be.ItemBeIAttributeModel
import senegai.codegen.renderer.model.be.ItemReferenceBeAttributeModel
import senegai.codegen.renderer.model.ui.BuiltInTypeUiAttributeModel
import senegai.codegen.renderer.model.ui.EnumUiAttributeModel
import senegai.codegen.renderer.model.ui.ItemReferenceUiAttributeModel
import senegai.codegen.renderer.model.ui.ItemUiIAttributeModel
import senegai.codegen.renderer.model.ui.UiAttributeModel
import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiEntityViewsModel
import senegai.codegen.renderer.model.ui.UiEnumModel
import senegai.codegen.renderer.model.ui.UiItemDescriptionModel
import senegai.codegen.renderer.model.ui.UiItemModel
import senegai.codegen.renderer.model.ui.UiModel
import senegai.codegen.renderer.model.ui.UiReferencedItemModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewColumnModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewTabModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormItemAttributeBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormNamedSectionSplitBlockModel
import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormTextBlockModel
import senegai.model.schema.BuiltInType
import senegai.model.schema.EnumId
import senegai.model.schema.EnumType
import senegai.model.schema.ExampleDataCategory
import senegai.model.schema.Item
import senegai.model.schema.ItemAttribute
import senegai.model.schema.ItemId
import senegai.model.schema.SchemaData
import senegai.model.schema.UiBlock
import senegai.model.schema.UiEntity
import senegai.model.schema.UiEntityEditorColumn
import senegai.model.schema.UiEntityEditorEntityConfiguration
import senegai.model.schema.UiEntityEditorEntityNestedItemConfiguration
import senegai.model.schema.UiEntityEditorTab
import senegai.model.schema.UiItemAttributeBlock
import senegai.model.schema.UiSectionBlock
import senegai.model.schema.UiTextBlock

object RendererModelConverter {

    fun convertSchemaDataToSchemaModel(schemaData: SchemaData): SchemaModel {
        val allUiEnumModels = schemaData.enums.map { UiEnumModel(it) }
        val allBeEnumModels = schemaData.enums.map { BeEnumModel(it) }

        // An item is independent of any UiEntity: it can appear in the editor of several of
        // them, therefore it is mapped exactly once.
        val allUiItemModels = schemaData.items.map { mapUiItemModel(it, schemaData.enums, schemaData.items) }
        val allBeItemModels = schemaData.items.map { mapBeItemModel(it, schemaData.enums, schemaData.items) }

        return SchemaModel(
            uiModel = UiModel(
                uiItems = allUiItemModels,
                uiEnums = allUiEnumModels,
                uiEntitiesViews = schemaData.uiEntities.map { uiEntity ->
                    val allNestedItemIds = HierarchicalItemSearch.findAllItemNames(uiEntity.rootItem, schemaData.items)
                    mapUiEntityViewsModel(uiEntity, allNestedItemIds, allUiItemModels, allUiEnumModels)
                }
            ),
            beModel = BeModel(
                items = allBeItemModels,
                enums = allBeEnumModels,
            )
        )
    }

    // **************
    // UI items
    // **************

    private fun mapUiItemModel(item: Item, enums: List<EnumType>, items: List<Item>): UiItemModel {
        val itemDescription = toUiItemDescriptionModel(item.itemId)
        val attributes = item.attributes.map { mapUiItemAttribute(itemDescription, it, enums, items) }
        return UiItemModel(
            itemDescription = itemDescription,
            attributes = attributes,
            idAttribute = item.idAttributeName?.let { idAttributeName ->
                attributes.single { it.attributeName.isEqual(idAttributeName) }
            },
        )
    }

    private fun toUiItemDescriptionModel(itemId: ItemId): UiItemDescriptionModel {
        return UiItemDescriptionModel(
            itemId = itemId,
            itemName = NameCase(itemId.itemName),
        )
    }

    private fun mapUiItemAttribute(
        item: UiItemDescriptionModel,
        itemAttribute: ItemAttribute,
        enums: List<EnumType>,
        items: List<Item>,
    ): UiAttributeModel {
        val itemAttributeType = itemAttribute.type
        val attributeName = NameCase(itemAttribute.attributeName)

        return when (itemAttributeType) {
            is BuiltInType -> BuiltInTypeUiAttributeModel(
                item = item,
                attributeName = attributeName,
                isNullable = itemAttribute.isNullable,
                isList = itemAttribute.isMultiple,
                customValidation = itemAttribute.customValidation,
                builtInType = itemAttributeType,
            )
            is EnumId -> {
                val enumType = enums.singleOrNull { it.enumId == itemAttributeType }
                    ?: throw NoSuchElementException("EnumType ${itemAttributeType.enumName} not found in schema enums")
                EnumUiAttributeModel(
                    item = item,
                    attributeName = attributeName,
                    isNullable = itemAttribute.isNullable,
                    isList = itemAttribute.isMultiple,
                    customValidation = itemAttribute.customValidation,
                    enum = UiEnumModel(enumType),
                )
            }
            is ItemId -> if (itemAttribute.isReference) {
                ItemReferenceUiAttributeModel(
                    item = item,
                    attributeName = attributeName,
                    isNullable = itemAttribute.isNullable,
                    isList = itemAttribute.isMultiple,
                    customValidation = itemAttribute.customValidation,
                    referencedItem = referencedItem(itemAttributeType, items).toUiReferencedItemModel(),
                )
            } else {
                ItemUiIAttributeModel(
                    item = item,
                    attributeName = attributeName,
                    isNullable = itemAttribute.isNullable,
                    isList = itemAttribute.isMultiple,
                    customValidation = itemAttribute.customValidation,
                    referencedItem = toUiItemDescriptionModel(itemAttributeType),
                )
            }
        }
    }

    private fun Item.toUiReferencedItemModel(): UiReferencedItemModel {
        return UiReferencedItemModel(
            itemId = itemId,
            itemName = NameCase(itemName),
            idAttributeName = NameCase(requireNotNull(idAttributeName)),
        )
    }

    // **************
    // Backend items
    // **************

    private fun mapBeItemModel(item: Item, enums: List<EnumType>, items: List<Item>): BeItemModel {
        val itemDescription = toBeItemDescriptionModel(item.itemId)
        val attributes = item.attributes.map { mapBeItemAttribute(itemDescription, it, enums, items) }
        return BeItemModel(
            itemDescription = itemDescription,
            attributes = attributes,
            idAttribute = item.idAttributeName?.let { idAttributeName ->
                attributes.single { it.attributeName.isEqual(idAttributeName) }
            },
        )
    }

    private fun toBeItemDescriptionModel(itemId: ItemId): BeItemDescriptionModel {
        return BeItemDescriptionModel(
            itemId = itemId,
            itemName = NameCase(itemId.itemName),
        )
    }

    private fun mapBeItemAttribute(
        item: BeItemDescriptionModel,
        itemAttribute: ItemAttribute,
        enums: List<EnumType>,
        items: List<Item>,
    ): BeAttributeModel {
        val itemAttributeType = itemAttribute.type
        val attributeName = NameCase(itemAttribute.attributeName)

        return when (itemAttributeType) {
            is BuiltInType -> BuiltInTypeBeAttributeModel(
                item = item,
                attributeName = attributeName,
                isNullable = itemAttribute.isNullable,
                isList = itemAttribute.isMultiple,
                customValidation = itemAttribute.customValidation,
                builtInType = itemAttributeType,
                exampleDataGeneratorConfig = toExampleDataGeneratorConfig(item, itemAttribute)
            )
            is EnumId -> {
                val enumType = enums.singleOrNull { it.enumId == itemAttributeType }
                    ?: throw NoSuchElementException("EnumType ${itemAttributeType.enumName} not found in schema enums")
                EnumBeAttributeModel(
                    item = item,
                    attributeName = attributeName,
                    isNullable = itemAttribute.isNullable,
                    isList = itemAttribute.isMultiple,
                    customValidation = itemAttribute.customValidation,
                    enum = BeEnumModel(enumType),
                )
            }
            is ItemId -> if (itemAttribute.isReference) {
                ItemReferenceBeAttributeModel(
                    item = item,
                    attributeName = attributeName,
                    isNullable = itemAttribute.isNullable,
                    isList = itemAttribute.isMultiple,
                    customValidation = itemAttribute.customValidation,
                    referencedItem = referencedItem(itemAttributeType, items).toBeReferencedItemModel(),
                )
            } else {
                ItemBeIAttributeModel(
                    item = item,
                    attributeName = attributeName,
                    isNullable = itemAttribute.isNullable,
                    isList = itemAttribute.isMultiple,
                    customValidation = itemAttribute.customValidation,
                    referencedItem = toBeItemDescriptionModel(itemAttributeType),
                )
            }
        }
    }

    private fun Item.toBeReferencedItemModel(): BeReferencedItemModel {
        return BeReferencedItemModel(
            itemId = itemId,
            itemName = NameCase(itemName),
            idAttributeName = NameCase(requireNotNull(idAttributeName)),
        )
    }

    /**
     * The item an attribute of type [ItemId] with `isReference` refers to. The referenced item
     * must be declared in the same schema, otherwise the reference could never be resolved,
     * and it must declare a primary key, otherwise there is no identifier to store.
     */
    private fun referencedItem(itemId: ItemId, items: List<Item>): Item {
        val item = items.singleOrNull { it.itemId == itemId }
            ?: throw NoSuchElementException("Item ${itemId.itemName} not found in schema items")

        require(item.hasPrimaryKey) {
            "The item '${item.itemName}' is referenced by another item, but it declares no " +
                    "primary key. Only an item with a primary key can be referenced, because a " +
                    "reference stores exactly that primary key."
        }
        return item
    }

    // **************
    // Example data
    // **************

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

    // **************
    // UiEntity editor views
    // **************

    private fun mapUiEntityViewsModel(
        uiEntity: UiEntity,
        entityItemModelIds: Set<ItemId>,
        allUiItemModels: List<UiItemModel>,
        allUiEnumModels: List<UiEnumModel>,
    ): UiEntityViewsModel {
        val entityRootItem = allUiItemModels.single { it.itemId == uiEntity.rootItem.itemId }
        val uiEntityModel = UiEntityModel(
            entityName = NameCase(uiEntity.uiEntityName),
            entityRootItem = entityRootItem,
            entityItemModels = allUiItemModels.filter { it.itemId in entityItemModelIds },
            entityEnumTypes = allUiEnumModels, // TODO filter for only the enums used in this UiEntity
        )

        val uiEntityItems = uiEntity.editorView.itemConfiguration.map { itemConfiguration ->
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
