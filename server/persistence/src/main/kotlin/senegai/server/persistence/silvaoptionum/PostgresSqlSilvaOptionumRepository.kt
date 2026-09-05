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

private const val TABLE_NAME = "SILVA_OPTIONUM"
private const val PRIMARY_KEY_COLUMN_NAME = "INDEX_UNICUS"
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
        jdbcClient.sql("$selectColumns ORDER BY $PRIMARY_KEY_COLUMN_NAME").query(rowMapper).list()

    override fun findById(indexUnicus: UUID): SilvaOptionumBO? =
        jdbcClient.sql("$selectColumns WHERE $PRIMARY_KEY_COLUMN_NAME = :primaryKeyValue")
            .param("primaryKeyValue", indexUnicus)
            .query(rowMapper)
            .optional()
            .orElse(null)

    override fun findByIds(criteria: SilvaOptionumByIdsCriteriaBO): List<SilvaOptionumBO> {
        if (criteria.indexUnicusList.isEmpty()) {
            return emptyList()
        }
        val found = jdbcClient.sql("$selectColumns WHERE $PRIMARY_KEY_COLUMN_NAME IN (:primaryKeyValues)")
            .param("primaryKeyValues", criteria.indexUnicusList)
            .query(rowMapper)
            .list()
            .associateBy { it.indexUnicus }
        return criteria.indexUnicusList.mapNotNull { found[it] }
    }

    override fun search(searchCriteria: SilvaOptionumSearchCriteriaBO): List<SilvaOptionumBO> =
        jdbcClient.sql("$selectColumns WHERE $TABLE_NAME::text ILIKE :query ESCAPE '\\' ORDER BY $PRIMARY_KEY_COLUMN_NAME")
            .param("query", "%${searchCriteria.query.escapeForLikePattern()}%")
            .query(rowMapper)
            .list()

    override fun save(silvaOptionum: SilvaOptionumBO): SilvaOptionumBO {
        jdbcClient.sql(upsertStatement)
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
        jdbcClient.sql("DELETE FROM $TABLE_NAME WHERE $PRIMARY_KEY_COLUMN_NAME = :indexValue")
            .param("indexValue", indexUnicus)
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
            articulusInteriorSingularisOptionalis = resultSet.getJsonbOrNull<ArticulusInteriorBO>("ARTICULUS_INTERIOR_SINGULARIS_OPTIONALIS"),
            articulusInteriorOptionalisIteratus = resultSet.getJsonbOrNull<List<ArticulusInteriorBO>>("ARTICULUS_INTERIOR_OPTIONALIS_ITERATUS"),
            appellatioOptionalisIteratus = resultSet.getJsonbOrNull<List<AppellatioComis>>("APPELLATIO_OPTIONALIS_ITERATUS"),
            campusDiei = resultSet.getObject("CAMPUS_DIEI", LocalDate::class.java),
            campusBivalens = resultSet.getBoolean("CAMPUS_BIVALENS"),
            campusNumerorum = resultSet.getInt("CAMPUS_NUMERORUM"),
            iteratioSimpliciumTextuum = resultSet.getJsonb("ITERATIO_SIMPLICIUM_TEXTUUM"),
            relatioAdEntitatemOptionalisIteratus = resultSet.getJsonbOrNull<List<UUID>>("RELATIO_AD_ENTITATEM_OPTIONALIS_ITERATUS"),
            relatioAdEntitatemOptionalis = resultSet.getObject("RELATIO_AD_ENTITATEM_OPTIONALIS", UUID::class.java),
        )
    }

    private val selectColumns: String
        get() {
            // because we need kotlin comments between the lines, we can not use kotlins multiline-comments
            val sb = StringBuilder()
            sb.append("SELECT")
            sb.append("    $PRIMARY_KEY_COLUMN_NAME,")
            sb.append("    CAMPUS_TEXTUS_OBLIGATORIUS,")
            sb.append("    CAMPUS_TEXTUS_OPTIONALIS,")
            sb.append("    APPELLATIO,")
            sb.append("    ARTICULUS_INTERIOR_SINGULARIS,")
            sb.append("    ARTICULUS_INTERIOR_ITERATUS,")
            sb.append("    ARTICULUS_INTERIOR_SINGULARIS_OPTIONALIS,")
            sb.append("    ARTICULUS_INTERIOR_OPTIONALIS_ITERATUS,")
            sb.append("    APPELLATIO_OPTIONALIS_ITERATUS,")
            sb.append("    CAMPUS_DIEI,")
            sb.append("    CAMPUS_BIVALENS,")
            sb.append("    CAMPUS_NUMERORUM,")
            sb.append("    ITERATIO_SIMPLICIUM_TEXTUUM,")
            sb.append("    RELATIO_AD_ENTITATEM_OPTIONALIS_ITERATUS,")
            sb.append("    RELATIO_AD_ENTITATEM_OPTIONALIS")
            sb.append("FROM $TABLE_NAME")
            return sb.toString()
    }
    private val upsertStatement: String
        get() {
            // because we need kotlin comments between the lines, we can not use kotlins multiline-comments
            val sb = StringBuilder()
            sb.append("INSERT INTO $TABLE_NAME (")
            sb.append("    INDEX_UNICUS,")
            sb.append("    CAMPUS_TEXTUS_OBLIGATORIUS,")
            sb.append("    CAMPUS_TEXTUS_OPTIONALIS,")
            sb.append("    APPELLATIO,")
            sb.append("    ARTICULUS_INTERIOR_SINGULARIS,")
            sb.append("    ARTICULUS_INTERIOR_ITERATUS,")
            sb.append("    ARTICULUS_INTERIOR_SINGULARIS_OPTIONALIS,")
            sb.append("    ARTICULUS_INTERIOR_OPTIONALIS_ITERATUS,")
            sb.append("    APPELLATIO_OPTIONALIS_ITERATUS,")
            sb.append("    CAMPUS_DIEI,")
            sb.append("    CAMPUS_BIVALENS,")
            sb.append("    CAMPUS_NUMERORUM,")
            sb.append("    ITERATIO_SIMPLICIUM_TEXTUUM,")
            sb.append("    RELATIO_AD_ENTITATEM_OPTIONALIS_ITERATUS,")
            sb.append("    RELATIO_AD_ENTITATEM_OPTIONALIS")
            sb.append(") VALUES (")
            sb.append("    :indexUnicus,")
            sb.append("    :campusTextusObligatorius,")
            sb.append("    :campusTextusOptionalis,")
            sb.append("    :appellatio,")
            sb.append("    CAST(:articulusInteriorSingularis AS jsonb),")
            sb.append("    CAST(:articulusInteriorIteratus AS jsonb),")
            sb.append("    CAST(:articulusInteriorSingularisOptionalis AS jsonb),")
            sb.append("    CAST(:articulusInteriorOptionalisIteratus AS jsonb),")
            sb.append("    CAST(:appellatioOptionalisIteratus AS jsonb),")
            sb.append("    :campusDiei,")
            sb.append("    :campusBivalens,")
            sb.append("    :campusNumerorum,")
            sb.append("    CAST(:iteratioSimpliciumTextuum AS jsonb),")
            sb.append("    CAST(:relatioAdEntitatemOptionalisIteratus AS jsonb),")
            sb.append("    :relatioAdEntitatemOptionalis")
            sb.append(")")
            sb.append("ON CONFLICT ($PRIMARY_KEY_COLUMN_NAME) DO UPDATE SET")
            sb.append("     CAMPUS_TEXTUS_OBLIGATORIUS = EXCLUDED.CAMPUS_TEXTUS_OBLIGATORIUS,")
            sb.append("     CAMPUS_TEXTUS_OPTIONALIS = EXCLUDED.CAMPUS_TEXTUS_OPTIONALIS,")
            sb.append("     APPELLATIO = EXCLUDED.APPELLATIO,")
            sb.append("     ARTICULUS_INTERIOR_SINGULARIS = EXCLUDED.ARTICULUS_INTERIOR_SINGULARIS,")
            sb.append("     ARTICULUS_INTERIOR_ITERATUS = EXCLUDED.ARTICULUS_INTERIOR_ITERATUS,")
            sb.append("     ARTICULUS_INTERIOR_SINGULARIS_OPTIONALIS = EXCLUDED.ARTICULUS_INTERIOR_SINGULARIS_OPTIONALIS,")
            sb.append("     ARTICULUS_INTERIOR_OPTIONALIS_ITERATUS = EXCLUDED.ARTICULUS_INTERIOR_OPTIONALIS_ITERATUS,")
            sb.append("     APPELLATIO_OPTIONALIS_ITERATUS = EXCLUDED.APPELLATIO_OPTIONALIS_ITERATUS,")
            sb.append("     CAMPUS_DIEI = EXCLUDED.CAMPUS_DIEI,")
            sb.append("     CAMPUS_BIVALENS = EXCLUDED.CAMPUS_BIVALENS,")
            sb.append("     CAMPUS_NUMERORUM = EXCLUDED.CAMPUS_NUMERORUM,")
            sb.append("     ITERATIO_SIMPLICIUM_TEXTUUM = EXCLUDED.ITERATIO_SIMPLICIUM_TEXTUUM,")
            sb.append("     RELATIO_AD_ENTITATEM_OPTIONALIS_ITERATUS = EXCLUDED.RELATIO_AD_ENTITATEM_OPTIONALIS_ITERATUS,")
            sb.append("     RELATIO_AD_ENTITATEM_OPTIONALIS = EXCLUDED.RELATIO_AD_ENTITATEM_OPTIONALIS")
            return sb.toString()
    }
}
