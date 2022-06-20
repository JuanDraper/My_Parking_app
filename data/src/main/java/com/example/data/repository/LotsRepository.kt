package com.example.data.repository


import com.example.data.converter.Converter
import com.example.data.data_base.ParkingDataBase

import com.example.data.service.Service
import com.example.domain.entities.MappedParkingLot
import com.example.domain.entities.MappedParkingLotList

import com.example.domain.utils.Result
import com.example.domain.repository.GetLotListRepo


class LotListRepoImp(private val service : Service, private val dataBase : ParkingDataBase): GetLotListRepo {


    override suspend fun getLotList(parkingId: String, localDataBase: Boolean): Result<MappedParkingLotList> {
        return when(localDataBase){
            true -> {getFromLocal()}
            else -> {getFromRemote(parkingId)}
        }
    }

    override suspend fun getLot(parkingId: Int): Result<MappedParkingLot> {
        val lotRoom = dataBase.lotDao().selectByParkingLot(parkingId)
        return if (lotRoom == null) {
            Result.Success(Converter.lotRoomToLotModel(lotRoom))
        } else {
            Result.Failure(null)
        }
    }

    private suspend fun getFromRemote(parkingId: String): Result<MappedParkingLotList> {
        return when (val result =  service.getLotList(parkingId)){
            is Result.Success -> {
                Result.Success(Converter.parkingListResponseMapping(result.value!!))
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            } else -> return Result()
        }
    }

    private suspend fun getFromLocal(): Result<MappedParkingLotList> {
        val list = dataBase.lotDao().selectFromLotList()
        return Result.Success(Converter.lotRoomListToParkingLotListModel(list))
        }
    }





