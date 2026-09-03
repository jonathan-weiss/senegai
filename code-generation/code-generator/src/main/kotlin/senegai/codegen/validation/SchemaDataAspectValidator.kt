package senegai.codegen.validation

import senegai.model.schema.SchemaData

/**
 * Validates exactly one aspect of the [SchemaData] and throws a
 * [SchemaDataValidationException] as soon as that aspect is violated.
 *
 * The [path] is the breadcrumb of the [schemaData] itself, every validator
 * appends the way to the place it complains about.
 */
interface SchemaDataAspectValidator {
    fun validate(schemaData: SchemaData, path: ValidationPath)
}
