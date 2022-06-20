package com.example.my_parking_app.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.data_base.ParkingDataBase
import com.example.data.repository.LotListRepoImp
import com.example.data.repository.ReservationListRepoImp
import com.example.data.service.Service
import com.example.domain.use_cases.GetLotListUseCase
import com.example.domain.use_cases.GetReservationListUseCase

class LotViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == LotViewModel::class.java) {
            LotViewModel(GetLotListUseCase().apply {
                getLotListRepository = LotListRepoImp(
                    Service(),
                    ParkingDataBase.getInstance(context),
                                 )
            }
                , GetReservationListUseCase().apply {
                    getReservationListRepository = ReservationListRepoImp(
                        Service(),
                        ParkingDataBase.getInstance(context),
                                            )
                }
            ) as T
        } else {
            super.create(modelClass)
        }
    }
}