package senegai.server.exampledata.framework.datagenerator

import org.springframework.stereotype.Component

@Component
class RandomBooleanDataGenerator(): DataGenerator<Boolean> {
    override fun generateData(): Boolean {
        return true
    }
}
