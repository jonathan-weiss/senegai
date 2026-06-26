package senegai.server.restapi.opusmagnum

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import senegai.server.restapi.opusmagnum.wto.SilvaOptionumWTO
import senegai.server.service.opusmagnum.OpusMagnumService

/**
 * REST endpoints of the OpusMagnum business context. Served under `/api/opus-magnum`
 * (the `/api` prefix is added by `WebConfig`) and consumed by the Angular
 * `OpusMagnumService`.
 *
 * The controller speaks WTOs to the outside world, maps them to BOs via
 * [OpusMagnumMapper] and always calls the [OpusMagnumService] with the whole
 * `SilvaOptionum` aggregate.
 */
@RestController
@RequestMapping("/opus-magnum")
class OpusMagnumController(
    private val opusMagnumService: OpusMagnumService,
) {

    @GetMapping
    fun getSilvaOptionumList(): List<SilvaOptionumWTO> =
        opusMagnumService.getSilvaOptionumList().map { OpusMagnumMapper.toWto(it) }

    @GetMapping("/{indexUnicus}")
    fun getSilvaOptionumById(@PathVariable indexUnicus: String): SilvaOptionumWTO? =
        opusMagnumService.getSilvaOptionumById(indexUnicus)?.let { OpusMagnumMapper.toWto(it) }

    @PostMapping
    fun createSilvaOptionum(@RequestBody silvaOptionum: SilvaOptionumWTO): SilvaOptionumWTO {
        val created = opusMagnumService.createSilvaOptionum(OpusMagnumMapper.toBo(silvaOptionum))
        return OpusMagnumMapper.toWto(created)
    }

    @PutMapping("/{indexUnicus}")
    fun updateSilvaOptionum(
        @PathVariable indexUnicus: String,
        @RequestBody silvaOptionum: SilvaOptionumWTO,
    ): SilvaOptionumWTO {
        val toUpdate = OpusMagnumMapper.toBo(silvaOptionum).copy(indexUnicus = indexUnicus)
        return OpusMagnumMapper.toWto(opusMagnumService.updateSilvaOptionum(toUpdate))
    }

    @DeleteMapping("/{indexUnicus}")
    fun deleteSilvaOptionum(@PathVariable indexUnicus: String) =
        opusMagnumService.deleteSilvaOptionum(indexUnicus)
}
