package com.example.domain.entities

class MappedReservation (var id: String,
                         val authorizationCode: String,
                         val startDate: Long,
                         val endDate: Long,
                         val parkingLot: Int,
) {}