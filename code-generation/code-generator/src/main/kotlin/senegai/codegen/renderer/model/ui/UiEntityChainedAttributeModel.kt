package senegai.codegen.renderer.model.ui

data class UiEntityChainedAttributeModel(
    val itemsAndAttributes: List<UiEntityItemAndAttributeModel>,
) {
    val attributeName: String = itemsAndAttributes.last().attribute.attributeName
}
