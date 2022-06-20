package com.example.my_parking_app

import com.example.domain.entities.MappedReservationList
import com.example.domain.repository.GetReservationListRepo
import com.example.domain.use_cases.GetReservationListUseCase
import com.example.domain.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetReservationListUseCaseUnitTest {
    private lateinit var getReservationListUseCase: GetReservationListUseCase

    @MockK
    private lateinit var repo : GetReservationListRepo

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        getReservationListUseCase = GetReservationListUseCase()
        getReservationListUseCase.getReservationListRepository = repo
    }
    @Test
    fun testGetReservationListUseCase(){
        val mappedResList : MappedReservationList = mockk()
        coEvery { repo.getReservationList("", false) } answers { Result.Success(mappedResList) }
        runBlocking{getReservationListUseCase("", false)}
        coVerify (exactly = 1){repo.getReservationList("", false)  }
    }
}