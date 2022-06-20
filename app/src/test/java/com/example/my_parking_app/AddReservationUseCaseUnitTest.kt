package com.example.my_parking_app

import com.example.domain.entities.ReservationDetails
import com.example.domain.repository.AddReservationRepo
import com.example.domain.use_cases.AddReservationUseCase
import com.example.domain.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AddReservationUseCaseUnitTest {
    @MockK
    lateinit var addRepo: AddReservationRepo
    lateinit var addUseCase: AddReservationUseCase

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        addUseCase = AddReservationUseCase()
        addUseCase.addReservationRepository = addRepo
    }
    @Test
    fun addReservationAddsToDataBase() {
        coEvery{ addRepo.addReservation("", ReservationDetails(), true)} answers
                { Result.Success(true) }
        runBlocking { addUseCase("", ReservationDetails(), true) }
        coVerify (exactly = 1){ addRepo.addReservation("", ReservationDetails(), true) }
    }

}