package senegai.server.exampledata.opusmagnum

import org.springframework.stereotype.Component
import senegai.server.service.opusmagnum.bo.AppellatioComis

/**
 * Creates example data for the [AppellatioComis] business enum.
 */
@Component
class AppellatioComisExampleDataCreator {

    /** A single representative example value. */
    fun create(): AppellatioComis = AppellatioComis.VIR_HONORATUS

    /** All enum values as example data. */
    fun createList(): List<AppellatioComis> = AppellatioComis.entries.toList()
}
