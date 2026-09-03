package senegai.server.exampledata.membrumrelatum

import org.springframework.stereotype.Component
import senegai.server.exampledata.DataContext
import senegai.server.exampledata.ExampleDataCreator
import senegai.server.exampledata.bo.MembrumRelatumExampleDataCreator
import senegai.server.exampledata.framework.datafaker.FakerHelper
import senegai.server.service.bo.MembrumRelatumBO
import senegai.server.service.membrumrelatum.MembrumRelatumRepository

/**
 * Orchestrates the creation of MembrumRelatum example data.
 *
 * Builds a list of [MembrumRelatumBO] aggregates by delegating to the per-business-object
 * example data creators and persists the result through the [MembrumRelatumRepository] port.
 */
@Component
class MembrumRelatumExampleDataPopulator(
    private val membrumRelatumExampleDataCreator: MembrumRelatumExampleDataCreator,
    private val membrumRelatumRepository: MembrumRelatumRepository,
): ExampleDataCreator {

    /**
     * Creates the example [MembrumRelatumBO] aggregates, writes each of them to the
     * persistence via the [MembrumRelatumRepository] and returns the persisted list.
     */
    override fun createExampleData(dataContext: DataContext) {
        membrumRelatumExampleDataCreator.createList(dataContext, FakerHelper.itemListRandomSize(dataContext))
            .forEach { membrumRelatumRepository.save(it) }
    }
}
