package senegai.codegen.schema

data class SchemaData(val items: List<ItemData>)

data class ItemId(val itemName: String)

data class ItemData(
    val itemId: ItemId,
    val attributes: List<ItemAttributeData>,
)
data class ItemAttributeData(
    val attributeName: String,
    val cardinality: ItemAttributeCardinality,
    val type: ItemAttributeType,
)

enum class ItemAttributeCardinality {
    ZERO_TO_ONE, // = nullable
    EXACTLY_ONE, // = non-nullable
    ZERO_TO_MANY, // = list or set
}


sealed interface ItemAttributeType

data class ItemAttributeEnumType(
    val enumValues: List<String>,
): ItemAttributeType

data class ItemAttributeNestedItemType(
    val nestedItem: ItemData,
): ItemAttributeType

data class ItemAttributeBuiltInType(
    val builtInType: BuiltInType,
): ItemAttributeType


enum class BuiltInType {
    STRING,
    NUMBER,
    BOOLEAN,
}
