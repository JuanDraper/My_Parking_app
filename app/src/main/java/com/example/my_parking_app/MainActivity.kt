package com.example.my_parking_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
        val fab: FloatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        fab.setOnClickListener{
            val intent2 = Intent(this, reservationActivity::class.java)
            startActivity(intent2)
        }
    }

    override fun onLotClick(position: Int) {
        val intent = Intent(this,Lot_Details::class.java)
        startActivity(intent)
    }
}