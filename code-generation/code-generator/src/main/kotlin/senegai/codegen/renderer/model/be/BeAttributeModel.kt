package senegai.codegen.renderer.model.be

import senegai.codegen.renderer.model.NameCase
import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EnumId

sealed class BeAttributeModel(
    val entity: BeEntityDescriptionModel,
    val item: BeItemDescriptionModel,
    val attributeName: NameCase,
    val isNullable: Boolean,
    val isList: Boolean,
    val hasCustomValidation: Boolean,
) {
    abstract val isItem: Boolean
    abstract val isBuiltIn: Boolean
    abstract val isEnum: Boolean

    val typescriptAttributeType: String
        get() = calculateAttributeTypeWithCardinality()

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
}


class BuiltInTypeBeAttributeModel(
    entity: BeEntityDescriptionModel,
    item: BeItemDescriptionModel,
    attributeName: NameCase,
    isNullable: Boolean,
    isList: Boolean,
    customValidation: Boolean,
    val builtInType: BuiltInType,
) : BeAttributeModel(
    entity = entity,
    item = item,
    attributeName = attributeName,
    isNullable = isNullable,
    isList = isList,
    hasCustomValidation = customValidation,
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


    private fun typescriptBuildInType(builtInType: BuiltInType): String {
        return when (builtInType) {
            BuiltInType.STRING -> "string"
            BuiltInType.NUMBER -> "number"
            BuiltInType.BOOLEAN -> "boolean"
        }
    }
}

class ItemBeIAttributeModel(
    entity: BeEntityDescriptionModel,
    item: BeItemDescriptionModel,
    attributeName: NameCase,
    isNullable: Boolean,
    isList: Boolean,
    customValidation: Boolean,
    val referencedItem: BeItemDescriptionModel,
) : BeAttributeModel(
    entity = entity,
    item = item,
    attributeName = attributeName,
    isNullable = isNullable,
    isList = isList,
    hasCustomValidation = customValidation,
) {
    // it is always the same entity as the parent entity, as references can only exist within entities
    val referencedEntity: BeEntityDescriptionModel = entity

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
}

class EnumBeAttributeModel(
    entity: BeEntityDescriptionModel,
    item: BeItemDescriptionModel,
    attributeName: NameCase,
    isNullable: Boolean,
    isList: Boolean,
    customValidation: Boolean,
    val enum: BeEnumModel,
) : BeAttributeModel(
    entity = entity,
    item = item,
    attributeName = attributeName,
    isNullable = isNullable,
    isList = isList,
    hasCustomValidation = customValidation,
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

    private fun enumTypeAsString(): String = this.enum.enumName.pascalCase

}
