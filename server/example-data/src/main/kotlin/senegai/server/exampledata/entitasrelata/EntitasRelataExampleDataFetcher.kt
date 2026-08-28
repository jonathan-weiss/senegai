package senegai.server.exampledata.entitasrelata

import org.springframework.stereotype.Component
import senegai.server.exampledata.DataContext
import senegai.server.exampledata.framework.datafaker.FakerHelper
import senegai.server.service.bo.MembrumRelatumBO
import senegai.server.service.entitasrelata.EntitasRelataRepository
import java.util.UUID

/**
 * Fetches already persisted EntitasRelata example data so that other example data creators can
 * reference it.
 *
 * Unlike the example data creators, this does not create anything: it reads the
 * [MembrumRelatumBO] instances that the [EntitasRelataExampleDataCreator] has written before,
 * so that a reference is always a valid one. It therefore only returns something once
 * EntitasRelata example data has been created.
 */
@Component
class EntitasRelataExampleDataFetcher(
    private val entitasRelataRepository: EntitasRelataRepository,
) {

    /**
     * A random subset of the clavisPrimaria of the existing [MembrumRelatumBO] instances, or an
     * empty list if none exist yet.
     */
    fun fetchRandomKeysList(dataContext: DataContext): List<UUID> =
        FakerHelper.manyOfRandom(
            dataContext = dataContext,
            array = entitasRelataRepository.findAll().toTypedArray(),
            size = FakerHelper.referenceListRandomSize(dataContext),
        ).map { it.clavisPrimaria }
}
