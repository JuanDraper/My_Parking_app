package com.example.my_parking_app

import com.example.domain.entities.ReservationDetails
import com.example.domain.repository.DeleteReservationRepo
import com.example.domain.use_cases.DeleteReservationUseCase
import com.example.domain.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteReservationUseCaseUnitTest {
    private lateinit var deleteReservationUseCase : DeleteReservationUseCase

    @MockK
    lateinit var repository: DeleteReservationRepo

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        deleteReservationUseCase = DeleteReservationUseCase()
        deleteReservationUseCase.deleteReservationRepository = repository
    }

    @Test
    fun testDeleteReservationUseCase(){
        val boolean = true
        coEvery { repository.deleteReservation("", ReservationDetails(), false) }answers { Result.Success(boolean) }
        runBlocking { deleteReservationUseCase("", ReservationDetails(), false) }
        coVerify (exactly = 1){repository.deleteReservation("", ReservationDetails(), false)  }
    }
}