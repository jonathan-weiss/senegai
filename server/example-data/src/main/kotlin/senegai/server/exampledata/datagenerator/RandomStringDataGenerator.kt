package senegai.server.exampledata.datagenerator

import org.springframework.stereotype.Component

@Component
class RandomStringDataGenerator(): DataGenerator<String> {
    override fun generateData(): String {
        return "foo"
    }
}
