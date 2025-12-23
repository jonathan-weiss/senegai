package senegai.codegen.renderer.model

import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.ItemAttributeBuiltInType
import senegai.codegen.schema.ItemAttributeCardinality
import senegai.codegen.schema.ItemAttributeEnumType
import senegai.codegen.schema.ItemAttributeNestedItemType
import senegai.codegen.schema.ItemAttributeType

data class ItemAttribute(
    val attributeName: String,
    val cardinality: ItemAttributeCardinality,
    val type: ItemAttributeType,
) {
    val typescriptAttributeTypeExample: String = calculateExampleValue()
    val typescriptAttributeInitialValue: String = calculateInitialValue()
    val typescriptAttributeType: String = calculateAttributeTypeWithNullability()
    //val typescriptAttributeType: String = if(attributeName == "nickname") "string | null" else "string"

    private fun calculateAttributeType(): String {
        return when(type) {
            is ItemAttributeEnumType -> "enum not supported"
            is ItemAttributeNestedItemType -> "nested item not supported"
            is ItemAttributeBuiltInType -> type.builtInType.builtInTypeAsString()
        }
    }

    private fun BuiltInType.builtInTypeAsString(): String {
        return when(this) {
            BuiltInType.STRING -> "string"
            BuiltInType.NUMBER -> "integer"
            BuiltInType.BOOLEAN -> "boolean"
        }
    }

    private fun calculateAttributeTypeWithNullability(): String {
        val type = calculateAttributeType()
        return when(cardinality) {
            ItemAttributeCardinality.ZERO_TO_ONE -> "$type | null"
            ItemAttributeCardinality.EXACTLY_ONE -> type
            ItemAttributeCardinality.ZERO_TO_MANY -> "ReadonlyArray<$type>"
        }
    }

    private fun calculateInitialValue(): String {
        return when(type) {
            is ItemAttributeEnumType -> "enum not supported"
            is ItemAttributeNestedItemType -> "nested item not supported"
            is ItemAttributeBuiltInType -> type.builtInType.builtInTypeInitialValue()
        }
    }

    private fun BuiltInType.builtInTypeInitialValue(): String {
        return when(this) {
            BuiltInType.STRING -> "''"
            BuiltInType.NUMBER -> "0"
            BuiltInType.BOOLEAN -> "false"
        }
    }

    private fun calculateExampleValue(): String {
        return when(type) {
            is ItemAttributeEnumType -> "enum not supported"
            is ItemAttributeNestedItemType -> "nested item not supported"
            is ItemAttributeBuiltInType -> type.builtInType.builtInTypeExampleValue()
        }
    }

    private fun BuiltInType.builtInTypeExampleValue(): String {
        return when(this) {
            BuiltInType.STRING -> "'John'"
            BuiltInType.NUMBER -> "316"
            BuiltInType.BOOLEAN -> "true"
        }
    }

}
