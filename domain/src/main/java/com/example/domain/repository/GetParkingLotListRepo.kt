package com.example.domain.repository

import com.example.domain.entities.MappedParkingLot
import com.example.domain.entities.MappedParkingLotList
import com.example.domain.utils.Result
interface GetLotListRepo {
   suspend fun getLotList(parkingId: String, localDataBase: Boolean): Result<MappedParkingLotList>
   suspend fun getLot(parkingId: Int): Result<MappedParkingLot>
}