package senegai.server.restapi.opusmagnum.wto

import java.time.LocalDate

/**
 * Root WTO (Web Transfer Object) of the OpusMagnum context, mirroring the Angular
 * `SilvaOptionumWTO` interface field by field so it serializes 1:1 for the client.
 */
data class SilvaOptionumWTO(
    val indexUnicus: String,
    val campusTextusObligatorius: String,
    val campusTextusOptionalis: String?,
    val appellatio: AppellatioComisEnum,
    val articulusInteriorSingularis: ArticulusInteriorWTO,
    val articulusInteriorIteratus: List<ArticulusInteriorWTO>,
    val articulusInteriorSingularisOptionalis: ArticulusInteriorWTO?,
    val articulusInteriorOptionalisIteratus: List<ArticulusInteriorWTO>?,
    val appellatioOptionalisIteratus: List<AppellatioComisEnum>?,
    val campusDiei: LocalDate?,
    val campusBivalens: Boolean,
    val campusNumerorum: Int,
    val iteratioSimpliciumTextuum: List<String>,
)
