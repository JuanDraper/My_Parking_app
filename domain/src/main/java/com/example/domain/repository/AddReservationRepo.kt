package com.example.domain.repository

import com.example.domain.entities.ReservationDetails
import com.example.domain.utils.Result

interface AddReservationRepo {
    suspend fun addReservation(parkingId: String, res: ReservationDetails, fromLocal: Boolean): Result<Boolean>
    suspend fun addLot( lot: Int): Result<Boolean>
    suspend fun addRes(res: ReservationDetails): Result<Boolean>
}