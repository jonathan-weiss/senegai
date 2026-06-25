package senegai.codegen.renderer.model.ui

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EnumId

sealed class UiItemAttributeModel(
    open val entity: UiEntityDescriptionModel,
    open val item: UiItemDescriptionModel,
    open val attributeName: NameCase,
    open val isNullable: Boolean,
    open val isList: Boolean,
) {
    abstract val isItem: Boolean
    abstract val isBuiltIn: Boolean
    abstract val isEnum: Boolean

    val typescriptAttributeType: String
        get() = calculateAttributeTypeWithCardinality()

    val angularInitialValueFormType: String
        get() = calculateAngularInitialValueFormType()
    val angularFormControlType: String
        get() = calculateAngularFormControlType(withCollection = false)
    val angularFormControlTypeWithCollection: String
        get() = calculateAngularFormControlType(withCollection = true)
    val angularFormInitialValue: String
        get() = determineAngularFormInitialValue()

    protected abstract fun attributeTypeAsString(): String


    private fun calculateAttributeTypeWithCardinality(): String {
        val type = attributeTypeAsString()
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
        val singleType = calculateAngularInitialValueFormSingleType()

        // Built-in type and enum lists store their elements directly in a `FormArray<FormControl<...>>`.
        // The initial value provided by the form-part service is the value of a single element
        // (e.g. `string` or an enum value), not the whole array (which is created empty). Only item
        // lists need the `Array<...>` wrapping, because their FormArray is seeded from the initial value service.
        return if (isList && isItem) {
            "Array<$singleType>"
        } else {
            singleType
        }
    }

    protected abstract fun calculateAngularInitialValueFormSingleType(): String


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
        val singleFormType = calculateAngularFormControlSingleType()

        return if (isList && withCollection) {
            "FormArray<$singleFormType>"
        } else {
            singleFormType
        }
    }

    protected abstract fun calculateAngularFormControlSingleType(): String

    protected abstract fun determineAngularFormInitialValue(): String
}


class BuiltInTypeUiAttributeModel(
    entity: UiEntityDescriptionModel,
    item: UiItemDescriptionModel,
    attributeName: NameCase,
    isNullable: Boolean,
    isList: Boolean,
    val builtInType: BuiltInType,
) : UiItemAttributeModel(
    entity = entity,
    item = item,
    attributeName = attributeName,
    isNullable = isNullable,
    isList = isList,
) {
    override val isItem: Boolean
        get() = false
    override val isBuiltIn: Boolean
        get() = true
    override val isEnum: Boolean
        get() = false

    override fun attributeTypeAsString(): String {
        return builtInTypeAsString()
    }

    private fun builtInTypeAsString(): String =
        when (builtInType) {
            BuiltInType.STRING -> "string"
            BuiltInType.NUMBER -> "number"
            BuiltInType.BOOLEAN -> "boolean"
        }


    override fun calculateAngularInitialValueFormSingleType(): String {
        return typescriptBuildInType(builtInType)
    }

    private fun typescriptBuildInType(builtInType: BuiltInType): String {
        return when (builtInType) {
            BuiltInType.STRING -> "string"
            BuiltInType.NUMBER -> "number"
            BuiltInType.BOOLEAN -> "boolean"
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
    override fun calculateAngularFormControlSingleType(): String {
        return "FormControl<${typescriptBuildInType(builtInType)}>"
    }

    override fun determineAngularFormInitialValue(): String {
        return when (builtInType) {
            BuiltInType.STRING -> "''"
            BuiltInType.NUMBER -> "0"
            BuiltInType.BOOLEAN -> "false"
        }
    }

}

class ItemUiIAttributeModel(
    entity: UiEntityDescriptionModel,
    item: UiItemDescriptionModel,
    attributeName: NameCase,
    isNullable: Boolean,
    isList: Boolean,
    val referencedItem: UiItemDescriptionModel,
) : UiItemAttributeModel(
    entity = entity,
    item = item,
    attributeName = attributeName,
    isNullable = isNullable,
    isList = isList,
) {
    // it is always the same entity as the parent entity, as references can only exist within entities
    val referencedEntity: UiEntityDescriptionModel = entity

    override val isItem: Boolean
        get() = true
    override val isBuiltIn: Boolean
        get() = false
    override val isEnum: Boolean
        get() = false

    override fun attributeTypeAsString(): String {
        return "${referencedItemTypeAsString()}WTO"
    }

    private fun referencedItemTypeAsString(): String = this.referencedItem.itemName.pascalCase


    override fun calculateAngularInitialValueFormSingleType(): String {
        return "FormGroup<${entityAndReferencedItemTypeAsString()}FormPartGroup>"
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
    override fun calculateAngularFormControlSingleType(): String {
        return "FormGroup<${entityAndReferencedItemTypeAsString()}FormPartGroup>"
    }

    private fun entityAndReferencedItemTypeAsString(): String =
        "${entity.entityName.pascalCase}${referencedItem.itemName.pascalCase}"

    override fun determineAngularFormInitialValue(): String {
        return if (isList) {
            "[]"
        } else {
            throw RuntimeException("ItemUiItemAttributeTypeModel has no form initial value.") // should not occur
        }
    }

}

class EnumUiAttributeModel(
    entity: UiEntityDescriptionModel,
    item: UiItemDescriptionModel,
    attributeName: NameCase,
    isNullable: Boolean,
    isList: Boolean,
    val enum: UiEnumModel,
) : UiItemAttributeModel(
    entity = entity,
    item = item,
    attributeName = attributeName,
    isNullable = isNullable,
    isList = isList,
) {
    val enumId: EnumId = enum.enumId

    override val isItem: Boolean
        get() = false
    override val isBuiltIn: Boolean
        get() = false
    override val isEnum: Boolean
        get() = true

    override fun attributeTypeAsString(): String {
        return enumTypeAsString()
    }

    private fun enumTypeAsString(): String = this.enum.enumClassName

    override fun calculateAngularInitialValueFormSingleType(): String {
        return enumTypeAsString()
    }

    /**
     * Something like:
     * - `FormControl<string>`
     * - `FormControl<string | null>`
     * - `FormControl<AppellatioEnum>`
     * - `FormGroup<ArticulusInteriorFormPartGroup>`
     * Form values are always `null`based, not `undefined`.
     */
    override fun calculateAngularFormControlSingleType(): String {
        return "FormControl<${enumTypeAsString()}>"
    }

    override fun determineAngularFormInitialValue(): String {
        return enum.angularFormInitialValue
    }

}
