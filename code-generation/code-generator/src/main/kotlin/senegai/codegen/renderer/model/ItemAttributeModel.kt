package senegai.codegen.renderer.model

import senegai.codegen.renderer.NotSupportedInTemplateException
import senegai.codegen.schema.*

data class ItemAttributeModel(
    val attributeName: String,
    val cardinality: ItemAttributeCardinality,
    val type: ItemAttributeType,
) {
    val typescriptAttributeTypeExample: String = calculateExampleValue()
    val typescriptAttributeInitialValue: String = calculateInitialValue()
    val typescriptAttributeType: String = calculateAttributeTypeWithNullability()
    val typescriptAttributeTypeWithoutNullability: String = calculateAttributeType()
    val typescriptAttributeTypeCapitalizedWithoutNullability: String = calculateAttributeType().capitalize()
    //val typescriptAttributeType: String = if(attributeName == "nickname") "string | null" else "string"

    private fun calculateAttributeType(): String {
        return when(type) {
            is BuiltInType -> type.builtInTypeAsString()
            is EntityId -> throw NotSupportedInTemplateException(type.toString())
            is EnumId -> throw NotSupportedInTemplateException(type.toString())
            is ItemId -> throw NotSupportedInTemplateException(type.toString())
        }
    }

    private fun BuiltInType.builtInTypeAsString(): String {
        return when(this) {
            BuiltInType.STRING -> "string"
            BuiltInType.NUMBER -> "number"
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
            is BuiltInType -> type.builtInTypeInitialValue()
            is EntityId -> throw NotSupportedInTemplateException(type.toString())
            is EnumId -> throw NotSupportedInTemplateException(type.toString())
            is ItemId -> throw NotSupportedInTemplateException(type.toString())
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
            is BuiltInType -> type.builtInTypeExampleValue()
            is EntityId -> throw NotSupportedInTemplateException(type.toString())
            is EnumId -> throw NotSupportedInTemplateException(type.toString())
            is ItemId -> throw NotSupportedInTemplateException(type.toString())
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
