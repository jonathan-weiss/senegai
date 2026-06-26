package senegai.server.exampledata.opusmagnum

import org.springframework.stereotype.Component
import senegai.server.service.opusmagnum.bo.ArticulusInteriorBO

/**
 * Creates example data for [ArticulusInteriorBO].
 */
@Component
class ArticulusInteriorExampleDataCreator {

    /** A single example item; defaults can be overridden to build varied data. */
    fun create(
        scriptumTriviale: String = "scriptum-exemplum",
        numerusStupidus: Int = 42,
    ): ArticulusInteriorBO = ArticulusInteriorBO(
        scriptumTriviale = scriptumTriviale,
        numerusStupidus = numerusStupidus,
    )

    /** A list of distinct example items. */
    fun createList(): List<ArticulusInteriorBO> = listOf(
        create(scriptumTriviale = "scriptum-primum", numerusStupidus = 1),
        create(scriptumTriviale = "scriptum-secundum", numerusStupidus = 2),
        create(scriptumTriviale = "scriptum-tertium", numerusStupidus = 3),
    )
}
