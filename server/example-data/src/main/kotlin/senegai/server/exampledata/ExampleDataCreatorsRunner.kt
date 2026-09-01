package senegai.server.exampledata

import org.springframework.stereotype.Component
import senegai.server.exampledata.employee.EmployeeExampleDataCreator
import senegai.server.exampledata.employeeaddress.EmployeeAddressExampleDataCreator
import senegai.server.exampledata.entitasrelata.EntitasRelataExampleDataCreator
import senegai.server.exampledata.framework.datafaker.DataFakerDataContextFactoryService
import senegai.server.exampledata.opusmagnum.OpusMagnumExampleDataCreator
import senegai.server.service.opusmagnum.OpusMagnumRepository
import senegai.server.service.bo.SilvaOptionumBO

/**
 * Orchestrates the creation of example data.
 */
@Component
class ExampleDataCreatorsRunner(
    private val opusMagnumExampleDataCreator: OpusMagnumExampleDataCreator,
    private val entitasRelataExampleDataCreator: EntitasRelataExampleDataCreator,
    private val employeeExampleDataCreator: EmployeeExampleDataCreator,
    private val employeeAddressExampleDataCreator: EmployeeAddressExampleDataCreator,
    private val dataContextFactoryService: DataFakerDataContextFactoryService,
) {

    /**
     * Creates the example [SilvaOptionumBO] aggregates, writes each of them to the
     * persistence via the [OpusMagnumRepository] and returns the persisted list.
     */
    fun createExampleData() {
        val dataContext = dataContextFactoryService.createContext()
        // the order matters: an entity that references another one can only pick an existing
        // instance, so the referenced entity has to create its example data first
        entitasRelataExampleDataCreator.createExampleData(dataContext)
        opusMagnumExampleDataCreator.createExampleData(dataContext)
        employeeAddressExampleDataCreator.createExampleData(dataContext)
        employeeExampleDataCreator.createExampleData(dataContext)
    }
}
