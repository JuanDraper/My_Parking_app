package com.example.data

import com.example.data.service.RetrofitFactory
import com.example.data.service.Service
import com.example.data.service.response.LotListResponse
import com.example.domain.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ServiceUnitTest {
    @MockK
    private lateinit var dispatcher: CoroutineDispatcher
    private lateinit var service : Service
    private lateinit var retrofit : RetrofitFactory

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dispatcher = Dispatchers.IO
        service = Service()
        retrofit = RetrofitFactory
    }

    @Test
    fun testServiceGetLotListRetrofitUsage() {
        val result = Result<LotListResponse?>()
        coEvery { service.getLotList("-N0TU3sdjmm3RuXY7jLS") } answers{
            result
        }
        runBlocking { service.getLotList("-N0TU3sdjmm3RuXY7jLS") }
        coVerify (exactly = 1) { retrofit.getRetrofit() }
    }



}