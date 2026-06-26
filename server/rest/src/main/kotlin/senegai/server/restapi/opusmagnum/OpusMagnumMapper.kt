package senegai.server.restapi.opusmagnum

import senegai.server.restapi.opusmagnum.wto.AppellatioComisEnum
import senegai.server.restapi.opusmagnum.wto.ArticulusInteriorWTO
import senegai.server.restapi.opusmagnum.wto.SilvaOptionumWTO
import senegai.server.service.opusmagnum.bo.AppellatioComis
import senegai.server.service.opusmagnum.bo.ArticulusInteriorBO
import senegai.server.service.opusmagnum.bo.SilvaOptionumBO

/**
 * Maps between the OpusMagnum WTOs (transport layer) and BOs (business layer).
 *
 * The REST layer receives/returns WTOs but always calls the business service with the
 * whole [SilvaOptionumBO] aggregate, so the mapping happens here, on the root object.
 */
object OpusMagnumMapper {

    fun toBo(wto: SilvaOptionumWTO): SilvaOptionumBO = SilvaOptionumBO(
        indexUnicus = wto.indexUnicus,
        campusTextusObligatorius = wto.campusTextusObligatorius,
        campusTextusOptionalis = wto.campusTextusOptionalis,
        appellatio = wto.appellatio.toBo(),
        articulusInteriorSingularis = wto.articulusInteriorSingularis.toBo(),
        articulusInteriorIteratus = wto.articulusInteriorIteratus.map { it.toBo() },
        articulusInteriorSingularisOptionalis = wto.articulusInteriorSingularisOptionalis?.toBo(),
        articulusInteriorOptionalisIteratus = wto.articulusInteriorOptionalisIteratus?.map { it.toBo() },
        appellatioOptionalisIteratus = wto.appellatioOptionalisIteratus?.map { it.toBo() },
        campusDiei = wto.campusDiei,
        campusBivalens = wto.campusBivalens,
        campusNumerorum = wto.campusNumerorum,
        iteratioSimpliciumTextuum = wto.iteratioSimpliciumTextuum,
    )

    fun toWto(bo: SilvaOptionumBO): SilvaOptionumWTO = SilvaOptionumWTO(
        indexUnicus = bo.indexUnicus,
        campusTextusObligatorius = bo.campusTextusObligatorius,
        campusTextusOptionalis = bo.campusTextusOptionalis,
        appellatio = bo.appellatio.toWto(),
        articulusInteriorSingularis = bo.articulusInteriorSingularis.toWto(),
        articulusInteriorIteratus = bo.articulusInteriorIteratus.map { it.toWto() },
        articulusInteriorSingularisOptionalis = bo.articulusInteriorSingularisOptionalis?.toWto(),
        articulusInteriorOptionalisIteratus = bo.articulusInteriorOptionalisIteratus?.map { it.toWto() },
        appellatioOptionalisIteratus = bo.appellatioOptionalisIteratus?.map { it.toWto() },
        campusDiei = bo.campusDiei,
        campusBivalens = bo.campusBivalens,
        campusNumerorum = bo.campusNumerorum,
        iteratioSimpliciumTextuum = bo.iteratioSimpliciumTextuum,
    )

    private fun ArticulusInteriorWTO.toBo() = ArticulusInteriorBO(
        scriptumTriviale = scriptumTriviale,
        numerusStupidus = numerusStupidus,
    )

    private fun ArticulusInteriorBO.toWto() = ArticulusInteriorWTO(
        scriptumTriviale = scriptumTriviale,
        numerusStupidus = numerusStupidus,
    )

    private fun AppellatioComisEnum.toBo() = when (this) {
        AppellatioComisEnum.VIR_HONORATUS -> AppellatioComis.VIR_HONORATUS
        AppellatioComisEnum.FEMINA_HONESTA -> AppellatioComis.FEMINA_HONESTA
    }

    private fun AppellatioComis.toWto() = when (this) {
        AppellatioComis.VIR_HONORATUS -> AppellatioComisEnum.VIR_HONORATUS
        AppellatioComis.FEMINA_HONESTA -> AppellatioComisEnum.FEMINA_HONESTA
    }
}
