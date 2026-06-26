package senegai.server.restapi

import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Prefixes every [RestController] endpoint with a shared `/api` path, so e.g. `/greeting`
 * is served under `/api/greeting`. New controllers are picked up automatically.
 */
@Configuration
class WebConfig : WebMvcConfigurer {

    override fun configurePathMatch(configurer: PathMatchConfigurer) {
        configurer.addPathPrefix("/api") { it.isAnnotationPresent(RestController::class.java) }
    }
}
