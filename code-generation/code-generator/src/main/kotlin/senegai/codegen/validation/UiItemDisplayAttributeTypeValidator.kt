package senegai.codegen.validation

import senegai.model.schema.BuiltInType
import senegai.model.schema.EnumId
import senegai.model.schema.SchemaData

/**
 * The ui configuration of an item shows only attributes of a built-in type or of an enum
 * type as its display attributes, because a display attribute is rendered as a plain
 * string wherever a reference to the item is shown.
 */
class UiItemDisplayAttributeTypeValidator : SchemaDataAspectValidator {
    override fun validate(schemaData: SchemaData, path: ValidationPath) {
        schemaData.uiItems.forEachIndexed { uiItemIndex, uiItem ->
            val uiItemPath = path.child("uiItems", uiItemIndex, uiItem.itemId.itemName)
            val item = schemaData.items.firstOrNull { it.itemId == uiItem.itemId } ?: return@forEachIndexed

            uiItem.displayAttributeNames.forEachIndexed { attributeIndex, attributeName ->
                val attribute = item.attributes.firstOrNull { it.attributeName == attributeName }
                    ?: return@forEachIndexed
                if (attribute.type !is BuiltInType && attribute.type !is EnumId) {
                    validationError(
                        uiItemPath.child("displayAttributeNames", attributeIndex, attributeName),
                        "The ui configuration shows '$attributeName' of the item '${item.itemName}' as a " +
                                "display attribute, but that attribute is of ${attribute.type.description}. " +
                                "A display attribute is only an attribute of a built-in type or of an enum type.",
                    )
                }
            }
        }
    }
}
