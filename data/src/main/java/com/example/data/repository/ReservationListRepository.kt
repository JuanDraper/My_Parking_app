package com.example.data.repository

import com.example.data.converter.Converter
import com.example.data.data_base.ParkingDataBase
import com.example.data.service.Service
import com.example.domain.entities.MappedReservationList
import com.example.domain.utils.Result
import com.example.domain.repository.GetReservationListRepo

class ReservationListRepoImp(private val service :Service, private val dataBase : ParkingDataBase): GetReservationListRepo {



    override suspend fun getReservationList(parkingId: String, fromLocal: Boolean): Result<MappedReservationList> {
        return when(fromLocal){
            true -> {
                getReservationListFromLocal()
            }
            else -> getReservationListFromRemote(parkingId)
        }
    }

    private suspend fun getReservationListFromRemote(parkingId: String): Result<MappedReservationList> {
        return when (val result =  service.getReservationList(parkingId)){
            is Result.Success -> {
                Result.Success(Converter.reservationListResponseMapping(result.value))
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }else -> return Result()
        }

    }

    private suspend fun getReservationListFromLocal(): Result<MappedReservationList> {
        val reservationList = dataBase.reservationDataBaseDao().selectFromReservationList()
        return Result.Success(Converter.reservationRoomListToReservationListModel(reservationList))

    }

    override suspend fun getReservationListInDepth(parkingId: Int):Result<MappedReservationList> {
        val reservationList = dataBase.reservationDataBaseDao().selectFromReservationListByParkingLot(parkingId)
        return Result.Success(Converter.reservationRoomListToReservationListModel(reservationList))
    }
}