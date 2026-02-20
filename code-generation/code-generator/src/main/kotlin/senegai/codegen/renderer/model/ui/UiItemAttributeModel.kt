package senegai.codegen.renderer.model.ui

import org.codeblessing.templatetools.CaseUtil
import senegai.codegen.renderer.NotSupportedInTemplateException
import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EntityId
import senegai.codegen.schema.EnumId
import senegai.codegen.schema.ItemAttributeType
import senegai.codegen.schema.ItemId

data class UiItemAttributeModel(
    val attributeName: NameCase,
    val isNullable: Boolean,
    val isList: Boolean,
    val type: UiItemAttributeTypeModel,
) {
    val typescriptAttributeTypeExample: String = calculateExampleValue()
    val typescriptAttributeInitialValue: String = calculateInitialValue()
    val typescriptAttributeType: String = calculateAttributeTypeWithNullability()
    val typescriptAttributeTypeWithoutNullability: String = calculateAttributeType()
    val typescriptAttributeTypeCapitalizedWithoutNullability: String = CaseUtil.capitalize(calculateAttributeType())
    //val typescriptAttributeType: String = if(attributeName == "nickname") "string | null" else "string"

    val typescriptAttributeFormType: String = calculateFormType()
    val typescriptAttributeFormControlType: String = calculateFormTypeIncludingCollection()
    val formInitialValue: String = determineFormInitialValue()
    val attributeCardinality: AttributeCardinalityModel = attributeCardinalityModel()

    private fun calculateAttributeType(): String {
        return when(type) {
            is BuiltInTypeUiItemAttributeTypeModel -> type.builtInType.builtInTypeAsString()
            is EnumUiItemAttributeTypeModel -> throw NotSupportedInTemplateException(type.toString())
            is ItemUiItemAttributeTypeModel -> throw NotSupportedInTemplateException(type.toString())
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

    private fun calculateFormType(): String {
        val type = calculateAttributeType()
        return if(isNullable) {
            "$type | null"
        } else {
            type
        }
    }

    private fun calculateFormTypeIncludingCollection(): String {
        // TODO if it is an item, we need FormArray<FormGroup<...>
        val type = calculateFormType()
        return if(isList) {
            "FormArray<FormControl<$type>>"
        } else {
            "FormControl<$type>"
        }
    }

    private fun determineFormInitialValue(): String {
        return if(isNullable) {
            "null"
        } else if(isList) {
            "[]"
        } else {
            "''"
        }
    }

    private fun calculateInitialValue(): String {
        return when(type) {
            is BuiltInTypeUiItemAttributeTypeModel -> type.builtInType.builtInTypeInitialValue()
            is EnumUiItemAttributeTypeModel -> throw NotSupportedInTemplateException(type.toString())
            is ItemUiItemAttributeTypeModel -> throw NotSupportedInTemplateException(type.toString())
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
            is BuiltInTypeUiItemAttributeTypeModel -> type.builtInType.builtInTypeExampleValue()
            is EnumUiItemAttributeTypeModel -> throw NotSupportedInTemplateException(type.toString())
            is ItemUiItemAttributeTypeModel -> throw NotSupportedInTemplateException(type.toString())
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
