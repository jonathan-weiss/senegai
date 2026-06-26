package senegai.server.service.opusmagnum

import org.springframework.stereotype.Service
import senegai.server.service.opusmagnum.bo.SilvaOptionumBO
import java.util.UUID

/**
 * Business service of the OpusMagnum business context. It is called by the REST layer
 * with [SilvaOptionumBO] business objects (never WTOs) and delegates persistence to the
 * [OpusMagnumRepository] port.
 *
 * Every operation works on the whole [SilvaOptionumBO] aggregate as a single unit.
 */
@Service
class OpusMagnumService(
    private val opusMagnumRepository: OpusMagnumRepository,
) {

    fun getSilvaOptionumList(): List<SilvaOptionumBO> = opusMagnumRepository.findAll()

    fun getSilvaOptionumById(indexUnicus: String): SilvaOptionumBO? =
        opusMagnumRepository.findById(indexUnicus)

    fun createSilvaOptionum(silvaOptionum: SilvaOptionumBO): SilvaOptionumBO {
        val toCreate = silvaOptionum.copy(indexUnicus = UUID.randomUUID().toString())
        return opusMagnumRepository.save(toCreate)
    }

    fun updateSilvaOptionum(silvaOptionum: SilvaOptionumBO): SilvaOptionumBO =
        opusMagnumRepository.save(silvaOptionum)

    fun deleteSilvaOptionum(indexUnicus: String) = opusMagnumRepository.deleteById(indexUnicus)
}
