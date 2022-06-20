package com.example.domain.use_cases

import com.example.domain.entities.MappedReservationList
import com.example.domain.repository.GetReservationListRepo
import com.example.domain.utils.Result

class GetReservationListUseCase {
    lateinit var getReservationListRepository: GetReservationListRepo
    suspend operator fun invoke(parkingId: String, fromLocal : Boolean) : Result<MappedReservationList> = getReservationListRepository.getReservationList(parkingId, fromLocal)
    suspend operator fun invoke(parkingId: Int): Result<MappedReservationList> = getReservationListRepository.getReservationListInDepth(parkingId)

    }
