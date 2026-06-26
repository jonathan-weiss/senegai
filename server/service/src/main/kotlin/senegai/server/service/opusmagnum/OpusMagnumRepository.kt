package senegai.server.service.opusmagnum

import senegai.server.service.bo.SilvaOptionumBO

/**
 * Port for persisting the OpusMagnum root object [SilvaOptionumBO]. The implementation
 * lives in the persistence module, so the service (business) layer stays independent of
 * any persistence technology.
 *
 * Always operates on the whole [SilvaOptionumBO] aggregate, never on nested items.
 */
interface OpusMagnumRepository {

    fun findAll(): List<SilvaOptionumBO>

    fun findById(indexUnicus: String): SilvaOptionumBO?

    /** Inserts or replaces the given [silvaOptionum] (matched by [SilvaOptionumBO.indexUnicus]). */
    fun save(silvaOptionum: SilvaOptionumBO): SilvaOptionumBO

    fun deleteById(indexUnicus: String)
}
