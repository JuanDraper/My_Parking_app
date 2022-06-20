package com.example.domain.entities

import java.io.Serializable

class LotReservation(
    val id: Int = 0,
    val reservations: List<ReservationDetails> = mutableListOf<ReservationDetails>(),
    var isFree: Boolean = true,
) : Serializable
