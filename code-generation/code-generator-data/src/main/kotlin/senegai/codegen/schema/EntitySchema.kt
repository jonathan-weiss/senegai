package senegai.codegen.schema

/**
 * All types an [ItemAttribute]'s type can
 * be, have implemented this interface.
 */
sealed interface ItemAttributeType

/**
 * An [EntityId] is globally unique.
 */
data class EntityId(val entityName: String): ItemAttributeType

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
) {
    val entityName: String = entityId.entityName
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

enum class BuiltInType: ItemAttributeType {
    STRING,
    NUMBER,
    BOOLEAN,
}


/**
 * An [ItemId] is only used to wire the
 * different items and entities together.
 */
interface ItemId: ItemAttributeType {
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
    val cardinality: ItemAttributeCardinality,
    val type: ItemAttributeType,
)

enum class ItemAttributeCardinality {
    ZERO_TO_ONE, // = nullable
    EXACTLY_ONE, // = non-nullable
    ZERO_TO_MANY, // = list
}

