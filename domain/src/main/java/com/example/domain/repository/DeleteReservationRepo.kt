package com.example.domain.repository

import com.example.domain.entities.ReservationDetails
import com.example.domain.utils.Result

interface DeleteReservationRepo {
    suspend fun deleteReservation(parkingId: String, res: ReservationDetails, fromLocal : Boolean): Result<Boolean>
}