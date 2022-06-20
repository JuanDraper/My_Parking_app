package com.example.data.service.response

import com.example.domain.entities.LotReservation
import java.io.Serializable

data class LotResponse(var parkingLot: Int) : Serializable {
    fun toLotParkingLotModel(): LotReservation {
        return LotReservation(parkingLot, listOf())
    }
}
