package senegai.server.exampledata

import org.springframework.stereotype.Component
import senegai.server.exampledata.opusmagnum.OpusMagnumExampleDataCreator
import senegai.server.exampledata.opusmagnum.SilvaOptionumExampleDataCreator
import senegai.server.service.opusmagnum.OpusMagnumRepository
import senegai.server.service.opusmagnum.bo.SilvaOptionumBO

/**
 * Orchestrates the creation of example data.
 */
@Component
class ExampleDataCreator(
    private val opusMagnumExampleDataCreator: OpusMagnumExampleDataCreator,
) {

    /**
     * Creates the example [SilvaOptionumBO] aggregates, writes each of them to the
     * persistence via the [OpusMagnumRepository] and returns the persisted list.
     */
    fun createExampleData() {
        opusMagnumExampleDataCreator.createExampleData()
    }
}
