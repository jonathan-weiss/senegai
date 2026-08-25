package senegai.server.exampledata.framework.datagenerator

import org.springframework.stereotype.Component
import senegai.server.exampledata.DataContext
import senegai.server.exampledata.framework.datafaker.FakerHelper

@Component
class AgeNumberDataGenerator(): DataGenerator<Int> {
    override fun generateData(dataContext: DataContext): Int {
        return FakerHelper.age(dataContext)
    }
}
