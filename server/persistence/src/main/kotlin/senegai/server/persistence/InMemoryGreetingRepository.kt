package senegai.server.persistence

import org.springframework.stereotype.Repository
import senegai.server.service.GreetingRepository
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Simple in-memory implementation of the [GreetingRepository] port defined in the
 * service module. Holds greetings in memory only; a real persistence framework can
 * replace this later without touching the service layer.
 */
@Repository
class InMemoryGreetingRepository : GreetingRepository {

    private val greetings = CopyOnWriteArrayList<String>()

    override fun save(greeting: String) {
        greetings.add(greeting)
    }

    override fun findAll(): List<String> = greetings.toList()
}
