package senegai.server.exampledata

import org.springframework.stereotype.Component
import senegai.server.exampledata.address.AddressExampleDataPopulator
import senegai.server.exampledata.contact.ContactExampleDataPopulator
import senegai.server.exampledata.framework.datafaker.DataFakerDataContextFactoryService
import senegai.server.exampledata.membrumrelatum.MembrumRelatumExampleDataPopulator
import senegai.server.exampledata.silvaoptionum.SilvaOptionumExampleDataPopulator

/**
 * Orchestrates the creation of example data: one populator per item with a primary key,
 * each of them filling the repository of that item.
 */
@Component
class ExampleDataCreatorsRunner(
    private val membrumRelatumExampleDataPopulator: MembrumRelatumExampleDataPopulator,
    private val silvaOptionumExampleDataPopulator: SilvaOptionumExampleDataPopulator,
    private val addressExampleDataPopulator: AddressExampleDataPopulator,
    private val contactExampleDataPopulator: ContactExampleDataPopulator,
    private val dataContextFactoryService: DataFakerDataContextFactoryService,
) {

    fun createExampleData() {
        val dataContext = dataContextFactoryService.createContext()
        // the order matters: an item that references another one can only pick an existing
        // instance, so the referenced item has to create its example data first
        membrumRelatumExampleDataPopulator.createExampleData(dataContext)
        silvaOptionumExampleDataPopulator.createExampleData(dataContext)
        addressExampleDataPopulator.createExampleData(dataContext)
        contactExampleDataPopulator.createExampleData(dataContext)
    }
}
