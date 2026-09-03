package senegai.codegen.validation

import senegai.model.schema.SchemaData

/** Every value of an enum type is in PascalCase. */
class EnumValuePascalCaseValidator : SchemaDataAspectValidator {
    override fun validate(schemaData: SchemaData, path: ValidationPath) {
        schemaData.enums.forEachIndexed { enumIndex, enumType ->
            val enumPath = path.child("enums", enumIndex, enumType.enumName)
            enumType.enumValues.forEachIndexed { valueIndex, enumValue ->
                if (!PascalCase.isPascalCase(enumValue)) {
                    validationError(
                        enumPath.child("enumValues", valueIndex, enumValue),
                        "The enum value ${PascalCase.violationDescription(enumValue)}",
                    )
                }
            }
        }
    }
}
