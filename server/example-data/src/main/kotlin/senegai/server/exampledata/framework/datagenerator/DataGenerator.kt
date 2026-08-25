package senegai.server.exampledata.framework.datagenerator

import senegai.server.exampledata.DataContext

interface DataGenerator<T> {

    fun generateData(dataContext: DataContext): T

    fun generateDataList(dataContext: DataContext, size: Int): List<T> =
        List( size =size) { generateData(dataContext) }
}
