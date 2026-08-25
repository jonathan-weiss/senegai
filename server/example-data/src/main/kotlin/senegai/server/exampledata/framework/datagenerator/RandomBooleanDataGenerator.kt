package senegai.server.exampledata.framework.datagenerator

import org.springframework.stereotype.Component
import senegai.server.exampledata.DataContext
import senegai.server.exampledata.framework.datafaker.FakerHelper

@Component
class RandomBooleanDataGenerator(): DataGenerator<Boolean> {
    override fun generateData(dataContext: DataContext): Boolean {
        return FakerHelper.randomBoolean(dataContext)
    }
}
