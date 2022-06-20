package com.example.data.service.response
import java.io.Serializable

data class LotListResponse(var lotList: List<LotResponse>,
                           var owner: String?,
                           var parkingSize: Int
) : Serializable
