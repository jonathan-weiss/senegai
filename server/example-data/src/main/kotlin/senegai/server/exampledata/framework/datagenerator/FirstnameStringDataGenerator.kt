package senegai.server.exampledata.framework.datagenerator

import org.springframework.stereotype.Component
import senegai.server.exampledata.DataContext
import senegai.server.exampledata.framework.datafaker.FakerHelper

@Component
class FirstnameStringDataGenerator: DataGenerator<String> {
    override fun generateData(dataContext: DataContext): String {
        return FakerHelper.firstname(dataContext)
    }
}
