package com.example.my_parking_app.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.*
import com.example.domain.use_cases.GetLotListUseCase
import com.example.domain.use_cases.GetReservationListUseCase
import com.example.domain.utils.Result
import java.util.*
import kotlinx.coroutines.launch

class LotViewModel(
    private val getLotListUseCase: GetLotListUseCase,
    private val getReservationListUseCase: GetReservationListUseCase
) : ViewModel() {

    private var _lotReservation: MutableLiveData<List<LotReservation>> = MutableLiveData()

    val lotReservationLiveData: LiveData<List<LotReservation>>
        get() {
            return _lotReservation
        }

    fun lotReservationCreation(parkingId: String, fromLocal: Boolean = false) =
        viewModelScope.launch {
            val mappedLotList = getLotList(parkingId, fromLocal)
            val mappedReservationList = getReservationList(parkingId, fromLocal)

            val lotReservationList = mutableListOf<LotReservation>()
            mappedLotList.forEach { lot ->
                val reservationList = mutableListOf<ReservationDetails>()
                mappedReservationList.forEach { reservation ->
                    if (lot.parkingLot == reservation.parkingLot) {
                        reservationList.add(
                            ReservationDetails(
                                reservation.id,
                                reservation.startDate,
                                reservation.endDate,
                                reservation.authorizationCode,
                                reservation.parkingLot
                            )
                        )
                    }
                }
                val createdLotReservation = LotReservation(lot.parkingLot, reservationList)
                lotReservationList.add(createdLotReservation)
            }
            _lotReservation.postValue(lotReservationList)
        }

    private suspend fun getLotList(
        parkingId: String,
        fromLocal: Boolean
    ): List<MappedParkingLot> {
        return when (val getLots = getLotListUseCase(parkingId, fromLocal)) {
            is Result.Success -> getLots.value?.lotList ?: listOf()
            else -> listOf()
        }
    }

    private suspend fun getReservationList(parkingId: String, fromLocal: Boolean): List<MappedReservation> {
        return when (val getReservations = getReservationListUseCase(parkingId, fromLocal)) {
            is Result.Success -> getReservations.value?.reservationList ?: listOf()
            else -> listOf()
        }
    }

    fun availabilityHandler(lotReservationList: List<LotReservation>): Int {
        val date = Calendar.getInstance()
        var availability = lotReservationList.size
        lotReservationList.forEach { lot ->
            lot.isFree = true
            lot.reservations.forEach { reservation ->
                if (
                    reservation.startDate < date.timeInMillis &&
                    reservation.endDate > date.timeInMillis
                ) {
                    lot.isFree = false
                    availability--
                }
            }
        }
        return availability
    }
}

