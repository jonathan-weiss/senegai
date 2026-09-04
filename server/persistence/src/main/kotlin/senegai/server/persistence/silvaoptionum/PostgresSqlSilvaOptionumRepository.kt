package senegai.server.persistence.silvaoptionum

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import senegai.server.persistence.postgres.escapeForLikePattern
import senegai.server.persistence.postgres.getJsonb
import senegai.server.persistence.postgres.getJsonbOrNull
import senegai.server.persistence.postgres.toJsonb
import senegai.server.service.bo.AppellatioComis
import senegai.server.service.bo.ArticulusInteriorBO
import senegai.server.service.bo.SilvaOptionumBO
import senegai.server.service.bo.SilvaOptionumByIdsCriteriaBO
import senegai.server.service.bo.SilvaOptionumSearchCriteriaBO
import senegai.server.service.silvaoptionum.SilvaOptionumRepository
import java.time.LocalDate
import java.util.UUID

/**
 * PostgreSQL implementation of the [SilvaOptionumRepository] port, storing every
 * [SilvaOptionumBO] aggregate as one row of the SILVA_OPTIONUM table.
 *
 * Attributes without a flat relational representation — nested items, lists of nested items
 * and lists of built-in types — are stored as `jsonb`; all others get their own typed column.
 *
 * Only active when `senegai.persistence.type=postgres`; it then takes precedence over the
 * [senegai.server.persistence.silvaoptionum.InMemorySilvaOptionumRepository].
 */
@Repository
@Primary
@ConditionalOnProperty(name = ["senegai.persistence.type"], havingValue = "postgres")
class PostgresSqlSilvaOptionumRepository(
    private val jdbcClient: JdbcClient,
) : SilvaOptionumRepository {

    override fun findAll(): List<SilvaOptionumBO> =
        jdbcClient.sql("$SELECT_COLUMNS ORDER BY INDEX_UNICUS").query(rowMapper).list()

    override fun findById(indexUnicus: UUID): SilvaOptionumBO? =
        jdbcClient.sql("$SELECT_COLUMNS WHERE INDEX_UNICUS = :indexUnicus")
            .param("indexUnicus", indexUnicus)
            .query(rowMapper)
            .optional()
            .orElse(null)

    override fun findByIds(criteria: SilvaOptionumByIdsCriteriaBO): List<SilvaOptionumBO> {
        if (criteria.indexUnicusList.isEmpty()) {
            return emptyList()
        }
        val found = jdbcClient.sql("$SELECT_COLUMNS WHERE INDEX_UNICUS IN (:indexUnicusList)")
            .param("indexUnicusList", criteria.indexUnicusList)
            .query(rowMapper)
            .list()
            .associateBy { it.indexUnicus }
        return criteria.indexUnicusList.mapNotNull { found[it] }
    }

    override fun search(searchCriteria: SilvaOptionumSearchCriteriaBO): List<SilvaOptionumBO> =
        jdbcClient.sql("$SELECT_COLUMNS WHERE SILVA_OPTIONUM::text ILIKE :query ESCAPE '\\' ORDER BY INDEX_UNICUS")
            .param("query", "%${searchCriteria.query.escapeForLikePattern()}%")
            .query(rowMapper)
            .list()

    override fun save(silvaOptionum: SilvaOptionumBO): SilvaOptionumBO {
        jdbcClient.sql(UPSERT)
            .param("indexUnicus", silvaOptionum.indexUnicus)
            .param("campusTextusObligatorius", silvaOptionum.campusTextusObligatorius)
            .param("campusTextusOptionalis", silvaOptionum.campusTextusOptionalis)
            .param("appellatio", silvaOptionum.appellatio.name)
            .param("articulusInteriorSingularis", toJsonb(silvaOptionum.articulusInteriorSingularis))
            .param("articulusInteriorIteratus", toJsonb(silvaOptionum.articulusInteriorIteratus))
            .param("articulusInteriorSingularisOptionalis", toJsonb(silvaOptionum.articulusInteriorSingularisOptionalis))
            .param("articulusInteriorOptionalisIteratus", toJsonb(silvaOptionum.articulusInteriorOptionalisIteratus))
            .param("appellatioOptionalisIteratus", toJsonb(silvaOptionum.appellatioOptionalisIteratus))
            .param("campusDiei", silvaOptionum.campusDiei)
            .param("campusBivalens", silvaOptionum.campusBivalens)
            .param("campusNumerorum", silvaOptionum.campusNumerorum)
            .param("iteratioSimpliciumTextuum", toJsonb(silvaOptionum.iteratioSimpliciumTextuum))
            .param("relatioAdEntitatemOptionalisIteratus", toJsonb(silvaOptionum.relatioAdEntitatemOptionalisIteratus))
            .param("relatioAdEntitatemOptionalis", silvaOptionum.relatioAdEntitatemOptionalis)
            .update()
        return silvaOptionum
    }

    override fun deleteById(indexUnicus: UUID) {
        jdbcClient.sql("DELETE FROM SILVA_OPTIONUM WHERE INDEX_UNICUS = :indexUnicus")
            .param("indexUnicus", indexUnicus)
            .update()
    }

    override fun nextId(): UUID = UUID.randomUUID()

    private val rowMapper = RowMapper { resultSet, _ ->
        SilvaOptionumBO(
            indexUnicus = resultSet.getObject("INDEX_UNICUS", UUID::class.java),
            campusTextusObligatorius = resultSet.getString("CAMPUS_TEXTUS_OBLIGATORIUS"),
            campusTextusOptionalis = resultSet.getString("CAMPUS_TEXTUS_OPTIONALIS"),
            appellatio = AppellatioComis.valueOf(resultSet.getString("APPELLATIO")),
            articulusInteriorSingularis = resultSet.getJsonb("ARTICULUS_INTERIOR_SINGULARIS"),
            articulusInteriorIteratus = resultSet.getJsonb("ARTICULUS_INTERIOR_ITERATUS"),
            articulusInteriorSingularisOptionalis =
                resultSet.getJsonbOrNull<ArticulusInteriorBO>("ARTICULUS_INTERIOR_SINGULARIS_OPTIONALIS"),
            articulusInteriorOptionalisIteratus =
                resultSet.getJsonbOrNull<List<ArticulusInteriorBO>>("ARTICULUS_INTERIOR_OPTIONALIS_ITERATUS"),
            appellatioOptionalisIteratus =
                resultSet.getJsonbOrNull<List<AppellatioComis>>("APPELLATIO_OPTIONALIS_ITERATUS"),
            campusDiei = resultSet.getObject("CAMPUS_DIEI", LocalDate::class.java),
            campusBivalens = resultSet.getBoolean("CAMPUS_BIVALENS"),
            campusNumerorum = resultSet.getInt("CAMPUS_NUMERORUM"),
            iteratioSimpliciumTextuum = resultSet.getJsonb("ITERATIO_SIMPLICIUM_TEXTUUM"),
            relatioAdEntitatemOptionalisIteratus =
                resultSet.getJsonbOrNull<List<UUID>>("RELATIO_AD_ENTITATEM_OPTIONALIS_ITERATUS"),
            relatioAdEntitatemOptionalis = resultSet.getObject("RELATIO_AD_ENTITATEM_OPTIONALIS", UUID::class.java),
        )
    }

    private companion object {
        const val SELECT_COLUMNS = """
            SELECT INDEX_UNICUS,
                   CAMPUS_TEXTUS_OBLIGATORIUS,
                   CAMPUS_TEXTUS_OPTIONALIS,
                   APPELLATIO,
                   ARTICULUS_INTERIOR_SINGULARIS,
                   ARTICULUS_INTERIOR_ITERATUS,
                   ARTICULUS_INTERIOR_SINGULARIS_OPTIONALIS,
                   ARTICULUS_INTERIOR_OPTIONALIS_ITERATUS,
                   APPELLATIO_OPTIONALIS_ITERATUS,
                   CAMPUS_DIEI,
                   CAMPUS_BIVALENS,
                   CAMPUS_NUMERORUM,
                   ITERATIO_SIMPLICIUM_TEXTUUM,
                   RELATIO_AD_ENTITATEM_OPTIONALIS_ITERATUS,
                   RELATIO_AD_ENTITATEM_OPTIONALIS
              FROM SILVA_OPTIONUM
        """

        const val UPSERT = """
            INSERT INTO SILVA_OPTIONUM (
                INDEX_UNICUS,
                CAMPUS_TEXTUS_OBLIGATORIUS,
                CAMPUS_TEXTUS_OPTIONALIS,
                APPELLATIO,
                ARTICULUS_INTERIOR_SINGULARIS,
                ARTICULUS_INTERIOR_ITERATUS,
                ARTICULUS_INTERIOR_SINGULARIS_OPTIONALIS,
                ARTICULUS_INTERIOR_OPTIONALIS_ITERATUS,
                APPELLATIO_OPTIONALIS_ITERATUS,
                CAMPUS_DIEI,
                CAMPUS_BIVALENS,
                CAMPUS_NUMERORUM,
                ITERATIO_SIMPLICIUM_TEXTUUM,
                RELATIO_AD_ENTITATEM_OPTIONALIS_ITERATUS,
                RELATIO_AD_ENTITATEM_OPTIONALIS
            ) VALUES (
                :indexUnicus,
                :campusTextusObligatorius,
                :campusTextusOptionalis,
                :appellatio,
                CAST(:articulusInteriorSingularis AS jsonb),
                CAST(:articulusInteriorIteratus AS jsonb),
                CAST(:articulusInteriorSingularisOptionalis AS jsonb),
                CAST(:articulusInteriorOptionalisIteratus AS jsonb),
                CAST(:appellatioOptionalisIteratus AS jsonb),
                :campusDiei,
                :campusBivalens,
                :campusNumerorum,
                CAST(:iteratioSimpliciumTextuum AS jsonb),
                CAST(:relatioAdEntitatemOptionalisIteratus AS jsonb),
                :relatioAdEntitatemOptionalis
            )
            ON CONFLICT (INDEX_UNICUS) DO UPDATE SET
                CAMPUS_TEXTUS_OBLIGATORIUS = EXCLUDED.CAMPUS_TEXTUS_OBLIGATORIUS,
                CAMPUS_TEXTUS_OPTIONALIS = EXCLUDED.CAMPUS_TEXTUS_OPTIONALIS,
                APPELLATIO = EXCLUDED.APPELLATIO,
                ARTICULUS_INTERIOR_SINGULARIS = EXCLUDED.ARTICULUS_INTERIOR_SINGULARIS,
                ARTICULUS_INTERIOR_ITERATUS = EXCLUDED.ARTICULUS_INTERIOR_ITERATUS,
                ARTICULUS_INTERIOR_SINGULARIS_OPTIONALIS = EXCLUDED.ARTICULUS_INTERIOR_SINGULARIS_OPTIONALIS,
                ARTICULUS_INTERIOR_OPTIONALIS_ITERATUS = EXCLUDED.ARTICULUS_INTERIOR_OPTIONALIS_ITERATUS,
                APPELLATIO_OPTIONALIS_ITERATUS = EXCLUDED.APPELLATIO_OPTIONALIS_ITERATUS,
                CAMPUS_DIEI = EXCLUDED.CAMPUS_DIEI,
                CAMPUS_BIVALENS = EXCLUDED.CAMPUS_BIVALENS,
                CAMPUS_NUMERORUM = EXCLUDED.CAMPUS_NUMERORUM,
                ITERATIO_SIMPLICIUM_TEXTUUM = EXCLUDED.ITERATIO_SIMPLICIUM_TEXTUUM,
                RELATIO_AD_ENTITATEM_OPTIONALIS_ITERATUS = EXCLUDED.RELATIO_AD_ENTITATEM_OPTIONALIS_ITERATUS,
                RELATIO_AD_ENTITATEM_OPTIONALIS = EXCLUDED.RELATIO_AD_ENTITATEM_OPTIONALIS
        """
    }
}
