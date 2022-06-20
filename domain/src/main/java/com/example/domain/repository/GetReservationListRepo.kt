package com.example.domain.repository

import com.example.domain.entities.MappedReservationList
import com.example.domain.utils.Result

interface GetReservationListRepo {
    suspend fun getReservationList(parkingId: String, fromLocal : Boolean): Result<MappedReservationList>
    suspend fun getReservationListInDepth(parkingId: Int): Result<MappedReservationList>
}