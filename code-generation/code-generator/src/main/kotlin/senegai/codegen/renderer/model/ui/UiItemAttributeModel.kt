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
    val attributeCardinality: AttributeCardinalityModel = attributeCardinalityModel()

    private fun calculateAttributeType(): String =
        when (type) {
            is BuiltInTypeUiItemAttributeTypeModel -> type.builtInTypeAsString()
            is EnumUiItemAttributeTypeModel -> type.enumTypeAsString()
            is ItemUiItemAttributeTypeModel -> type.itemTypeAsString()
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
