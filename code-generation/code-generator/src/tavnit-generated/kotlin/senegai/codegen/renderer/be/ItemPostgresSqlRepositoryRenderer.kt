/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.be

import senegai.codegen.renderer.model.be.BeItemModel

/**
 * Generate the content for the template `ItemPostgresSqlRepositoryRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `PostgresSqlSilvaOptionumRepository.kt`
 * - path: `senegai/server/persistence/silvaoptionum/PostgresSqlSilvaOptionumRepository.kt`
 */
object ItemPostgresSqlRepositoryRenderer : BeItemRenderer {

    override fun renderTemplate(model: BeItemModel): String {
        return """
          |package senegai.server.persistence.${model.itemName.lowerCase}
          |
          |import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
          |import org.springframework.context.annotation.Primary
          |import org.springframework.jdbc.core.RowMapper
          |import org.springframework.jdbc.core.simple.JdbcClient
          |import org.springframework.stereotype.Repository
          |import senegai.server.persistence.postgres.columnValue
          |import senegai.server.persistence.postgres.escapeForLikePattern
          |import senegai.server.persistence.postgres.paramValue
          |import senegai.server.service.bo.${model.itemName.pascalCase}BO
          |import senegai.server.service.bo.${model.itemName.pascalCase}ByIdsCriteriaBO
          |import senegai.server.service.bo.${model.itemName.pascalCase}SearchCriteriaBO
          |import senegai.server.service.${model.itemName.lowerCase}.${model.itemName.pascalCase}Repository
          |${ if(model.hasUuidPrimaryKey) { """import java.util.${model.primaryKeyAttribute.kotlinAttributeType}
              |""" } else { """""" } }
          |private const val TABLE_NAME = "${model.table.tableName}"
          |private const val PRIMARY_KEY_COLUMN_NAME = "${model.table.primaryKeyColumn.columnName}"
          |/**
          | * PostgreSQL implementation of the [${model.itemName.pascalCase}Repository] port, storing every
          | * [${model.itemName.pascalCase}BO] aggregate as one row of the ${model.table.tableName} table.
          | *
          | * Attributes without a flat relational representation — nested items, lists of nested items
          | * and lists of built-in types — are stored as `jsonb`; all others get their own typed column.
          | *
          | * Only active when `senegai.persistence.type=postgres`; it then takes precedence over the
          | * [senegai.server.persistence.${model.itemName.lowerCase}.InMemory${model.itemName.pascalCase}Repository].
          | */
          |@Repository
          |@Primary
          |@ConditionalOnProperty(name = ["senegai.persistence.type"], havingValue = "postgres")
          |class PostgresSql${model.itemName.pascalCase}Repository(
          |    private val jdbcClient: JdbcClient,
          |) : ${model.itemName.pascalCase}Repository {
          |
          |
          |    override fun findAll(): List<${model.itemName.pascalCase}BO> =
          |        jdbcClient.sql("${"$"}selectColumns ORDER BY ${"$"}PRIMARY_KEY_COLUMN_NAME").query(rowMapper).list()
          |
          |    override fun findById(${model.primaryKeyAttribute.attributeName.camelCase}: ${model.primaryKeyAttribute.kotlinAttributeType}): ${model.itemName.pascalCase}BO? =
          |        jdbcClient.sql("${"$"}selectColumns WHERE ${"$"}PRIMARY_KEY_COLUMN_NAME = :primaryKeyValue")
          |            .param("primaryKeyValue", ${model.primaryKeyAttribute.attributeName.camelCase})
          |            .query(rowMapper)
          |            .optional()
          |            .orElse(null)
          |
          |    override fun findByIds(criteria: ${model.itemName.pascalCase}ByIdsCriteriaBO): List<${model.itemName.pascalCase}BO> {
          |        if (criteria.${model.primaryKeyAttribute.attributeName.camelCase}List.isEmpty()) {
          |            return emptyList()
          |        }
          |        val found = jdbcClient.sql("${"$"}selectColumns WHERE ${"$"}PRIMARY_KEY_COLUMN_NAME IN (:primaryKeyValues)")
          |            .param("primaryKeyValues", criteria.${model.primaryKeyAttribute.attributeName.camelCase}List)
          |            .query(rowMapper)
          |            .list()
          |            .associateBy { it.${model.primaryKeyAttribute.attributeName.camelCase} }
          |        return criteria.${model.primaryKeyAttribute.attributeName.camelCase}List.mapNotNull { found[it] }
          |    }
          |
          |    override fun search(searchCriteria: ${model.itemName.pascalCase}SearchCriteriaBO): List<${model.itemName.pascalCase}BO> =
          |        jdbcClient.sql("${"$"}selectColumns WHERE ${"$"}TABLE_NAME::text ILIKE :query ESCAPE '\\' ORDER BY ${"$"}PRIMARY_KEY_COLUMN_NAME")
          |            .param("query", "%${"$"}{searchCriteria.query.escapeForLikePattern()}%")
          |            .query(rowMapper)
          |            .list()
          |
          |    override fun save(${model.itemName.camelCase}: ${model.itemName.pascalCase}BO): ${model.itemName.pascalCase}BO {
          |        jdbcClient.sql(upsertStatement)
          |${ model.attributes.joinToString("") { attribute ->  """            .param("${attribute.attributeName.camelCase}", paramValue(${model.itemName.camelCase}.${attribute.attributeName.camelCase}))
              |""" } }            .update()
          |        return ${model.itemName.camelCase}
          |    }
          |
          |    override fun deleteById(${model.primaryKeyAttribute.attributeName.camelCase}: ${model.primaryKeyAttribute.kotlinAttributeType}) {
          |        jdbcClient.sql("DELETE FROM ${"$"}TABLE_NAME WHERE ${"$"}PRIMARY_KEY_COLUMN_NAME = :indexValue")
          |            .param("indexValue", ${model.primaryKeyAttribute.attributeName.camelCase})
          |            .update()
          |    }
          |
          |${ if(model.hasGeneratedPrimaryKey) { """    override fun nextId(): ${model.primaryKeyAttribute.kotlinAttributeType} = ${model.databaseNextPrimaryKeyValueExpression}
              |""" } else { """""" } }
          |    private val rowMapper = RowMapper { resultSet, _ ->
          |        ${model.itemName.pascalCase}BO(
          |${ model.attributes.joinToString("") { attribute ->  """            ${attribute.attributeName.camelCase} = resultSet.columnValue("${attribute.column.columnName}"),
              |""" } }        )
          |    }
          |
          |    private val selectColumns: String
          |        get() {
          |            // because we need kotlin comments between the lines, we can not use kotlins multiline-comments
          |            val sb = StringBuilder()
          |            sb.appendLine("SELECT")
          |${ model.table.columnsWithoutPrimaryKey.joinToString("") { column ->  """            sb.appendLine("    ${column.columnName},")
              |""" } }            // the primary key is rendered last, so that every looped line above can carry a comma
          |            sb.appendLine("    ${"$"}PRIMARY_KEY_COLUMN_NAME")
          |            sb.appendLine("FROM ${"$"}TABLE_NAME")
          |            return sb.toString()
          |    }
          |    private val upsertStatement: String
          |        get() {
          |            // because we need kotlin comments between the lines, we can not use kotlins multiline-comments
          |            val sb = StringBuilder()
          |            sb.appendLine("INSERT INTO ${"$"}TABLE_NAME (")
          |${ model.table.columnsWithoutPrimaryKey.joinToString("") { column ->  """            sb.appendLine("    ${column.columnName},")
              |""" } }            sb.appendLine("    ${"$"}PRIMARY_KEY_COLUMN_NAME")
          |            sb.appendLine(") VALUES (")
          |${ model.table.columnsWithoutPrimaryKey.joinToString("") { column ->  """${ if(column.isJsonb) { """            sb.appendLine("    CAST(:${column.attributeName.camelCase} AS jsonb),")
                  |""" } else { """            sb.appendLine("    :${column.attributeName.camelCase},")
                  |""" } }""" } }            sb.appendLine("    :${model.primaryKeyAttribute.attributeName.camelCase}")
          |            sb.appendLine(")")
          |            sb.appendLine("ON CONFLICT (${"$"}PRIMARY_KEY_COLUMN_NAME) DO UPDATE SET")
          |${ model.table.columnsWithoutPrimaryKey.joinToString("") { column ->  """            sb.appendLine("     ${column.columnName} = EXCLUDED.${column.columnName},")
              |""" } }            // assigning the key to itself is a no-op and keeps the last line free of a comma
          |            sb.appendLine("     ${"$"}PRIMARY_KEY_COLUMN_NAME = EXCLUDED.${"$"}PRIMARY_KEY_COLUMN_NAME")
          |            return sb.toString()
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: BeItemModel): String {
      return "senegai/server/persistence/${model.itemName.lowerCase}/PostgresSql${model.itemName.pascalCase}Repository.kt"
    }
}