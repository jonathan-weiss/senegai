package senegai.server.exampledata.framework.datagenerator

import org.springframework.stereotype.Component

@Component
class RandomNumberDataGenerator(): DataGenerator<Int> {
    override fun generateData(): Int {
        return 42
    }
}
