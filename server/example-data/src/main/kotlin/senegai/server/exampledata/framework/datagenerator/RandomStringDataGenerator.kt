package senegai.server.exampledata.framework.datagenerator

import org.springframework.stereotype.Component

@Component
class RandomStringDataGenerator(): DataGenerator<String> {
    override fun generateData(): String {
        return "foo"
    }
}
