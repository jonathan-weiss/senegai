package senegai.server.restapi.wto.mapper

import senegai.server.restapi.wto.MembrumRelatumSearchCriteriaWTO
import senegai.server.service.bo.MembrumRelatumSearchCriteriaBO

object MembrumRelatumSearchCriteriaMapper {

    fun MembrumRelatumSearchCriteriaWTO.toBo(): MembrumRelatumSearchCriteriaBO = MembrumRelatumSearchCriteriaBO(
        query = query,
    )
}
