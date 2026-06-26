package senegai.server.exampledata.opusmagnum

import org.springframework.stereotype.Component
import senegai.server.service.opusmagnum.bo.SilvaOptionumBO
import java.time.LocalDate

/**
 * Creates example data for the OpusMagnum root business object [SilvaOptionumBO].
 *
 * Delegates the creation of nested objects to the dedicated example data creators of the
 * respective business objects.
 */
@Component
class SilvaOptionumExampleDataCreator(
    private val articulusInteriorExampleDataCreator: ArticulusInteriorExampleDataCreator,
    private val appellatioComisExampleDataCreator: AppellatioComisExampleDataCreator,
) {

    /** A single fully populated example aggregate with the given [indexUnicus]. */
    fun create(indexUnicus: String): SilvaOptionumBO = SilvaOptionumBO(
        indexUnicus = indexUnicus,
        campusTextusObligatorius = "textus-obligatorius-$indexUnicus",
        campusTextusOptionalis = "textus-optionalis-$indexUnicus",
        appellatio = appellatioComisExampleDataCreator.create(),
        articulusInteriorSingularis = articulusInteriorExampleDataCreator.create(),
        articulusInteriorIteratus = articulusInteriorExampleDataCreator.createList(),
        articulusInteriorSingularisOptionalis = articulusInteriorExampleDataCreator.create(
            scriptumTriviale = "scriptum-optionalis",
            numerusStupidus = 99,
        ),
        articulusInteriorOptionalisIteratus = articulusInteriorExampleDataCreator.createList(),
        appellatioOptionalisIteratus = appellatioComisExampleDataCreator.createList(),
        campusDiei = LocalDate.of(2026, 1, 1),
        campusBivalens = true,
        campusNumerorum = 7,
        iteratioSimpliciumTextuum = listOf("textus-unus", "textus-duo", "textus-tres"),
    )

    /** A list of distinct example aggregates. */
    fun createList(): List<SilvaOptionumBO> = listOf(
        create(indexUnicus = "exemplum-1"),
        create(indexUnicus = "exemplum-2"),
        create(indexUnicus = "exemplum-3"),
    )
}
