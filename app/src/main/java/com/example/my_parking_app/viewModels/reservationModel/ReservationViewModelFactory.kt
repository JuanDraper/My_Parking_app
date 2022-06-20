package com.example.my_parking_app.viewModels.reservationModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.data.data_base.ParkingDataBase
import com.example.data.repository.DeleteReservationRepoImp
import com.example.data.service.Service
import com.example.domain.use_cases.DeleteReservationUseCase

class ReservationViewModelFactory(private val context: Context) : NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == ReservationViewModel::class.java) {
            ReservationViewModel(DeleteReservationUseCase().apply {
                deleteReservationRepository = DeleteReservationRepoImp(
                    Service(),
                    ParkingDataBase.getInstance(context)
                )
            }) as T
        } else {
            super.create(modelClass)
        }
    }

}