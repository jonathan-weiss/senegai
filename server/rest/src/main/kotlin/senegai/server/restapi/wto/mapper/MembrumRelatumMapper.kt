package senegai.server.restapi.wto.mapper

import senegai.server.restapi.wto.MembrumRelatumWTO
import senegai.server.service.bo.MembrumRelatumBO

/**
 * Maps between the WTOs (transport layer) and BOs (business layer).
 */
object MembrumRelatumMapper {

    fun MembrumRelatumWTO.toBo(): MembrumRelatumBO = MembrumRelatumBO(
        clavisPrimaria = clavisPrimaria,
        descriptioExDistanti = descriptioExDistanti,
    )

    fun MembrumRelatumBO.toWto(): MembrumRelatumWTO = MembrumRelatumWTO(
        clavisPrimaria = clavisPrimaria,
        descriptioExDistanti = descriptioExDistanti,
    )
}
