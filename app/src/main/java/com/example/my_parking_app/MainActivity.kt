package com.example.my_parking_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<ParkingList>()
        val adapter = CustomAdapter(Test.parkings, this)
        recyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onLotClick(position: Int) {
        val intent = Intent(this,Lot_Details::class.java)
        startActivity(intent)
    }
}