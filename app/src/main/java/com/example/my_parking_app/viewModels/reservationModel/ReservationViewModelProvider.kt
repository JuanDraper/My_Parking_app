package com.example.my_parking_app.viewModels.reservationModel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class ReservationViewModelProvider(activity: FragmentActivity?) : ViewModelProvider(
    (activity as ViewModelStoreOwner),
    ReservationViewModelFactory(activity.applicationContext)
)