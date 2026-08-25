package senegai.server.exampledata.framework.datafaker

import net.datafaker.Faker
import senegai.server.exampledata.DataContext

object FakerHelper {

    fun randomString(dataContext: DataContext): String {
        return dataContext.toFaker().lorem().word()
    }

    fun randomBoolean(dataContext: DataContext): Boolean {
        return dataContext.toFaker().bool().bool()
    }

    fun randomInt(dataContext: DataContext): Int {
        return dataContext.toFaker().number().randomNumber().toInt()
    }

    fun firstname(dataContext: DataContext): String {
        return dataContext.toFaker().name().firstName()
    }

    fun lastname(dataContext: DataContext): String {
        return dataContext.toFaker().name().lastName()
    }

    fun street(dataContext: DataContext): String {
        return dataContext.toFaker().address().streetAddress()
    }

    fun postcode(dataContext: DataContext): String {
        return dataContext.toFaker().address().postcode()
    }

    fun city(dataContext: DataContext): String {
        return dataContext.toFaker().address().city()
    }

    fun countryIsoCode(dataContext: DataContext): String {
        return dataContext.toFaker().country().currencyCode()
    }

    fun countryName(dataContext: DataContext): String {
        return dataContext.toFaker().country().name()
    }

    fun age(dataContext: DataContext): Int {
        return dataContext.toFaker().number().numberBetween(1, 99)
    }


    fun <T> oneRandomOf(dataContext: DataContext, array: Array<T>): T {
        val index = dataContext.toFaker().number().numberBetween(0, array.size - 1)
        return array[index]
    }

    fun innerListRandomSize(dataContext: DataContext): Int {
        return dataContext.toFaker().number().numberBetween(0, 3)
    }

    fun entityListRandomSize(dataContext: DataContext): Int {
        return dataContext.toFaker().number().numberBetween(0, 25)
    }


    private fun DataContext.toFaker(): Faker {
        require(this is DataFakerDataContext) {
            "DataContext was not an instance of ${DataFakerDataContext::class.qualifiedName}: $this"
        }
        return this.faker
    }
}
