package com.example.domain.use_cases

import com.example.domain.entities.ReservationDetails
import com.example.domain.utils.Result
import com.example.domain.repository.DeleteReservationRepo

class DeleteReservationUseCase{
    lateinit var deleteReservationRepository: DeleteReservationRepo
    suspend operator fun invoke(parkingId: String,
                                res: ReservationDetails,
                                fromLocal: Boolean ): Result<Boolean> {
        return deleteReservationRepository.deleteReservation(parkingId, res,fromLocal)
    }
}