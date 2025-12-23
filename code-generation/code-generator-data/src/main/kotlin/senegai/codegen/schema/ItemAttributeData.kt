package senegai.codegen.schema

data class ItemAttributeData(
    val attributeName: String,
    val cardinality: ItemAttributeCardinality,
    val type: ItemAttributeType,
)
