package senegai.codegen.schema

import org.codeblessing.sourceamazing.schema.api.annotations.References

data class ItemAttributeData(
    val attributeName: String,
    val cardinality: ItemAttributeCardinality,
    @References([ItemAttributeBuiltInType::class, ItemAttributeEnumType::class, ItemAttributeNestedItemType::class])
    val type: ItemAttributeType,
)
