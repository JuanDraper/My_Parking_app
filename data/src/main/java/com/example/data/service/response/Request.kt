package com.example.data.service.response

data class Request(var authorizationCode: String,
                   var startDate: String,
                   var endDate: String,
                   var parkingLot: Int
)
