    package com.example.data.service

import com.example.data.service.response.ReservationListResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


    object RetrofitFactory {

        private const val BASE_URL = "https://parking-lot-5aace-default-rtdb.firebaseio.com/"

        private val httpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGsonBuilder()))
                .client(httpClient.build())
                .build()
        }

        private fun getGsonBuilder() =
            GsonBuilder().registerTypeAdapter(ReservationListResponse::class.java, RealTimeDatabaseDeserializer()).create()
    }

