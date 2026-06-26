package senegai.server.persistence.opusmagnum

import org.springframework.stereotype.Repository
import senegai.server.service.opusmagnum.OpusMagnumRepository
import senegai.server.service.opusmagnum.bo.SilvaOptionumBO
import java.util.concurrent.ConcurrentHashMap

/**
 * Simple in-memory implementation of the [OpusMagnumRepository] port defined in the
 * service module. Holds the [SilvaOptionumBO] aggregates in memory only; a real
 * persistence framework can replace this later without touching the service layer.
 */
@Repository
class InMemoryOpusMagnumRepository : OpusMagnumRepository {

    private val store = ConcurrentHashMap<String, SilvaOptionumBO>()

    override fun findAll(): List<SilvaOptionumBO> = store.values.toList()

    override fun findById(indexUnicus: String): SilvaOptionumBO? = store[indexUnicus]

    override fun save(silvaOptionum: SilvaOptionumBO): SilvaOptionumBO {
        store[silvaOptionum.indexUnicus] = silvaOptionum
        return silvaOptionum
    }

    override fun deleteById(indexUnicus: String) {
        store.remove(indexUnicus)
    }
}
