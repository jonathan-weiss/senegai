package senegai.server.exampledata.datagenerator

interface DataGenerator<T> {

    fun generateData(): T

    fun generateDataList(): List<T> = listOf(generateData(), generateData(), generateData())
}
