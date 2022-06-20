package com.example.my_parking_app.tools

import com.example.domain.entities.ReservationDetails

interface DeleteDialogCallBack {
    fun onDeleteClicked(authorizationCode: String, reservation: ReservationDetails)
}