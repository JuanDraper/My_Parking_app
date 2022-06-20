package com.example.data.repository

import com.example.data.data_base.ParkingDataBase
import com.example.data.service.Service
import com.example.domain.entities.ReservationDetails
import com.example.domain.utils.Result
import com.example.domain.repository.DeleteReservationRepo

class DeleteReservationImp(private val deleteFromRemote: Service, private val deleteFromLocal: ParkingDataBase): DeleteReservationRepo {



    override suspend fun deleteReservation(parkingId: String, res: ReservationDetails, fromLocal: Boolean
    ): Result<Boolean> {
        return if (fromLocal){
            deleteFromLocal(res.id)
            Result.Success(true)
        } else{
           deleteFromRemote(parkingId,res)
        }
    }

    private suspend fun deleteFromRemote(parkingId: String, res: ReservationDetails): Result<Boolean> {
        return when (val result = deleteFromRemote.deleteReservation(parkingId, res.id)) {
            is Result.Success -> {
                Result.Success(result.value as Boolean)
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
        }
    }

    private suspend fun deleteFromLocal(resId: String) {
        return deleteFromLocal.reservationDataBaseDao().deleteReservation(resId)

    }
}