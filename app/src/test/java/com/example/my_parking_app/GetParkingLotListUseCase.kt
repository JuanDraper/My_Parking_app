package com.example.my_parking_app

import com.example.data.service.response.LotListResponse
import com.example.domain.entities.MappedParkingLotList
import com.example.domain.repository.GetLotListRepo
import com.example.domain.use_cases.GetLotListUseCase
import com.example.domain.utils.Result
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetParkingLotListUseCaseUnitTesting {
    private lateinit var getParkingLotListUseCase : GetLotListUseCase

    @MockK
    lateinit var repository : GetLotListRepo

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        getParkingLotListUseCase = GetLotListUseCase()
        getParkingLotListUseCase.getLotListRepository = repository
    }

    @Test
    fun testLotUseCase(){
        val parkingLotListResponse : MappedParkingLotList = mockk()
        coEvery { repository.getLotList("", false) } answers { Result.Success(parkingLotListResponse) }
        runBlocking{
            getParkingLotListUseCase("", false)

        }
        coVerify (exactly = 1) { repository.getLotList("", false) }
    }

}

