package senegai.codegen.renderer.model.be

import senegai.codegen.renderer.model.NameCase
import senegai.model.schema.BuiltInType
import senegai.model.schema.EnumId
import senegai.model.schema.ExampleDataCategory

sealed class BeAttributeModel(
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
     * Whether this attribute references another item by its primary key.
     * Such an attribute is a [BuiltInType.UUID] in every layer, therefore it is
     * also a built-in attribute ([isBuiltIn] is `true` as well).
     */
    open val isItemReference: Boolean
        get() = false

    /**
     * The example-data creator call to obtain a value for this attribute, respecting its cardinality:
     * `createList()` for list attributes, `create()` otherwise.
     */
    open val exampleDataCreatorCall: String
        get() = if (isList) "createList(dataContext)" else "create(dataContext)"

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


open class BuiltInTypeBeAttributeModel(
    item: BeItemDescriptionModel,
    attributeName: NameCase,
    isNullable: Boolean,
    isList: Boolean,
    customValidation: Boolean,
    val builtInType: BuiltInType,
    val exampleDataGeneratorConfig: BeExampleDataGeneratorConfig
) : BeAttributeModel(
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
            BuiltInType.UUID -> "UUID"
        }

    // built-in types are identical in the BO and WTO layers
    override fun wtoAttributeTypeAsString(): String = kotlinAttributeTypeAsString()

    override val exampleDataCreatorCall: String = exampleDataGeneratorConfig.exampleDataGeneratorCallExpression


    private fun builtInTypeAsString(): String =
        when (builtInType) {
            BuiltInType.STRING -> "string"
            BuiltInType.NUMBER -> "number"
            BuiltInType.BOOLEAN -> "boolean"
            BuiltInType.UUID -> "UUID"
        }
}

/**
 * An attribute that references another item by its primary key.
 *
 * The referenced item is always identified by a [BuiltInType.UUID], therefore this attribute
 * behaves exactly like a built-in UUID attribute in the business objects and in the transport
 * layer. What distinguishes it is [referencedItem]: it tells the templates which item has to
 * be searched and resolved to display the reference by its identifying attributes instead of
 * displaying the raw UUID.
 */
class ItemReferenceBeAttributeModel(
    item: BeItemDescriptionModel,
    attributeName: NameCase,
    isNullable: Boolean,
    isList: Boolean,
    customValidation: Boolean,
    val referencedItem: BeReferencedItemModel,
) : BuiltInTypeBeAttributeModel(
    item = item,
    attributeName = attributeName,
    isNullable = isNullable,
    isList = isList,
    customValidation = customValidation,
    builtInType = BuiltInType.UUID,
    // Example data is a random UUID for now; resolving it to a really existing item
    // is up to the templates that know how to fetch the referenced items.
    exampleDataGeneratorConfig = randomUuidExampleDataGeneratorConfig(isNullable = isNullable, isList = isList),
) {
    override val isItemReference: Boolean
        get() = true

    private companion object {
        private fun randomUuidExampleDataGeneratorConfig(isNullable: Boolean, isList: Boolean) =
            BeExampleDataGeneratorConfig(
                generatorNamePrefix = NameCase(ExampleDataCategory.RANDOM_UUID.generatorPrefixName),
                isNullable = isNullable,
                numberOfEntries = if (isList) 3 else 1,
            )
    }
}

class ItemBeIAttributeModel(
    item: BeItemDescriptionModel,
    attributeName: NameCase,
    isNullable: Boolean,
    isList: Boolean,
    customValidation: Boolean,
    val referencedItem: BeItemDescriptionModel,
) : BeAttributeModel(
    item = item,
    attributeName = attributeName,
    isNullable = isNullable,
    isList = isList,
    hasCustomValidation = customValidation,
) {
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
    item: BeItemDescriptionModel,
    attributeName: NameCase,
    isNullable: Boolean,
    isList: Boolean,
    customValidation: Boolean,
    val enum: BeEnumModel,
) : BeAttributeModel(
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
