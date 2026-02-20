package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.ItemId

data class UiItemModel(
    val itemDescription: UiItemDescriptionModel,
    val attributes: List<UiItemAttributeModel>,
) {
    val itemId: ItemId = itemDescription.itemId
    val itemName: NameCase = itemDescription.itemName

    val attributesWithItems: List<UiItemAttributeModel> = attributes
        .filter { it.type is ItemUiItemAttributeTypeModel}

    val attributesWithLists: List<UiItemAttributeModel> = attributes
        .filter { it.isList}

    val attributeItemsFlat: List<UiItemDescriptionModel> = attributes
        .map { it.type }.filterIsInstance<ItemUiItemAttributeTypeModel>()
        .map { it.item }

}
