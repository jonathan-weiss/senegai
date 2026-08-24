package senegai.server.exampledata.datafaker

import net.datafaker.Faker
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*


@Service
class FakerDataService {
    private val faker: Faker = Faker(Locale.ENGLISH)


    fun fakeDataString(fakeDataCategory: TextFakeDataCategory): String {
        return when (fakeDataCategory) {
            TextFakeDataCategory.FIRSTNAME -> faker.name().firstName()
            TextFakeDataCategory.SPORT -> faker.olympicSport().summerOlympics()
            TextFakeDataCategory.TEAM -> faker.team().name()
            TextFakeDataCategory.COUNTRY -> faker.country().name()
        }
    }

    fun fakeDataInt(fakeDataCategory: NumberFakeDataCategory): Int {
        return when (fakeDataCategory) {
            NumberFakeDataCategory.POSITIVE_NUMBER -> faker.number().positive()
            NumberFakeDataCategory.NEGATIVE_NUMBER -> faker.number().negative()
            NumberFakeDataCategory.NUMBER -> faker.number().randomNumber().toInt()
        }
    }

    fun fakeDataBoolean(fakeDataCategory: BooleanFakeDataCategory): Boolean {
        return when (fakeDataCategory) {
            BooleanFakeDataCategory.BOOLEAN -> faker.bool().bool()
        }
    }

    fun fakeDataLocalDate(fakeDataCategory: DateFakeDataCategory): LocalDate {
        return when (fakeDataCategory) {
            DateFakeDataCategory.BIRTHDAY -> faker.timeAndDate().birthday()
            else -> throw IllegalArgumentException("Invalid data type 'LocalDate' for data category $fakeDataCategory")
        }
    }

}
