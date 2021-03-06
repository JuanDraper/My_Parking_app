package com.example.data.service

import com.example.data.service.api.APIService
import com.example.data.service.response.LotListResponse
import com.example.data.service.response.ReservationListResponse
import com.example.data.service.response.Request
import com.example.domain.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Service (private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend fun getLotList(parkingId: String) : Result<LotListResponse?> {
        var result : Result<LotListResponse?>

        withContext(dispatcher){
            result = try {
                val callResponse = RetrofitFactory.getRetrofit().create(APIService::class.java).getLotList(parkingId)
                if(callResponse.isSuccessful){
                    Result.Success((callResponse.body()))
                }
                else {
                    Result.Failure(Exception(callResponse.message()))
                }
            }
            catch(e: Exception){
                Result.Failure(e)
            }
        }
        return result
    }

    suspend fun getReservationList(parkingId: String) : Result<ReservationListResponse?> {
        var result : Result<ReservationListResponse?>

        withContext(dispatcher){
            result = try {
                val callResponse = RetrofitFactory.getRetrofit().create(APIService::class.java).getReservationList(parkingId)
                if(callResponse.isSuccessful){
                    Result.Success((callResponse.body()))
                }
                else {
                    Result.Failure(Exception(callResponse.message()))
                }
            }
            catch(e: Exception){
                Result.Failure(e)
            }
        }
        return result
    }

    suspend fun deleteReservation(parkingId: String, reservationId: String): Result<Boolean> {
        var result : Result<Boolean>
        withContext(dispatcher){
            result = try {
                val callResponse = RetrofitFactory.getRetrofit().create(APIService::class.java).deleteReservation(parkingId,reservationId)
                if(callResponse.isSuccessful){
                    Result.Success(true)
                }
                else {
                    Result.Failure(Exception(callResponse.message()))
                }
            }
            catch(e: Exception){
                Result.Failure(e)
            }
        }
        return result
    }

    suspend fun addReservation(parkingId: String, reservation: Request) : Result<Boolean> {
        var result : Result<Boolean>
        withContext(dispatcher){
            result = try {
                val callResponse = RetrofitFactory.getRetrofit().create(APIService::class.java).postReservation(parkingId,reservation)
                if(callResponse.isSuccessful){
                    Result.Success(true)
                }
                else {
                    Result.Failure(Exception(callResponse.message()))
                }
            }
            catch(e: Exception){
                Result.Failure(e)
            }
        }
        return result
    }



}