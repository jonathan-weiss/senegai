package senegai.codegen.validation

import senegai.model.schema.SchemaData

/** Every item name is in PascalCase. */
class ItemNamePascalCaseValidator : SchemaDataAspectValidator {
    override fun validate(schemaData: SchemaData, path: ValidationPath) {
        schemaData.items.forEachIndexed { index, item ->
            if (!PascalCase.isPascalCase(item.itemName)) {
                validationError(
                    path.child("items", index, item.itemName),
                    "The item name ${PascalCase.violationDescription(item.itemName)}",
                )
            }
        }
    }
}
