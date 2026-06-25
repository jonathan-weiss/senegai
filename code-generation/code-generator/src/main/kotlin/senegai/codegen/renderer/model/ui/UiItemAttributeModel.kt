package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.renderer.model.ui.entityform.AttributeAndBuiltInTypeDescriptionModel
import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EnumId

sealed class UiItemAttributeModel(
    open val entity: UiEntityDescriptionModel,
    open val item: UiItemDescriptionModel,
    open val attributeName: NameCase,
    open val isNullable: Boolean,
    open val isList: Boolean,
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

    val isBuiltIn: Boolean = (type is BuiltInTypeUiItemAttributeTypeModel)
    val isEnum: Boolean = (type is EnumUiItemAttributeTypeModel)

    private fun createAttributeAndItem(): AttributeAndItemDescriptionModel {
        return if(type is ItemUiItemAttributeTypeModel) {
            AttributeAndItemDescriptionModel(this, type)
        } else {
            throw RuntimeException("Attribute $attributeName is not an item type")
        }
    }

    private fun calculateAttributeType(): String =
        when (type) {
            is BuiltInTypeUiItemAttributeTypeModel -> type.builtInTypeAsString()
            is EnumUiItemAttributeTypeModel -> type.enumTypeAsString()
            is ItemUiItemAttributeTypeModel -> "${type.itemTypeAsString()}WTO"
        }

    private fun ItemUiItemAttributeTypeModel.itemTypeAsString(): String = this.item.itemName.pascalCase
    private fun ItemUiItemAttributeTypeModel.entityAndItemTypeAsString(): String = "${entity.entityName.pascalCase}${this.item.itemName.pascalCase}"

    private fun EnumUiItemAttributeTypeModel.enumTypeAsString(): String = this.enum.enumClassName

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

        // Built-in type and enum lists store their elements directly in a `FormArray<FormControl<...>>`.
        // The initial value provided by the form-part service is the value of a single element
        // (e.g. `string` or an enum value), not the whole array (which is created empty). Only item
        // lists need the `Array<...>` wrapping, because their FormArray is seeded from the initial value service.
        return if(isList && isItem) {
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
        if (isList && isItem) {
            "[]"
        } else {
            when (type) {
                is BuiltInTypeUiItemAttributeTypeModel -> when (type.builtInType) {
                    BuiltInType.STRING -> "''"
                    BuiltInType.NUMBER -> "0"
                    BuiltInType.BOOLEAN -> "false"
                }
                is EnumUiItemAttributeTypeModel -> type.enum.angularFormInitialValue
                is ItemUiItemAttributeTypeModel -> throw RuntimeException("ItemUiItemAttributeTypeModel has no form initial value.") // should not occur
            }
        }
}


class BuiltInTypeUiAttributeModel(
    entity: UiEntityDescriptionModel,
    item: UiItemDescriptionModel,
    attributeName: NameCase,
    isNullable: Boolean,
    isList: Boolean,
    attributeType: UiItemAttributeTypeModel,
    val builtInType: BuiltInType,
) : UiItemAttributeModel(
    entity = entity,
    item = item,
    attributeName = attributeName,
    isNullable = isNullable,
    isList = isList,
    type = attributeType
)

class ItemUiIAttributeModel(
    entity: UiEntityDescriptionModel,
    item: UiItemDescriptionModel,
    attributeName: NameCase,
    isNullable: Boolean,
    isList: Boolean,
    attributeType: UiItemAttributeTypeModel,
    val referencedItem: UiItemDescriptionModel,
) : UiItemAttributeModel(
    entity = entity,
    item = item,
    attributeName = attributeName,
    isNullable = isNullable,
    isList = isList,
    type = attributeType,
)

class EnumUiAttributeModel(
    entity: UiEntityDescriptionModel,
    item: UiItemDescriptionModel,
    attributeName: NameCase,
    isNullable: Boolean,
    isList: Boolean,
    attributeType: UiItemAttributeTypeModel,
    val enum: UiEnumModel,
) : UiItemAttributeModel(
    entity = entity,
    item = item,
    attributeName = attributeName,
    isNullable = isNullable,
    isList = isList,
    type = attributeType,
) {
    val enumId: EnumId = enum.enumId
}
