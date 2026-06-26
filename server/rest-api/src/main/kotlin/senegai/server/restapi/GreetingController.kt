package senegai.server.restapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import senegai.server.service.GreetingService

/**
 * Example REST endpoint that delegates to the [GreetingService] in the service module.
 */
@RestController
class GreetingController(
    private val greetingService: GreetingService,
) {

    @GetMapping("/greeting")
    fun greeting(@RequestParam(defaultValue = "World") name: String): String =
        greetingService.greet(name)

    @GetMapping("/greeting/history")
    fun history(): List<String> = greetingService.history()
}
