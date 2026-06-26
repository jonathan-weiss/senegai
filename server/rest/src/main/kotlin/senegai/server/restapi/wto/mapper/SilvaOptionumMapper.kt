package senegai.server.restapi.wto.mapper

import senegai.server.restapi.wto.SilvaOptionumWTO
import senegai.server.restapi.wto.mapper.AppellatioComisMapper.toBo
import senegai.server.restapi.wto.mapper.AppellatioComisMapper.toWto
import senegai.server.restapi.wto.mapper.ArticulusInteriorMapper.toBo
import senegai.server.restapi.wto.mapper.ArticulusInteriorMapper.toWto
import senegai.server.service.bo.SilvaOptionumBO

/**
 * Maps between the WTOs (transport layer) and BOs (business layer).
 */
object SilvaOptionumMapper {

    fun SilvaOptionumWTO.toBo(): SilvaOptionumBO = SilvaOptionumBO(
        indexUnicus = indexUnicus,
        campusTextusObligatorius = campusTextusObligatorius,
        campusTextusOptionalis = campusTextusOptionalis,
        appellatio = appellatio.toBo(),
        articulusInteriorSingularis = articulusInteriorSingularis.toBo(),
        articulusInteriorIteratus = articulusInteriorIteratus.map { it.toBo() },
        articulusInteriorSingularisOptionalis = articulusInteriorSingularisOptionalis?.toBo(),
        articulusInteriorOptionalisIteratus = articulusInteriorOptionalisIteratus?.map { it.toBo() },
        appellatioOptionalisIteratus = appellatioOptionalisIteratus?.map { it.toBo() },
        campusDiei = campusDiei,
        campusBivalens = campusBivalens,
        campusNumerorum = campusNumerorum,
        iteratioSimpliciumTextuum = iteratioSimpliciumTextuum,
    )

    fun SilvaOptionumBO.toWto(): SilvaOptionumWTO = SilvaOptionumWTO(
        indexUnicus = indexUnicus,
        campusTextusObligatorius = campusTextusObligatorius,
        campusTextusOptionalis = campusTextusOptionalis,
        appellatio = appellatio.toWto(),
        articulusInteriorSingularis = articulusInteriorSingularis.toWto(),
        articulusInteriorIteratus = articulusInteriorIteratus.map { it.toWto() },
        articulusInteriorSingularisOptionalis = articulusInteriorSingularisOptionalis?.toWto(),
        articulusInteriorOptionalisIteratus = articulusInteriorOptionalisIteratus?.map { it.toWto() },
        appellatioOptionalisIteratus = appellatioOptionalisIteratus?.map { it.toWto() },
        campusDiei = campusDiei,
        campusBivalens = campusBivalens,
        campusNumerorum = campusNumerorum,
        iteratioSimpliciumTextuum = iteratioSimpliciumTextuum,
    )
}
