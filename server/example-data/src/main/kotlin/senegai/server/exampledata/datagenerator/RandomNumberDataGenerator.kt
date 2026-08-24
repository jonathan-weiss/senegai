package senegai.server.exampledata.datagenerator

import org.springframework.stereotype.Component

@Component
class RandomNumberDataGenerator(): DataGenerator<Int> {
    override fun generateData(): Int {
        return 42
    }
}
