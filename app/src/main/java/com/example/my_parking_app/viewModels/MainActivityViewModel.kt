package com.example.my_parking_app.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.my_parking_app.ParkingList
import com.example.my_parking_app.Test


class MainActivityViewModel : ViewModel() {
    private val recyclerModel : MutableLiveData<List<ParkingList>> by lazy {
        MutableLiveData<List<ParkingList>>().apply {
            loadRecyclerModel()
        }
    }

    fun getRecyclerModelObserver() : MutableLiveData<List<ParkingList>> {
        return recyclerModel
    }
    fun loadRecyclerModel(){

        recyclerModel.postValue(Test.parkings)


    }




}