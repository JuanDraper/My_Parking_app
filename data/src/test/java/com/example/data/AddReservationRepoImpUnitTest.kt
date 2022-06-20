package com.example.data

import com.example.data.data_base.ParkingDataBase
import com.example.data.repository.AddReservationRepoImp
import com.example.data.service.Service
import com.example.domain.entities.ReservationDetails
import com.example.domain.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddReservationRepoImpUnitTest {
    @MockK
    private lateinit var service: Service
    private lateinit var database: ParkingDataBase
    private lateinit var addResRepoImp : AddReservationRepoImp

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        addResRepoImp = AddReservationRepoImp(service,database)

    }
    @Test
    fun testAddReservation(){
        val result = Result.Success(true)
        coEvery { addResRepoImp.addReservation("parkingId",ReservationDetails(),false) } answers  { result}
        runBlocking{ addResRepoImp.addReservation("parkingID",ReservationDetails(), false)}
        coVerify (exactly = 1){addResRepoImp.addReservation("parkingId", ReservationDetails(), false)  }
    }
}