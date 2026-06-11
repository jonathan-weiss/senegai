package senegai.codegen.renderer.model.ui

import org.codeblessing.templatetools.CaseUtil
import senegai.codegen.renderer.NotSupportedInTemplateException
import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.BuiltInType

data class UiItemAttributeModel(
    val attributeName: NameCase,
    val isNullable: Boolean,
    val isList: Boolean,
    val type: UiItemAttributeTypeModel,
) {
    val typescriptAttributeType: String = calculateAttributeTypeWithNullability()
    val typescriptAttributeTypeWithoutNullability: String = calculateAttributeType()
    val typescriptAttributeTypeCapitalizedWithoutNullability: String = CaseUtil.capitalize(calculateAttributeType())

    val typescriptAttributeFormType: String = calculateFormType()
    val typescriptAttributeFormControlType: String = calculateFormTypeIncludingCollection()
    val formInitialValue: String = determineFormInitialValue()
    val attributeCardinality: AttributeCardinalityModel = attributeCardinalityModel()

    fun isBuildInTypeOf(expectedType: BuiltInType): Boolean {
        if (type !is BuiltInTypeUiItemAttributeTypeModel) {
            return false
        }
        return expectedType == type.builtInType
    }

    private fun calculateAttributeType(): String =
        when (type) {
            is BuiltInTypeUiItemAttributeTypeModel -> type.builtInType.builtInTypeAsString()
            is EnumUiItemAttributeTypeModel -> throw NotSupportedInTemplateException(type.toString())
            is ItemUiItemAttributeTypeModel -> throw NotSupportedInTemplateException(type.toString())
        }

    private fun BuiltInType.builtInTypeAsString(): String =
        when (this) {
            BuiltInType.STRING -> "string"
            BuiltInType.NUMBER -> "number"
            BuiltInType.BOOLEAN -> "boolean"
        }

    private fun calculateAttributeTypeWithNullability(): String {
        val type = calculateAttributeType()
        return when (attributeCardinalityModel()) {
            AttributeCardinalityModel.NULLABLE_SINGLE_ITEM -> "$type | null"
            AttributeCardinalityModel.SINGLE_ITEM -> type
            AttributeCardinalityModel.LIST_ITEMS -> "ReadonlyArray<$type>"
        }
    }

    private fun calculateFormType(): String {
        val type = calculateAttributeType()
        return if (isNullable) {
            "$type | null"
        } else {
            type
        }
    }

    private fun calculateFormTypeIncludingCollection(): String {
        // TODO if it is an item, we need FormArray<FormGroup<...>
        val type = calculateFormType()
        return if (isList) {
            "FormArray<FormControl<$type>>"
        } else {
            "FormControl<$type>"
        }
    }

    private fun determineFormInitialValue(): String =
        if (isNullable) {
            "null"
        } else if (isList) {
            "[]"
        } else {
            "''"
        }

    private fun attributeCardinalityModel(): AttributeCardinalityModel =
        if (isNullable) {
            AttributeCardinalityModel.NULLABLE_SINGLE_ITEM
        } else {
            AttributeCardinalityModel.SINGLE_ITEM
        }
}
