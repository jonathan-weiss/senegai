package senegai.codegen.renderer.model.ui

data class UiEntityModel(
    val entityRootItem: UiItemModel
) {
    val entityItemModels: List<UiItemModel> = listOf(entityRootItem) // TODO implement recursive

    val entityName: String = entityRootItem.itemName
    val entityNameLowercase: String = entityRootItem.itemName.lowercase()
    val entityNameForAngularFile: String = entityRootItem.itemName.lowercase()  // TODO uses dashes case

    val searchResultAttributes: List<UiItemAttributeModel> = entityRootItem.attributes
    val searchCriteriaAttributes: List<UiItemAttributeModel> = entityRootItem.attributes
    val chainedFormAttributes: List<UiEntityChainedAttributeModel> = entityRootItem.attributes
        .map { UiEntityChainedAttributeModel(listOf(
            UiEntityItemAndAttributeModel(this, entityRootItem, it) // TODO make that recursive
        )) }
}
