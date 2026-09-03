package senegai.codegen.validation

import senegai.model.schema.SchemaData

/** Every attribute name of an item is in PascalCase. */
class ItemAttributeNamePascalCaseValidator : SchemaDataAspectValidator {
    override fun validate(schemaData: SchemaData, path: ValidationPath) {
        schemaData.items.forEachIndexed { itemIndex, item ->
            val itemPath = path.child("items", itemIndex, item.itemName)
            item.attributes.forEachIndexed { attributeIndex, attribute ->
                if (!PascalCase.isPascalCase(attribute.attributeName)) {
                    validationError(
                        itemPath.child("attributes", attributeIndex, attribute.attributeName),
                        "The attribute name ${PascalCase.violationDescription(attribute.attributeName)}",
                    )
                }
            }
        }
    }
}
