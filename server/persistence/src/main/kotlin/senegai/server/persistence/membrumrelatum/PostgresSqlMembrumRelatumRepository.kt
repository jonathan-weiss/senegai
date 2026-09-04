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
import java.util.UUID

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
        jdbcClient.sql("$SELECT_COLUMNS ORDER BY CLAVIS_PRIMARIA").query(rowMapper).list()

    override fun findById(clavisPrimaria: UUID): MembrumRelatumBO? =
        jdbcClient.sql("$SELECT_COLUMNS WHERE CLAVIS_PRIMARIA = :clavisPrimaria")
            .param("clavisPrimaria", clavisPrimaria)
            .query(rowMapper)
            .optional()
            .orElse(null)

    override fun findByIds(criteria: MembrumRelatumByIdsCriteriaBO): List<MembrumRelatumBO> {
        if (criteria.clavisPrimariaList.isEmpty()) {
            return emptyList()
        }
        val found = jdbcClient.sql("$SELECT_COLUMNS WHERE CLAVIS_PRIMARIA IN (:clavisPrimariaList)")
            .param("clavisPrimariaList", criteria.clavisPrimariaList)
            .query(rowMapper)
            .list()
            .associateBy { it.clavisPrimaria }
        return criteria.clavisPrimariaList.mapNotNull { found[it] }
    }

    override fun search(searchCriteria: MembrumRelatumSearchCriteriaBO): List<MembrumRelatumBO> =
        jdbcClient.sql("$SELECT_COLUMNS WHERE MEMBRUM_RELATUM::text ILIKE :query ESCAPE '\\' ORDER BY CLAVIS_PRIMARIA")
            .param("query", "%${searchCriteria.query.escapeForLikePattern()}%")
            .query(rowMapper)
            .list()

    override fun save(membrumRelatum: MembrumRelatumBO): MembrumRelatumBO {
        jdbcClient.sql(UPSERT)
            .param("clavisPrimaria", membrumRelatum.clavisPrimaria)
            .param("descriptioExDistanti", membrumRelatum.descriptioExDistanti)
            .update()
        return membrumRelatum
    }

    override fun deleteById(clavisPrimaria: UUID) {
        jdbcClient.sql("DELETE FROM MEMBRUM_RELATUM WHERE CLAVIS_PRIMARIA = :clavisPrimaria")
            .param("clavisPrimaria", clavisPrimaria)
            .update()
    }

    private val rowMapper = RowMapper { resultSet, _ ->
        MembrumRelatumBO(
            clavisPrimaria = resultSet.getObject("CLAVIS_PRIMARIA", UUID::class.java),
            descriptioExDistanti = resultSet.getString("DESCRIPTIO_EX_DISTANTI"),
        )
    }

    private companion object {
        const val SELECT_COLUMNS = """
            SELECT CLAVIS_PRIMARIA, DESCRIPTIO_EX_DISTANTI
              FROM MEMBRUM_RELATUM
        """

        const val UPSERT = """
            INSERT INTO MEMBRUM_RELATUM (CLAVIS_PRIMARIA, DESCRIPTIO_EX_DISTANTI)
            VALUES (:clavisPrimaria, :descriptioExDistanti)
            ON CONFLICT (CLAVIS_PRIMARIA) DO UPDATE SET
                DESCRIPTIO_EX_DISTANTI = EXCLUDED.DESCRIPTIO_EX_DISTANTI
        """
    }
}
