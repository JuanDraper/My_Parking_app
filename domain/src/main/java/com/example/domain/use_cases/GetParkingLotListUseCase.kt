package com.example.domain.use_cases

import com.example.domain.entities.MappedParkingLot
import com.example.domain.entities.MappedParkingLotList
import com.example.domain.utils.Result
import com.example.domain.repository.GetLotListRepo

class GetLotListUseCase{
    lateinit var getLotListRepository: GetLotListRepo
    suspend operator fun invoke(parkingId: String, localDataBase: Boolean): Result<MappedParkingLotList> {
        return getLotListRepository.getLotList(parkingId, localDataBase)
    }
    suspend operator fun invoke(parkingId: Int): Result<MappedParkingLot>? {
        return getLotListRepository.getLot(parkingId)
}
}
