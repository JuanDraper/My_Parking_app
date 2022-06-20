package com.example.my_parking_app.viewModels.addReservationModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.ReservationDetails
import com.example.domain.use_cases.AddReservationUseCase
import com.example.domain.use_cases.GetReservationListUseCase
import com.example.domain.utils.Result
import kotlinx.coroutines.launch

class AddViewModel(val addReservation: AddReservationUseCase, private val resUseCase : GetReservationListUseCase) : ViewModel() {

  private val parkingId: String = "-N0TU3sdjmm3RuXY7jLS"

  private var addReservationMutableLiveData: MutableLiveData<States> = MutableLiveData()

  enum class States {
    Fail,
    Occupied,
    Successful
  }

  val addReservationLiveData: LiveData<States>
    get() {
      return addReservationMutableLiveData
    }

  fun addReservation(res: ReservationDetails) = viewModelScope.launch{
    if(res.parkingLot > 0){

      val reservationState = States.Successful
      when(val reservationListOfLot = resUseCase(res.parkingLot)){
        is Result.Success -> {
          val resList = reservationListOfLot.value?.reservationList
          resList?.forEach { resFromLocal ->
            if (   resFromLocal.endDate > res.startDate
              || resFromLocal.startDate > res.endDate){
              addReservationMutableLiveData.postValue(States.Occupied)
              return@forEach
            }
          }
        }
        is Result.Failure -> addReservationMutableLiveData.postValue(States.Successful)
      }
      if (reservationState == States.Successful) {
        when (addReservation(parkingId, res, false)) {
          is Result.Success -> addReservationMutableLiveData.postValue(States.Successful)
          is Result.Failure -> addReservationMutableLiveData.postValue(States.Fail)
        }
      }
    } else {
      addReservationMutableLiveData.postValue(States.Fail)
    }
  }
}