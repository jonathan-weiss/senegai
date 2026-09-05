package senegai.server.persistence.membrumrelatum

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import senegai.server.persistence.postgres.escapeForLikePattern
import senegai.server.service.bo.MembrumRelatumBO
import senegai.server.service.bo.MembrumRelatumByIdsCriteriaBO
import senegai.server.service.bo.MembrumRelatumSearchCriteriaBO
import senegai.server.service.membrumrelatum.MembrumRelatumRepository
import java.util.*

private const val TABLE_NAME = "MEMBRUM_RELATUM"
private const val PRIMARY_KEY_COLUMN_NAME = "CLAVIS_PRIMARIA"

/**
 * PostgreSQL implementation of the [MembrumRelatumRepository] port, storing every
 * [MembrumRelatumBO] aggregate as one row of the MEMBRUM_RELATUM table.
 *
 * Only active when `senegai.persistence.type=postgres`; it then takes precedence over the
 * [senegai.server.persistence.membrumrelatum.InMemoryMembrumRelatumRepository].
 */
@Repository
@Primary
@ConditionalOnProperty(name = ["senegai.persistence.type"], havingValue = "postgres")
class PostgresSqlMembrumRelatumRepository(
    private val jdbcClient: JdbcClient,
) : MembrumRelatumRepository {

    override fun findAll(): List<MembrumRelatumBO> =
        jdbcClient.sql("$selectColumns ORDER BY $PRIMARY_KEY_COLUMN_NAME").query(rowMapper).list()

    override fun findById(clavisPrimaria: UUID): MembrumRelatumBO? =
        jdbcClient.sql("$selectColumns WHERE $PRIMARY_KEY_COLUMN_NAME = :primaryKeyValue")
            .param("primaryKeyValue", clavisPrimaria)
            .query(rowMapper)
            .optional()
            .orElse(null)

    override fun findByIds(criteria: MembrumRelatumByIdsCriteriaBO): List<MembrumRelatumBO> {
        if (criteria.clavisPrimariaList.isEmpty()) {
            return emptyList()
        }
        val found = jdbcClient.sql("$selectColumns WHERE $PRIMARY_KEY_COLUMN_NAME IN (:primaryKeyValues)")
            .param("primaryKeyValues", criteria.clavisPrimariaList)
            .query(rowMapper)
            .list()
            .associateBy { it.clavisPrimaria }
        return criteria.clavisPrimariaList.mapNotNull { found[it] }
    }

    override fun search(searchCriteria: MembrumRelatumSearchCriteriaBO): List<MembrumRelatumBO> =
        jdbcClient.sql("$selectColumns WHERE $TABLE_NAME::text ILIKE :query ESCAPE '\\' ORDER BY $PRIMARY_KEY_COLUMN_NAME")
            .param("query", "%${searchCriteria.query.escapeForLikePattern()}%")
            .query(rowMapper)
            .list()

    override fun save(membrumRelatum: MembrumRelatumBO): MembrumRelatumBO {
        jdbcClient.sql(upsertStatement)
            .param("clavisPrimaria", membrumRelatum.clavisPrimaria)
            .param("descriptioExDistanti", membrumRelatum.descriptioExDistanti)
            .update()
        return membrumRelatum
    }

    override fun deleteById(clavisPrimaria: UUID) {
        jdbcClient.sql("DELETE FROM $TABLE_NAME WHERE $PRIMARY_KEY_COLUMN_NAME = :primaryKeyValue")
            .param("primaryKeyValue", clavisPrimaria)
            .update()
    }

    private val rowMapper = RowMapper { resultSet, _ ->
        MembrumRelatumBO(
            clavisPrimaria = resultSet.getObject("CLAVIS_PRIMARIA", UUID::class.java),
            descriptioExDistanti = resultSet.getString("DESCRIPTIO_EX_DISTANTI"),
        )
    }

    private val selectColumns: String
        get() {
            // because we need kotlin comments between the lines, we can not use kotlins multiline-comments
            val sb = StringBuilder()
            sb.append("SELECT")
            sb.append("    ${PRIMARY_KEY_COLUMN_NAME},")
            sb.append("    DESCRIPTIO_EX_DISTANTI,")
            sb.append("FROM $TABLE_NAME")
            return sb.toString()
        }
    private val upsertStatement: String
        get() {
            // because we need kotlin comments between the lines, we can not use kotlins multiline-comments
            val sb = StringBuilder()
            sb.append("INSERT INTO $TABLE_NAME (")
            sb.append("    ${PRIMARY_KEY_COLUMN_NAME},")
            sb.append("    DESCRIPTIO_EX_DISTANTI,")
            sb.append(") VALUES (")
            sb.append("    :clavisPrimaria,")
            sb.append("    :descriptioExDistanti")
            sb.append(")")
            sb.append("ON CONFLICT (${PRIMARY_KEY_COLUMN_NAME}) DO UPDATE SET")
            sb.append("     DESCRIPTIO_EX_DISTANTI = EXCLUDED.DESCRIPTIO_EX_DISTANTI")
            return sb.toString()
        }
}
