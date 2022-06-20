package com.example.my_parking_app.viewModels.addReservationModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.data_base.ParkingDataBase
import com.example.data.repository.AddReservationImp
import com.example.data.repository.GetReservationListImp
import com.example.data.service.Service
import com.example.domain.use_cases.AddReservationUseCase
import com.example.domain.use_cases.GetReservationListUseCase

class AddViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            AddViewModel::class.java -> {
                AddViewModel(
                    AddReservationUseCase().apply {
                    addReservationRepository = AddReservationImp(
                        Service(),
                        ParkingDataBase.getInstance(context)
                    )
                },
                    GetReservationListUseCase().apply {
                        getReservationListRepository = GetReservationListImp(
                            Service(),
                            ParkingDataBase.getInstance(context)
                        )
                    }
                ) as T
            }
            else -> super.create(modelClass)
        }
    }
}
