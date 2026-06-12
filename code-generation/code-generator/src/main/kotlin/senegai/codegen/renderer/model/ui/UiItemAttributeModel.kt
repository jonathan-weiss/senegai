package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.NotSupportedInTemplateException
import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.BuiltInType

data class UiItemAttributeModel(
    val attributeName: NameCase,
    val isNullable: Boolean,
    val isList: Boolean,
    val type: UiItemAttributeTypeModel,
) {
    val typescriptAttributeType: String = calculateAttributeTypeWithCardinality()
    val isItem: Boolean = (type is ItemUiItemAttributeTypeModel)

    val angularInitialValueFormType: String = calculateAngularInitialValueFormType()
    val angularFormControlType: String = calculateAngularFormControlType(withCollection = false)
    val angularFormControlTypeWithCollection: String = calculateAngularFormControlType(withCollection = true)
    val angularFormInitialValue: String
        get() = determineAngularFormInitialValue()
    val formComponentTypeInfix: String
        get() = determineFormComponentTypeInfix()
    val attributeCardinality: AttributeCardinalityModel = attributeCardinalityModel()

    private fun calculateAttributeType(): String =
        when (type) {
            is BuiltInTypeUiItemAttributeTypeModel -> type.builtInTypeAsString()
            is EnumUiItemAttributeTypeModel -> throw NotSupportedInTemplateException("EnumUiItemAttributeTypeModel is not supported.")
            is ItemUiItemAttributeTypeModel -> "${type.itemTypeAsString()}WTO"
        }

    private fun ItemUiItemAttributeTypeModel.itemTypeAsString(): String = this.item.itemName.pascalCase

    private fun EnumUiItemAttributeTypeModel.enumTypeAsString(): String = this.enumId.enumName

    private fun BuiltInTypeUiItemAttributeTypeModel.builtInTypeAsString(): String =
        when (this.builtInType) {
            BuiltInType.STRING -> "string"
            BuiltInType.NUMBER -> "number"
            BuiltInType.BOOLEAN -> "boolean"
        }

    private fun calculateAttributeTypeWithCardinality(): String {
        val type = calculateAttributeType()
        return when (attributeCardinalityModel()) {
            AttributeCardinalityModel.NULLABLE_SINGLE_ITEM -> "$type | null"
            AttributeCardinalityModel.SINGLE_ITEM -> type
            AttributeCardinalityModel.LIST_ITEMS -> "ReadonlyArray<$type>"
        }
    }

    /**
     * Something like:
     * - `string`
     * - `string | null`
     * - `AppellatioEnum | null`
     * - `Array<FormGroup<ArticulusInteriorFormPartGroup>>`
     * Form values are always `null`based, not `undefined`.
     */
    private fun calculateAngularInitialValueFormType(): String {
        val singleType = when (type) {
            is BuiltInTypeUiItemAttributeTypeModel -> typescriptBuildInType(type.builtInType)
            is EnumUiItemAttributeTypeModel -> type.enumTypeAsString()
            is ItemUiItemAttributeTypeModel -> "FormGroup<${type.itemTypeAsString()}FormPartGroup>"
        }

        val singleTypeWithNullability =  withAngularFormNullability(singleType)

        return if(isList) {
            "Array<$singleTypeWithNullability>"
        } else {
            singleTypeWithNullability
        }
    }

    /**
     * Something like:
     * - `FormControl<string>`
     * - `FormControl<string | null>`
     * - `FormControl<AppellatioEnum>`
     * - `FormGroup<ArticulusInteriorFormPartGroup>`
     * - `FormArray<FormGroup<ArticulusInteriorFormPartGroup>>`
     * Form values are always `null`based, not `undefined`.
     */
    private fun calculateAngularFormControlType(withCollection: Boolean): String {
        val singleType = when (type) {
            is BuiltInTypeUiItemAttributeTypeModel -> typescriptBuildInType(type.builtInType)
            is EnumUiItemAttributeTypeModel -> type.enumTypeAsString()
            is ItemUiItemAttributeTypeModel -> "${type.itemTypeAsString()}FormPartGroup"
        }

        val singleTypeWithNullability =  withAngularFormNullability(singleType)
        val singleFormType = if(isItem) {
            "FormGroup<$singleTypeWithNullability>"
        } else {
            "FormControl<$singleTypeWithNullability>"
        }
        return if(isList && withCollection) {
            "FormArray<$singleFormType>"
        } else {
            singleFormType
        }
    }

    private fun typescriptBuildInType(builtInType: BuiltInType): String {
        return when (builtInType) {
            BuiltInType.STRING -> "string"
            BuiltInType.NUMBER -> "number"
            BuiltInType.BOOLEAN -> "boolean"
        }
    }

    /**
     * Form values are always `null`based, not `undefined`.
     */
    private fun withAngularFormNullability(singleType: String): String {
        return if (isNullable) {
            "$singleType | null"
        } else {
            singleType
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


    private fun determineAngularFormInitialValue(): String =
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
                is EnumUiItemAttributeTypeModel -> throw RuntimeException("EnumUiItemAttributeTypeModel has no form initial value.") // should not occur
                is ItemUiItemAttributeTypeModel -> throw RuntimeException("ItemUiItemAttributeTypeModel has no form initial value.") // should not occur
            }
        }

    private fun attributeCardinalityModel(): AttributeCardinalityModel =
        if (isNullable) {
            AttributeCardinalityModel.NULLABLE_SINGLE_ITEM
        } else {
            AttributeCardinalityModel.SINGLE_ITEM
        }
}
