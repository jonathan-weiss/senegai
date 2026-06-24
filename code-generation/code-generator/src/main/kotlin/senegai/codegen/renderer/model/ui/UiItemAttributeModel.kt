package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.NotSupportedInTemplateException
import senegai.codegen.renderer.model.NameCase
import senegai.codegen.renderer.model.ui.entityform.AttributeAndBuiltInTypeDescriptionModel
import senegai.codegen.schema.BuiltInType

data class UiItemAttributeModel(
    val entity: UiEntityDescriptionModel,
    val attributeName: NameCase,
    val isNullable: Boolean,
    val isList: Boolean,
    val type: UiItemAttributeTypeModel,
) {
    val typescriptAttributeType: String = calculateAttributeTypeWithCardinality()

    val angularInitialValueFormType: String = calculateAngularInitialValueFormType()
    val angularFormControlType: String = calculateAngularFormControlType(withCollection = false)
    val angularFormControlTypeWithCollection: String = calculateAngularFormControlType(withCollection = true)
    val angularFormInitialValue: String
        get() = determineAngularFormInitialValue()

    val isItem: Boolean
        get() = (type is ItemUiItemAttributeTypeModel)
    val attributeAndItem: AttributeAndItemDescriptionModel
        get() = createAttributeAndItem()

    val isHideFormControlWithNullValues: Boolean = (type is BuiltInTypeUiItemAttributeTypeModel)
    val isBuiltIn: Boolean = (type is BuiltInTypeUiItemAttributeTypeModel)
    val attributeAndBuiltInType: AttributeAndBuiltInTypeDescriptionModel
        get() = createAttributeAndBuiltInType()

    private fun createAttributeAndItem(): AttributeAndItemDescriptionModel {
        return if(type is ItemUiItemAttributeTypeModel) {
            AttributeAndItemDescriptionModel(this, type)
        } else {
            throw RuntimeException("Attribute $attributeName is not an item type")
        }
    }

    private fun createAttributeAndBuiltInType(): AttributeAndBuiltInTypeDescriptionModel {
        return if(type is BuiltInTypeUiItemAttributeTypeModel) {
            AttributeAndBuiltInTypeDescriptionModel(this, type)
        } else {
            throw RuntimeException("Attribute $attributeName is not a builtInType type")
        }
    }

    private fun calculateAttributeType(): String =
        when (type) {
            is BuiltInTypeUiItemAttributeTypeModel -> type.builtInTypeAsString()
            is EnumUiItemAttributeTypeModel -> throw NotSupportedInTemplateException("EnumUiItemAttributeTypeModel is not supported.")
            is ItemUiItemAttributeTypeModel -> "${type.itemTypeAsString()}WTO"
        }

    private fun ItemUiItemAttributeTypeModel.itemTypeAsString(): String = this.item.itemName.pascalCase
    private fun ItemUiItemAttributeTypeModel.entityAndItemTypeAsString(): String = "${entity.entityName.pascalCase}${this.item.itemName.pascalCase}"

    private fun EnumUiItemAttributeTypeModel.enumTypeAsString(): String = this.enumId.enumName

    private fun BuiltInTypeUiItemAttributeTypeModel.builtInTypeAsString(): String =
        when (this.builtInType) {
            BuiltInType.STRING -> "string"
            BuiltInType.NUMBER -> "number"
            BuiltInType.BOOLEAN -> "boolean"
        }

    private fun calculateAttributeTypeWithCardinality(): String {
        val type = calculateAttributeType()
        return when {
            !isList && isNullable -> "$type | null"
            !isList && !isNullable -> type
            isList && !isNullable -> "Array<$type>"
            else -> "Array<$type> | null"
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
            is ItemUiItemAttributeTypeModel -> "FormGroup<${type.entityAndItemTypeAsString()}FormPartGroup>"
        }

        // TODO form values are never null, as the initial value is used for
        //val singleTypeWithNullability =  withAngularFormNullability(singleType)

        // Built-in type lists store their elements directly in a `FormArray<FormControl<...>>`.
        // The initial value provided by the form-part service is the value of a single element
        // (e.g. `string`), not the whole array (which is created empty). Only item lists need the
        // `Array<...>` wrapping, because their FormArray is seeded from the initial value service.
        return if(isList && type !is BuiltInTypeUiItemAttributeTypeModel) {
            "Array<$singleType>"
        } else {
            singleType
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
            is ItemUiItemAttributeTypeModel -> "${type.entityAndItemTypeAsString()}FormPartGroup"
        }
        // TODO form values are never null, as the initial value is used for
//        val singleTypeWithNullability =  withAngularFormNullability(singleType)
        val singleFormType = if(isItem) {
            "FormGroup<$singleType>"
        } else {
            "FormControl<$singleType>"
        }
//
//        if(this.attributeName.camelCase == "contactAddress") {
//            println("!! contactAddress($isItem, ${(type is ItemUiItemAttributeTypeModel)}, $type): $singleType -> $singleFormType ($this)")
//
//        }
//

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

    private fun determineAngularFormInitialValue(): String =
        if (isList && type !is BuiltInTypeUiItemAttributeTypeModel) {
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
}
