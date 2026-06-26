package senegai.server.restapi.wto.mapper

import senegai.server.restapi.wto.AppellatioComisEnum
import senegai.server.service.bo.AppellatioComis

/**
 * Maps between the WTOs (transport layer) and BOs (business layer).
 */
object AppellatioComisMapper {

    fun AppellatioComisEnum.toBo() = when (this) {
        AppellatioComisEnum.VIR_HONORATUS -> AppellatioComis.VIR_HONORATUS
        AppellatioComisEnum.FEMINA_HONESTA -> AppellatioComis.FEMINA_HONESTA
    }

    fun AppellatioComis.toWto() = when (this) {
        AppellatioComis.VIR_HONORATUS -> AppellatioComisEnum.VIR_HONORATUS
        AppellatioComis.FEMINA_HONESTA -> AppellatioComisEnum.FEMINA_HONESTA
    }
}
