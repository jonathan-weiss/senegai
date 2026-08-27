package senegai.server.exampledata.entitasrelata

import org.springframework.stereotype.Component
import senegai.server.exampledata.DataContext
import senegai.server.exampledata.ExampleDataCreator
import senegai.server.exampledata.bo.MembrumRelatumExampleDataCreator
import senegai.server.exampledata.framework.datafaker.FakerHelper
import senegai.server.service.bo.MembrumRelatumBO
import senegai.server.service.entitasrelata.EntitasRelataRepository

/**
 * Orchestrates the creation of EntitasRelata example data.
 *
 * Builds a list of [MembrumRelatumBO] aggregates by delegating to the per-business-object
 * example data creators and persists the result through the [EntitasRelataRepository] port.
 */
@Component
class EntitasRelataExampleDataCreator(
    private val membrumRelatumExampleDataCreator: MembrumRelatumExampleDataCreator,
    private val entitasRelataRepository: EntitasRelataRepository,
): ExampleDataCreator {

    /**
     * Creates the example [MembrumRelatumBO] aggregates, writes each of them to the
     * persistence via the [EntitasRelataRepository] and returns the persisted list.
     */
    override fun createExampleData(dataContext: DataContext) {
        membrumRelatumExampleDataCreator.createList(dataContext, FakerHelper.entityListRandomSize(dataContext))
            .forEach { entitasRelataRepository.save(it) }
    }
}
