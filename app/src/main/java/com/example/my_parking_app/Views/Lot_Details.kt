package com.example.my_parking_app.Views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_parking_app.OnItemClicked
import com.example.my_parking_app.R
import com.example.my_parking_app.RerservationDetails
import com.example.my_parking_app.Test
import com.example.my_parking_app.adapters.LotRDetAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Lot_Details : AppCompatActivity(), OnItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lot_details)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<RerservationDetails>()
        val adapter = LotRDetAdapter(Test.reservations, this, this)
        recyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
        val fab: FloatingActionButton =
            findViewById<FloatingActionButton>(R.id.fab2)
        fab.setOnClickListener {
            val intent = Intent(this, reservationActivity::class.java)
            startActivity(intent)

        }

    }
}