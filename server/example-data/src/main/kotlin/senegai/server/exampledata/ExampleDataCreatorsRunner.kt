package senegai.server.exampledata

import org.springframework.stereotype.Component
import senegai.server.exampledata.employee.EmployeeExampleDataCreator
import senegai.server.exampledata.employeeaddress.EmployeeAddressExampleDataCreator
import senegai.server.exampledata.opusmagnum.OpusMagnumExampleDataCreator
import senegai.server.service.opusmagnum.OpusMagnumRepository
import senegai.server.service.bo.SilvaOptionumBO

/**
 * Orchestrates the creation of example data.
 */
@Component
class ExampleDataCreatorsRunner(
    private val opusMagnumExampleDataCreator: OpusMagnumExampleDataCreator,
    private val employeeExampleDataCreator: EmployeeExampleDataCreator,
    private val employeeAddressExampleDataCreator: EmployeeAddressExampleDataCreator,
) {

    /**
     * Creates the example [SilvaOptionumBO] aggregates, writes each of them to the
     * persistence via the [OpusMagnumRepository] and returns the persisted list.
     */
    fun createExampleData() {
        opusMagnumExampleDataCreator.createExampleData()
        employeeExampleDataCreator.createExampleData()
        employeeAddressExampleDataCreator.createExampleData()
    }
}
