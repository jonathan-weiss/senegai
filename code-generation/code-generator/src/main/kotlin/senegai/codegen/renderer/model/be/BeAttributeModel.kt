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

    /**
     * The example-data creator call to obtain a value for this attribute, respecting its cardinality:
     * `createList()` for list attributes, `create()` otherwise.
     */
    val exampleDataCreatorCall: String
        get() = if (isList) "createList()" else "create()"

    val typescriptAttributeType: String
        get() = calculateAttributeTypeWithCardinality()

    /**
     * The Kotlin type of this attribute as used in the business objects, respecting its cardinality,
     * e.g. `String`, `Int?`, `List<ArticulusInteriorBO>` or `List<AppellatioComis>?`.
     */
    val kotlinAttributeType: String
        get() = withKotlinCardinality(kotlinAttributeTypeAsString())

    /**
     * The Kotlin type of this attribute as used in the WTOs (transport layer), respecting its
     * cardinality, e.g. `String`, `Int?`, `List<ArticulusInteriorWTO>` or `List<AppellatioComisEnum>?`.
     */
    val wtoAttributeType: String
        get() = withKotlinCardinality(wtoAttributeTypeAsString())

    /**
     * The suffix that maps this attribute's WTO value to its BO value, respecting cardinality and
     * nullability: e.g. `` (built-ins, identity), `.toBo()`, `?.toBo()`, `.map { it.toBo() }` or
     * `?.map { it.toBo() }`.
     */
    val toBoMappingSuffix: String
        get() = mappingSuffix("toBo")

    /** The counterpart of [toBoMappingSuffix] mapping a BO value to its WTO value (`toWto`). */
    val toWtoMappingSuffix: String
        get() = mappingSuffix("toWto")

    protected abstract fun attributeTypeAsString(): String

    protected abstract fun kotlinAttributeTypeAsString(): String

    protected abstract fun wtoAttributeTypeAsString(): String


    private fun mappingSuffix(conversion: String): String = when {
        isBuiltIn -> "" // built-in types are transported as-is, no conversion needed
        !isList && !isNullable -> ".$conversion()"
        !isList && isNullable -> "?.$conversion()"
        isList && !isNullable -> ".map { it.$conversion() }"
        else -> "?.map { it.$conversion() }"
    }

    private fun calculateAttributeTypeWithCardinality(): String {
        val type = attributeTypeAsString()
        return when {
            !isList && isNullable -> "$type | null"
            !isList && !isNullable -> type
            isList && !isNullable -> "Array<$type>"
            else -> "Array<$type> | null"
        }
    }

    private fun withKotlinCardinality(type: String): String = when {
        !isList && isNullable -> "$type?"
        !isList && !isNullable -> type
        isList && !isNullable -> "List<$type>"
        else -> "List<$type>?"
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

    override fun kotlinAttributeTypeAsString(): String =
        when (builtInType) {
            BuiltInType.STRING -> "String"
            BuiltInType.NUMBER -> "Int"
            BuiltInType.BOOLEAN -> "Boolean"
        }

    // built-in types are identical in the BO and WTO layers
    override fun wtoAttributeTypeAsString(): String = kotlinAttributeTypeAsString()

    /**
     * A representative Kotlin example value literal for this attribute, respecting its cardinality.
     * For example `"exemplum"`, `42`, `true` or, for list attributes, `listOf("exemplum")`.
     */
    val kotlinExampleValue: String
        get() = if (isList) "listOf(${singleKotlinExampleValue()})" else singleKotlinExampleValue()

    private fun singleKotlinExampleValue(): String =
        when (builtInType) {
            BuiltInType.STRING -> "\"exemplum\""
            BuiltInType.NUMBER -> "42"
            BuiltInType.BOOLEAN -> "true"
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

    override fun kotlinAttributeTypeAsString(): String = "${referencedItemTypeAsString()}BO"

    override fun wtoAttributeTypeAsString(): String = "${referencedItemTypeAsString()}WTO"

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

    override fun kotlinAttributeTypeAsString(): String = enumTypeAsString()

    override fun wtoAttributeTypeAsString(): String = "${enumTypeAsString()}Enum"

    private fun enumTypeAsString(): String = this.enum.enumName.pascalCase

}
