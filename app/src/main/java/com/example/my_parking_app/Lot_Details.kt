package com.example.my_parking_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Lot_Details : AppCompatActivity(), OnItemClicked{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lot_details)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<RerservationDetails>()
        val adapter = LotRDetAdapter(Test.reservations, this)

    }
}