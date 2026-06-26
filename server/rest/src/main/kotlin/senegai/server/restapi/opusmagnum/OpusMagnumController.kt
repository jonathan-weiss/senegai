package senegai.server.restapi.opusmagnum

import org.springframework.web.bind.annotation.*
import senegai.server.restapi.wto.SilvaOptionumWTO
import senegai.server.restapi.wto.mapper.SilvaOptionumMapper
import senegai.server.restapi.wto.mapper.SilvaOptionumMapper.toBo
import senegai.server.restapi.wto.mapper.SilvaOptionumMapper.toWto
import senegai.server.service.opusmagnum.OpusMagnumService

/**
 * REST endpoints of the OpusMagnum business context. Served under `/api/opus-magnum`
 * (the `/api` prefix is added by `WebConfig`) and consumed by the Angular
 * `OpusMagnumService`.
 *
 * The controller speaks WTOs to the outside world, maps them to BOs via
 * [SilvaOptionumMapper] and always calls the [OpusMagnumService] with the whole
 * `SilvaOptionum` aggregate.
 */
@RestController
@RequestMapping("/opus-magnum")
class OpusMagnumController(
    private val opusMagnumService: OpusMagnumService,
) {

    @GetMapping
    fun getSilvaOptionumList(): List<SilvaOptionumWTO> =
        opusMagnumService.getSilvaOptionumList().map { it.toWto() }

    @GetMapping("/{indexUnicus}")
    fun getSilvaOptionumById(@PathVariable indexUnicus: String): SilvaOptionumWTO? =
        opusMagnumService.getSilvaOptionumById(indexUnicus)?.toWto()

    @PostMapping
    fun createSilvaOptionum(@RequestBody silvaOptionum: SilvaOptionumWTO): SilvaOptionumWTO {
        val created = opusMagnumService.createSilvaOptionum(silvaOptionum.toBo())
        return created.toWto()
    }

    @PutMapping("/{indexUnicus}")
    fun updateSilvaOptionum(
        @PathVariable indexUnicus: String,
        @RequestBody silvaOptionum: SilvaOptionumWTO,
    ): SilvaOptionumWTO {
        val toUpdate = silvaOptionum.toBo().copy(indexUnicus = indexUnicus)
        return opusMagnumService.updateSilvaOptionum(toUpdate).toWto()
    }

    @DeleteMapping("/{indexUnicus}")
    fun deleteSilvaOptionum(@PathVariable indexUnicus: String) =
        opusMagnumService.deleteSilvaOptionum(indexUnicus)
}
