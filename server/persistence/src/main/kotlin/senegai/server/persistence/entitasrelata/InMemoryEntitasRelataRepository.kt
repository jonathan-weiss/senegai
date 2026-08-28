package senegai.server.persistence.entitasrelata

import org.springframework.stereotype.Repository
import senegai.server.service.entitasrelata.EntitasRelataRepository
import senegai.server.service.bo.MembrumRelatumBO
import senegai.server.service.bo.MembrumRelatumByIdsCriteriaBO
import senegai.server.service.bo.MembrumRelatumSearchCriteriaBO
import java.util.concurrent.ConcurrentHashMap
import java.util.UUID

/**
 * Simple in-memory implementation of the [EntitasRelataRepository] port defined in the
 * service module. Holds the [MembrumRelatumBO] aggregates in memory only; a real
 * persistence framework can replace this later without touching the service layer.
 */
@Repository
class InMemoryEntitasRelataRepository : EntitasRelataRepository {

    private val store = ConcurrentHashMap<UUID, MembrumRelatumBO>()

    override fun findAll(): List<MembrumRelatumBO> = store.values.toList()

    override fun findById(clavisPrimaria: UUID): MembrumRelatumBO? = store[clavisPrimaria]

    override fun findByIds(criteria: MembrumRelatumByIdsCriteriaBO): List<MembrumRelatumBO> =
        criteria.clavisPrimariaList.mapNotNull { store[it] }

    override fun search(searchCriteria: MembrumRelatumSearchCriteriaBO): List<MembrumRelatumBO> =
        store.values.filter { it.toString().contains(searchCriteria.query, ignoreCase = true) }

    override fun save(membrumRelatum: MembrumRelatumBO): MembrumRelatumBO {
        store[membrumRelatum.clavisPrimaria] = membrumRelatum
        return membrumRelatum
    }

    override fun deleteById(clavisPrimaria: UUID) {
        store.remove(clavisPrimaria)
    }
}
