package senegai.codegen.renderer.model.ui

import org.codeblessing.templatetools.CaseUtil
import senegai.codegen.renderer.NotSupportedInTemplateException
import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EntityId
import senegai.codegen.schema.EnumId
import senegai.codegen.schema.ItemAttributeCardinality
import senegai.codegen.schema.ItemAttributeType
import senegai.codegen.schema.ItemId

data class UiItemAttributeModel(
    val attributeName: NameCase,
    val isNullable: Boolean,
    private val isList: Boolean,
    private val type: ItemAttributeType, // TODO that is wrong, it is not a model class
) {
    val typescriptAttributeTypeExample: String = calculateExampleValue()
    val typescriptAttributeInitialValue: String = calculateInitialValue()
    val typescriptAttributeType: String = calculateAttributeTypeWithNullability()
    val typescriptAttributeTypeWithoutNullability: String = calculateAttributeType()
    val typescriptAttributeTypeCapitalizedWithoutNullability: String = CaseUtil.capitalize(calculateAttributeType())
    //val typescriptAttributeType: String = if(attributeName == "nickname") "string | null" else "string"

    val typescriptAttributeFormType: String = calculateSingleItemAttributeTypeWithNullability()
    val attributeCardinality: AttributeCardinalityModel = attributeCardinalityModel()

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
        return when(attributeCardinalityModel()) {
            AttributeCardinalityModel.NULLABLE_SINGLE_ITEM -> "$type | null"
            AttributeCardinalityModel.SINGLE_ITEM -> type
            AttributeCardinalityModel.LIST_ITEMS -> "ReadonlyArray<$type>"
        }
    }

    private fun calculateSingleItemAttributeTypeWithNullability(): String {
        val type = calculateAttributeType()
        return if(isNullable) {
            "$type | null"
        } else {
            type
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

    private fun attributeCardinalityModel(): AttributeCardinalityModel {
        return if(isNullable) {
            AttributeCardinalityModel.NULLABLE_SINGLE_ITEM
        } else {
            AttributeCardinalityModel.SINGLE_ITEM
        }
    }
}
