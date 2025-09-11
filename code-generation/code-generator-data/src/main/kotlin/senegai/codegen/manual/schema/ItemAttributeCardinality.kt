package senegai.codegen.manual.schema

enum class ItemAttributeCardinality {
    ZERO_TO_ONE, // = nullable
    EXACTLY_ONE, // = non-nullable
    ZERO_TO_MANY, // = list or set
}
