package senegai.server.restapi.wto

import java.util.UUID

data class MembrumRelatumByIdsCriteriaWTO(
    val clavisPrimariaList: List<UUID> = emptyList(),
)
