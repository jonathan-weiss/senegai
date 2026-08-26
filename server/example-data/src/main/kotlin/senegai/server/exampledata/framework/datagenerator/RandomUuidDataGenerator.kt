package senegai.server.exampledata.framework.datagenerator

import org.springframework.stereotype.Component
import senegai.server.exampledata.DataContext
import senegai.server.exampledata.framework.datafaker.FakerHelper
import java.util.UUID

@Component
class RandomUuidDataGenerator: DataGenerator<UUID> {
    override fun generateData(dataContext: DataContext): UUID {
        return FakerHelper.randomUuid(dataContext)
    }
}
