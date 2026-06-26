package senegai.server.service.opusmagnum.bo

import java.time.LocalDate

/**
 * Root business object of the OpusMagnum business context.
 *
 * All OpusMagnum services operate on this object as a single unit, never on the
 * nested [ArticulusInteriorBO] objects in isolation.
 */
data class SilvaOptionumBO(
    val indexUnicus: String,
    val campusTextusObligatorius: String,
    val campusTextusOptionalis: String?,
    val appellatio: AppellatioComis,
    val articulusInteriorSingularis: ArticulusInteriorBO,
    val articulusInteriorIteratus: List<ArticulusInteriorBO>,
    val articulusInteriorSingularisOptionalis: ArticulusInteriorBO?,
    val articulusInteriorOptionalisIteratus: List<ArticulusInteriorBO>?,
    val appellatioOptionalisIteratus: List<AppellatioComis>?,
    val campusDiei: LocalDate?,
    val campusBivalens: Boolean,
    val campusNumerorum: Int,
    val iteratioSimpliciumTextuum: List<String>,
)
