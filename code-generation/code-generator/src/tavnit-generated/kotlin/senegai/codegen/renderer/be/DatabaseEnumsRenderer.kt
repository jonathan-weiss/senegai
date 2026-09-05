/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeEnumModel

/**
 * Generate the content for the template `DatabaseEnumsRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `DatabaseEnums.kt`
 * - path: `senegai/server/persistence/postgres/DatabaseEnums.kt`
 */
object DatabaseEnumsRenderer : BeEnumsRenderer {

    override fun renderTemplate(models: List<BeEnumModel>): String {
        return """
          |package senegai.server.persistence.postgres
          |
          |${ models.joinToString("") { enumType ->  """import senegai.server.service.bo.${enumType.enumName.pascalCase}
              |""" } }
          |/**
          | * How the values of every business enum are spelled in the database.
          | */
          |internal val GENERATED_DATABASE_ENUMS: List<DatabaseEnum> = listOf(
          |${ models.joinToString("") { enumType ->  """    DatabaseEnum(
              |        enumClass = ${enumType.enumName.pascalCase}::class.java,
              |        databaseValueByEnumValue = mapOf(
              |${ enumType.dbEnum.values.joinToString("") { enumValue ->  """            ${enumType.enumName.pascalCase}.${enumValue.enumValue.screamingSnakeCase} to "${enumValue.databaseValue}",
                  |""" } }        ),
              |    ),
              |""" } })
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(models: List<BeEnumModel>): String {
      return "senegai/server/persistence/postgres/GeneratedDatabaseEnums.kt"
    }
}