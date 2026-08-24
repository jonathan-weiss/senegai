package senegai.server.exampledata.datagenerator

import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class RandomEnumValueDataGenerator {
    fun <T> generateData(enumClass: KClass<T>): T where T : Enum<T> {
        val enumConstants = enumClass.java.enumConstants
        return enumConstants.random()
    }

    fun <T> generateDataList(enumClass: KClass<T>): List<T> where T : Enum<T>
            = listOf(generateData(enumClass), generateData(enumClass), generateData(enumClass))
}
