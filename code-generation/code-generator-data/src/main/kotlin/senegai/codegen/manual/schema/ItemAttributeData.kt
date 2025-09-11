package senegai.codegen.manual.schema

data class ItemAttributeData(
    val attributeName: String,
    val cardinality: ItemAttributeCardinality,
    val type: ItemAttributeType,
)
