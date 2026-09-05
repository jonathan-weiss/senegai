package senegai.server.exampledata.framework.datagenerator

import org.springframework.stereotype.Component
import senegai.server.exampledata.DataContext
import senegai.server.exampledata.framework.datafaker.FakerHelper

@Component
class RandomDoubleDataGenerator(): DataGenerator<Double> {
    override fun generateData(dataContext: DataContext): Double {
        return FakerHelper.randomDouble(dataContext)
    }
}
