package com.example.data.repository

import com.example.data.data_base.ParkingDataBase
import com.example.data.data_base.entities.LotRoom
import com.example.data.data_base.entities.ReservationRoom
import com.example.data.service.Service
import com.example.data.service.response.Request
import com.example.domain.entities.ReservationDetails
import com.example.domain.repository.AddReservationRepo
import com.example.domain.utils.Result

class AddReservationRepoImp(
    private val addService: Service = Service(),
    private val dataBase: ParkingDataBase

) : AddReservationRepo {

  override suspend fun addReservation(
      parkingId: String,
      res: ReservationDetails,
      fromLocal: Boolean
  ): Result<Boolean> {

    return if (fromLocal) {
      resToDB(res)
      Result.Success(true)
    } else {
        when(resToRemote(parkingId, res)){
            is Result.Success -> Result.Success(true)
            else -> Result.Failure(null)
        }
    }
  }

  override suspend fun addLot(parkingLot: Int): Result<Boolean> {
    lotToDB(parkingLot)
    return Result.Success(true)
  }

  override suspend fun addRes(res: ReservationDetails): Result<Boolean> {
    resToDB(res)
    return Result.Success(true)
  }
  private suspend fun lotToDB(parkingLot: Int) {
    dataBase.lotDao().insertNewLot(LotRoom(parkingLot))
  }
  private suspend fun resToDB(res: ReservationDetails) {
    dataBase
        .reservationDataBaseDao()
        .insertNewReservation(
            ReservationRoom(
                res.id,
                res.authorizationCode,
                res.startDate,
                res.endDate,
                res.parkingLot))
  }
    private suspend fun resToRemote(id: String,res : ReservationDetails): Result<Boolean>   {
        val newReservation =
            Request(
                res.authorizationCode,
                res.startDate.toString(),
                res.endDate.toString(),
                res.parkingLot)

        val result = addService.addReservation(id, newReservation)
        return when (val result = addService.addReservation(id, newReservation)) {
            is Result.Success -> {
                Result.Success(result.value as Boolean)
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }else -> return Result()
        }
    }
}
