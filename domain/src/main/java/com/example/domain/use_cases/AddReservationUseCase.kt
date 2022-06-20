package com.example.domain.use_cases

import com.example.domain.entities.ReservationDetails
import com.example.domain.utils.Result
import com.example.domain.repository.AddReservationRepo

class AddReservationUseCase {
    lateinit var addReservationRepository: AddReservationRepo
    suspend operator fun invoke(parkingId: String,
                                res: ReservationDetails,
                                fromLocal: Boolean
    ): Result<Boolean> {
        return addReservationRepository.addReservation(parkingId,res, fromLocal)
    }
    suspend operator fun invoke(lot: Int): Result<Boolean> {
        return addReservationRepository.addLot(lot)
    }
    suspend operator fun invoke(res: ReservationDetails): Result<Boolean> {
        return addReservationRepository.addRes(res)
    }
}