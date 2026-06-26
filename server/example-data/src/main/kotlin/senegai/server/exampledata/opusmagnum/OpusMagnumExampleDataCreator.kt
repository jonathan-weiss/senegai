package senegai.server.exampledata.opusmagnum

import org.springframework.stereotype.Component
import senegai.server.service.opusmagnum.OpusMagnumRepository
import senegai.server.service.opusmagnum.bo.SilvaOptionumBO

/**
 * Orchestrates the creation of OpusMagnum example data.
 *
 * Builds a list of [SilvaOptionumBO] aggregates by delegating to the per-business-object
 * example data creators and persists the result through the [OpusMagnumRepository] port.
 */
@Component
class OpusMagnumExampleDataCreator(
    private val silvaOptionumExampleDataCreator: SilvaOptionumExampleDataCreator,
    private val opusMagnumRepository: OpusMagnumRepository,
) {

    /**
     * Creates the example [SilvaOptionumBO] aggregates, writes each of them to the
     * persistence via the [OpusMagnumRepository] and returns the persisted list.
     */
    fun createExampleData(): List<SilvaOptionumBO> =
        silvaOptionumExampleDataCreator.createList()
            .map { opusMagnumRepository.save(it) }
}
