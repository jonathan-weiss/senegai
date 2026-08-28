package senegai.server.restapi.wto.mapper

import senegai.server.restapi.wto.MembrumRelatumByIdsCriteriaWTO
import senegai.server.service.bo.MembrumRelatumByIdsCriteriaBO

object MembrumRelatumByIdsCriteriaMapper {

    fun MembrumRelatumByIdsCriteriaWTO.toBo(): MembrumRelatumByIdsCriteriaBO = MembrumRelatumByIdsCriteriaBO(
        clavisPrimariaList = clavisPrimariaList,
    )
}
