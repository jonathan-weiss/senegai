package senegai.codegen.schema

/**
 * All types an [ItemAttribute]'s type can
 * be, have implemented this interface.
 */
sealed interface ItemAttributeType

/**
 * An [EntityId] is globally unique.
 */
interface EntityId : ItemAttributeType {
    val entityName: String
}

/**
 * An [Entity] is an [Item] that
 * can be identified by an identifier (like a
 * primary key in the database).
 * Therefore, one can search for Entities
 * and they can be referenced from other
 * [Item].
 */
data class Entity(
    val entityId: EntityId,
    val item: Item,
    val idAttributeName: String,
) {
    val entityName: String = entityId.entityName

    /**
     * The attribute of the root [item] that identifies
     * this entity (like a primary key in the database).
     */
    val idAttribute: ItemAttribute = item.attributes
        .singleOrNull { it.attributeName == idAttributeName }
        ?: throw IllegalArgumentException(
            "The entity '$entityName' declares '$idAttributeName' as its identifying attribute, " +
                    "but the entity root item '${item.itemName}' has no such attribute. " +
                    "Available attributes are ${item.attributes.map { it.attributeName }}."
        )
}

/**
 * An [EntityId] is globally unique.
 */
interface EnumId : ItemAttributeType {
    val enumName: String
}

/**
 * An [EnumType] defines an enumeration
 * and all its values.
 */
data class EnumType(
    val enumId: EnumId,
    val enumValues: List<String>,
) {
    val enumName: String = enumId.enumName
}

enum class BuiltInType : ItemAttributeType {
    STRING,
    NUMBER,
    BOOLEAN,
}

enum class ExampleDataCategory(val generatorPrefixName: String, val supportedBuiltInType: BuiltInType) {
    RANDOM_TEXT("RandomString", BuiltInType.STRING),
    RANDOM_NUMBER("RandomNumber",BuiltInType.NUMBER),
    RANDOM_BOOLEAN("RandomBoolean",BuiltInType.BOOLEAN),
    FIRSTNAME("FirstnameString",BuiltInType.STRING),
    LASTNAME("LastnameString",BuiltInType.STRING),
    AGE("AgeNumber",BuiltInType.NUMBER),
    STREET("StreetAndNumberString",BuiltInType.STRING),
    POSTCODE("PostcodeString",BuiltInType.STRING),
    CITY("CityString",BuiltInType.STRING),
    COUNTRY_ISO("CountryIsoCodeString",BuiltInType.STRING),
    COUNTRY_NAME("CountryNameString",BuiltInType.STRING),
}

/**
 * An [ItemId] is only used to wire the
 * different items and entities together.
 */
interface ItemId : ItemAttributeType {
    val itemName: String
}

/**
 * An [Item] is named list of attributes.
 * In contrast to [Entity], an [Item] has
 * no identifier and can therefore not be referenced
 * by an identifier, only by a direct instance
 * reference or a position index (e.g. in a list).
 */
data class Item(
    val itemId: ItemId,
    val attributes: List<ItemAttribute>,
) {
    val itemName: String = itemId.itemName
}

/**
 * A named field of an [Item] with a type,
 * e.g. to model a field like `myStrings: List<String>`.
 */
data class ItemAttribute(
    val attributeName: String,
    val isNullable: Boolean,
    val isMultiple: Boolean,
    val type: ItemAttributeType,
    val customValidation: Boolean = false,
    val exampleDataCategory: ExampleDataCategory?
)

