package com.example.my_parking_app.Views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.my_parking_app.databinding.ActivityReservationBinding

class reservationActivity : AppCompatActivity() {
    lateinit var binding: ActivityReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}