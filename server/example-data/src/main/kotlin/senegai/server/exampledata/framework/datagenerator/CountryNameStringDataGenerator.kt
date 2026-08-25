package senegai.server.exampledata.framework.datagenerator

import org.springframework.stereotype.Component
import senegai.server.exampledata.DataContext
import senegai.server.exampledata.framework.datafaker.FakerHelper

@Component
class CountryNameStringDataGenerator: DataGenerator<String> {
    override fun generateData(dataContext: DataContext): String {
        return FakerHelper.countryName(dataContext)
    }
}
