package senegai.server.service.entitasrelata

import org.springframework.stereotype.Service
import senegai.server.service.bo.MembrumRelatumBO
import senegai.server.service.bo.MembrumRelatumByIdsCriteriaBO
import senegai.server.service.bo.MembrumRelatumSearchCriteriaBO
import java.util.UUID

/**
 * Business service of the EntitasRelata business context. It is called by the REST layer
 * with [MembrumRelatumBO] business objects (never WTOs) and delegates persistence to the
 * [EntitasRelataRepository] port.
 *
 * Every operation works on the whole [MembrumRelatumBO] aggregate as a single unit.
 */
@Service
class EntitasRelataService(
    private val entitasRelataRepository: EntitasRelataRepository,
) {

    fun getMembrumRelatumList(): List<MembrumRelatumBO> = entitasRelataRepository.findAll()

    fun getMembrumRelatumById(clavisPrimaria: UUID): MembrumRelatumBO? =
        entitasRelataRepository.findById(clavisPrimaria)

    fun getMembrumRelatumListByIds(criteria: MembrumRelatumByIdsCriteriaBO): List<MembrumRelatumBO> =
        entitasRelataRepository.findByIds(criteria)

    fun searchMembrumRelatumList(searchCriteria: MembrumRelatumSearchCriteriaBO): List<MembrumRelatumBO> =
        entitasRelataRepository.search(searchCriteria)

    fun createMembrumRelatum(membrumRelatum: MembrumRelatumBO): MembrumRelatumBO {
        val toCreate = membrumRelatum.copy(clavisPrimaria = UUID.randomUUID())
        return entitasRelataRepository.save(toCreate)
    }

    fun updateMembrumRelatum(membrumRelatum: MembrumRelatumBO): MembrumRelatumBO =
        entitasRelataRepository.save(membrumRelatum)

    fun deleteMembrumRelatum(clavisPrimaria: UUID) = entitasRelataRepository.deleteById(clavisPrimaria)
}
