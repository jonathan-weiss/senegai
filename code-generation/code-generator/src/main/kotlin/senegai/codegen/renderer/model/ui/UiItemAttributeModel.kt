package senegai.codegen.renderer.model.ui

import org.codeblessing.templatetools.CaseUtil
import senegai.codegen.renderer.NotSupportedInTemplateException
import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EntityId
import senegai.codegen.schema.EnumId
import senegai.codegen.schema.ItemAttributeCardinality
import senegai.codegen.schema.ItemAttributeType
import senegai.codegen.schema.ItemId

data class UiItemAttributeModel(
    val attributeName: String,
    val cardinality: ItemAttributeCardinality, // TODO that is wrong, it is not a model class
    val type: ItemAttributeType, // TODO that is wrong, it is not a model class
) {
    val typescriptAttributeTypeExample: String = calculateExampleValue()
    val typescriptAttributeInitialValue: String = calculateInitialValue()
    val typescriptAttributeType: String = calculateAttributeTypeWithNullability()
    val typescriptAttributeTypeWithoutNullability: String = calculateAttributeType()
    val typescriptAttributeTypeCapitalizedWithoutNullability: String = CaseUtil.capitalize(calculateAttributeType())
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
