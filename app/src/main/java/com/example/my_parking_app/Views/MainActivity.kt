package com.example.my_parking_app.Views

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_parking_app.OnItemClicked
import com.example.my_parking_app.ParkingList
import com.example.my_parking_app.R
import com.example.my_parking_app.Test
import com.example.my_parking_app.adapters.CustomAdapter
import com.example.my_parking_app.viewModels.MainActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), OnItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model: MainActivityViewModel by viewModels()
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<ParkingList>()
        model.getRecyclerModelObserver().observe(this, Observer<List<ParkingList>>{
            val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
            val adapter = CustomAdapter(Test.parkings, this)
            recyclerview.adapter = adapter
            adapter.notifyDataSetChanged()
        }


        )
        val fab: FloatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        fab.setOnClickListener{
            val intent2 = Intent(this, reservationActivity::class.java)
            startActivity(intent2)
        }
    }

    override fun onLotClick(position: Int) {
        val intent = Intent(this, Lot_Details::class.java)
        startActivity(intent)
    }
    fun recyclerViewStart(){
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CustomAdapter(Test.parkings, this)
        recyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}