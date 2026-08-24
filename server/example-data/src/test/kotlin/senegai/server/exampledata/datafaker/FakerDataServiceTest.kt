package senegai.server.exampledata

import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import senegai.server.exampledata.datafaker.FakerDataService
import senegai.server.exampledata.datafaker.TextFakeDataCategory

class FakerDataServiceTest {

    private val service = FakerDataService()

    @Test
    fun fakeDataString() {
        assertNotEquals("", service.fakeDataString(TextFakeDataCategory.FIRSTNAME))
    }
}
