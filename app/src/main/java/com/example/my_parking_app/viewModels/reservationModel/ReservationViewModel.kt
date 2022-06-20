package com.example.my_parking_app.viewModels.reservationModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.ReservationDetails
import com.example.domain.use_cases.DeleteReservationUseCase
import com.example.domain.utils.Result
import kotlinx.coroutines.launch

class ReservationViewModel(private val deleteReservation: DeleteReservationUseCase) : ViewModel() {

    private var _delete: MutableLiveData<Boolean> = MutableLiveData()

    val success: LiveData<Boolean>
        get() {
            return _delete
        }

    fun deleteReservation(
        parkingId: String,
        authCode: String,
        reservation: ReservationDetails
    ) =
        viewModelScope.launch {
            if (reservation.authorizationCode == authCode) {
                deleteReservation(parkingId, reservation, true)
                when (deleteReservation.invoke(parkingId, reservation, false)) {
                    is Result.Success -> {
                        _delete.value = true
                    }
                    is Result.Failure -> {
                        _delete.value = false
                    }
                }
            } else {
                _delete.value = false
            }
        }
}