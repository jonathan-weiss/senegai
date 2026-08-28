package senegai.server.restapi.entitasrelata

import org.springframework.web.bind.annotation.*
import senegai.server.restapi.wto.MembrumRelatumByIdsCriteriaWTO
import senegai.server.restapi.wto.MembrumRelatumByIdsResultWTO
import senegai.server.restapi.wto.MembrumRelatumSearchCriteriaWTO
import senegai.server.restapi.wto.MembrumRelatumSearchResultWTO
import senegai.server.restapi.wto.MembrumRelatumWTO
import senegai.server.restapi.wto.mapper.MembrumRelatumMapper
import senegai.server.restapi.wto.mapper.MembrumRelatumByIdsCriteriaMapper.toBo
import senegai.server.restapi.wto.mapper.MembrumRelatumSearchCriteriaMapper.toBo
import senegai.server.restapi.wto.mapper.MembrumRelatumMapper.toBo
import senegai.server.restapi.wto.mapper.MembrumRelatumMapper.toWto
import senegai.server.service.entitasrelata.EntitasRelataService
import java.util.UUID

/**
 * REST endpoints of the EntitasRelata business context. Served under `/api/entitas-relata`
 * (the `/api` prefix is added by `WebConfig`) and consumed by the Angular
 * `EntitasRelataService`.
 *
 * The controller speaks WTOs to the outside world, maps them to BOs via
 * [MembrumRelatumMapper] and always calls the [EntitasRelataService] with the whole
 * `MembrumRelatum` aggregate.
 */
@RestController
@RequestMapping("/entitas-relata")
class EntitasRelataController(
    private val entitasRelataService: EntitasRelataService,
) {

    @GetMapping
    fun getMembrumRelatumList(): List<MembrumRelatumWTO> =
        entitasRelataService.getMembrumRelatumList().map { it.toWto() }

    @PostMapping("/search")
    fun searchMembrumRelatumList(@RequestBody searchCriteria: MembrumRelatumSearchCriteriaWTO): MembrumRelatumSearchResultWTO =
        MembrumRelatumSearchResultWTO(
            membrumRelatumList = entitasRelataService.searchMembrumRelatumList(searchCriteria.toBo()).map { it.toWto() },
        )

    @PostMapping("/by-ids")
    fun getMembrumRelatumListByIds(@RequestBody criteria: MembrumRelatumByIdsCriteriaWTO): MembrumRelatumByIdsResultWTO =
        MembrumRelatumByIdsResultWTO(
            membrumRelatumList = entitasRelataService.getMembrumRelatumListByIds(criteria.toBo()).map { it.toWto() },
        )

    @GetMapping("/{clavisPrimaria}")
    fun getMembrumRelatumById(@PathVariable clavisPrimaria: UUID): MembrumRelatumWTO? =
        entitasRelataService.getMembrumRelatumById(clavisPrimaria)?.toWto()

    @PostMapping
    fun createMembrumRelatum(@RequestBody membrumRelatum: MembrumRelatumWTO): MembrumRelatumWTO {
        val created = entitasRelataService.createMembrumRelatum(membrumRelatum.toBo())
        return created.toWto()
    }

    @PutMapping("/{clavisPrimaria}")
    fun updateMembrumRelatum(
        @PathVariable clavisPrimaria: UUID,
        @RequestBody membrumRelatum: MembrumRelatumWTO,
    ): MembrumRelatumWTO {
        val toUpdate = membrumRelatum.toBo().copy(clavisPrimaria = clavisPrimaria)
        return entitasRelataService.updateMembrumRelatum(toUpdate).toWto()
    }

    @DeleteMapping("/{clavisPrimaria}")
    fun deleteMembrumRelatum(@PathVariable clavisPrimaria: UUID) =
        entitasRelataService.deleteMembrumRelatum(clavisPrimaria)
}
