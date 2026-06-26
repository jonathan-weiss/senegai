package senegai.server

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import senegai.server.exampledata.ExampleDataCreator

/**
 * Triggers the creation of example data on application startup and persists it.
 */
@Component
class ExampleDataInitializer(
    private val exampleDataCreator: ExampleDataCreator,
) : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String) {
        logger.info("Created and persisted example data...")
        exampleDataCreator.createExampleData()
        logger.info("Created and persisted example data. Done")
    }
}
