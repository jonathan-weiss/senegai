package senegai.codegen.validation

import senegai.model.schema.BuiltInType
import senegai.model.schema.EnumId
import senegai.model.schema.SchemaData

/**
 * The search result of a ui entity shows only attributes of a built-in type or of an
 * enum type, because a search result column renders its value as a plain string.
 */
class SearchResultViewAttributeTypeValidator : SchemaDataAspectValidator {
    override fun validate(schemaData: SchemaData, path: ValidationPath) {
        schemaData.uiEntities.forEachIndexed { entityIndex, uiEntity ->
            val searchResultViewPath = path
                .child("uiEntities", entityIndex, uiEntity.uiEntityName)
                .child("searchResultView")
            val rootItem = uiEntity.rootItem

            uiEntity.searchResultView.attributeNames.forEachIndexed { attributeIndex, attributeName ->
                val attribute = rootItem.attributes.firstOrNull { it.attributeName == attributeName }
                    ?: return@forEachIndexed
                if (attribute.type !is BuiltInType && attribute.type !is EnumId) {
                    validationError(
                        searchResultViewPath.child("attributeNames", attributeIndex, attributeName),
                        "The search result shows the attribute '$attributeName' of the root item " +
                                "'${rootItem.itemName}', but that attribute is of ${attribute.type.description}. " +
                                "A search result only shows an attribute of a built-in type or of an enum type.",
                    )
                }
            }
        }
    }
}
