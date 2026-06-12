package senegai.codegen.renderer.model.ui

import org.codeblessing.templatetools.CaseUtil
import senegai.codegen.renderer.NotSupportedInTemplateException
import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EnumId

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
    val formComponentTypeInfix: String = determineFormComponentTypeInfix()
    val attributeCardinality: AttributeCardinalityModel = attributeCardinalityModel()

    private fun calculateAttributeType(): String =
        when (type) {
            is BuiltInTypeUiItemAttributeTypeModel -> type.builtInTypeAsString()
            is EnumUiItemAttributeTypeModel -> throw NotSupportedInTemplateException("EnumUiItemAttributeTypeModel is not supported.")
            is ItemUiItemAttributeTypeModel -> throw NotSupportedInTemplateException("ItemUiItemAttributeTypeModel is not supported.") // TODO type.itemTypeAsString()
        }

    private fun ItemUiItemAttributeTypeModel.itemTypeAsString(): String = this.item.itemName.pascalCase

    private fun EnumUiItemAttributeTypeModel.enumTypeAsString(): String = this.enumId.enumName

    private fun BuiltInTypeUiItemAttributeTypeModel.builtInTypeAsString(): String =
        when (this.builtInType) {
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

    /**
     * Replaces the component to <app-text-input />
     */
    private fun determineFormComponentTypeInfix(): String =
        when (type) {
            is BuiltInTypeUiItemAttributeTypeModel -> when (type.builtInType) {
                BuiltInType.STRING -> "text"
                BuiltInType.NUMBER -> "number"
                BuiltInType.BOOLEAN -> "boolean"
            }
            is EnumUiItemAttributeTypeModel -> throw NotSupportedInTemplateException("EnumUiItemAttributeTypeModel is not supported.")
            is ItemUiItemAttributeTypeModel -> throw NotSupportedInTemplateException("ItemUiItemAttributeTypeModel is not supported.") // TODO type.itemTypeAsString()
        }


    private fun determineFormInitialValue(): String =
        if (isNullable) {
            "null"
        } else if (isList) {
            "[]"
        } else {
            when (type) {
                is BuiltInTypeUiItemAttributeTypeModel -> when (type.builtInType) {
                    BuiltInType.STRING -> "''"
                    BuiltInType.NUMBER -> "0"
                    BuiltInType.BOOLEAN -> "false"
                }
                is EnumUiItemAttributeTypeModel -> throw NotSupportedInTemplateException("EnumUiItemAttributeTypeModel is not supported.")
                is ItemUiItemAttributeTypeModel -> throw NotSupportedInTemplateException("ItemUiItemAttributeTypeModel is not supported.") // TODO type.itemTypeAsString()
            }
        }

    private fun attributeCardinalityModel(): AttributeCardinalityModel =
        if (isNullable) {
            AttributeCardinalityModel.NULLABLE_SINGLE_ITEM
        } else {
            AttributeCardinalityModel.SINGLE_ITEM
        }
}
