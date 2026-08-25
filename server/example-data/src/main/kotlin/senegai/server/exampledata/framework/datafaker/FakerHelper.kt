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
