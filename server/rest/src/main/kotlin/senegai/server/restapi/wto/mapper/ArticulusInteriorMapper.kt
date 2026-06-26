package senegai.server.restapi.wto.mapper

import senegai.server.restapi.wto.ArticulusInteriorWTO
import senegai.server.service.bo.ArticulusInteriorBO

/**
 * Maps between the WTOs (transport layer) and BOs (business layer).
 */
object ArticulusInteriorMapper {

    fun ArticulusInteriorWTO.toBo() = ArticulusInteriorBO(
        scriptumTriviale = scriptumTriviale,
        numerusStupidus = numerusStupidus,
    )

    fun ArticulusInteriorBO.toWto() = ArticulusInteriorWTO(
        scriptumTriviale = scriptumTriviale,
        numerusStupidus = numerusStupidus,
    )
}
