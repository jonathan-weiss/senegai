package senegai.server.exampledata.framework.datafaker

import net.datafaker.Faker
import senegai.server.exampledata.DataContext

data class DataFakerDataContext(
    val faker: Faker
): DataContext
