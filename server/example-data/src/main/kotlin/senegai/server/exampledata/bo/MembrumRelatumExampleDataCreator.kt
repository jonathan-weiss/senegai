package senegai.server.exampledata.bo

import org.springframework.stereotype.Component
import senegai.server.exampledata.DataContext
import senegai.server.exampledata.framework.datagenerator.LastnameStringDataGenerator
import senegai.server.exampledata.framework.datagenerator.RandomUuidDataGenerator
import senegai.server.service.bo.MembrumRelatumBO

/**
 * Creates example data for the business object [MembrumRelatumBO].
 *
 * Delegates the creation of nested objects to the dedicated example data creators of the
 * respective business objects.
 */
@Component
class MembrumRelatumExampleDataCreator(
    private val randomUuidDataGenerator: RandomUuidDataGenerator,
    private val lastnameStringDataGenerator: LastnameStringDataGenerator,
) {

    fun create(dataContext: DataContext): MembrumRelatumBO = MembrumRelatumBO(
        clavisPrimaria = randomUuidDataGenerator.generateData(dataContext),
        descriptioExDistanti = lastnameStringDataGenerator.generateData(dataContext),
    )

    fun createList(dataContext: DataContext, size: Int): List<MembrumRelatumBO> =
        List( size = size) { create(dataContext) }
}
