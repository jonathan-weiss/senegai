package senegai.codegen.validation

import senegai.model.schema.BuiltInType
import senegai.model.schema.SchemaData

/**
 * An item is identified by at most one attribute, and that attribute is a single
 * mandatory [BuiltInType.UUID] (like a primary key in the database).
 */
class ItemPrimaryKeyValidator : SchemaDataAspectValidator {
    override fun validate(schemaData: SchemaData, path: ValidationPath) {
        schemaData.items.forEachIndexed { itemIndex, item ->
            val itemPath = path.child("items", itemIndex, item.itemName)
            val primaryKeyAttributes = item.attributes.withIndex().filter { (_, attribute) -> attribute.isPrimaryKey }

            if (primaryKeyAttributes.size > 1) {
                val declarations = primaryKeyAttributes
                    .joinToString { (index, attribute) -> "attributes[$index] '${attribute.attributeName}'" }
                validationError(
                    itemPath,
                    "The item declares ${primaryKeyAttributes.size} attributes as its primary key, namely " +
                            "$declarations. An item is identified by one single attribute.",
                )
            }

            val (primaryKeyIndex, primaryKey) = primaryKeyAttributes.singleOrNull() ?: return@forEachIndexed
            val primaryKeyPath = itemPath.child("attributes", primaryKeyIndex, primaryKey.attributeName)

            if (primaryKey.type != BuiltInType.UUID) {
                validationError(
                    primaryKeyPath,
                    "The attribute identifies its item, but it is of ${primaryKey.type.description}. " +
                            "An item is only identified by an attribute of the built-in type ${BuiltInType.UUID}.",
                )
            }
            if (primaryKey.isNullable || primaryKey.isMultiple) {
                validationError(
                    primaryKeyPath,
                    "The attribute identifies its item, but it is declared as " +
                            "nullable=${primaryKey.isNullable} and multiple=${primaryKey.isMultiple}. " +
                            "An item is only identified by a single mandatory attribute.",
                )
            }
        }
    }
}
