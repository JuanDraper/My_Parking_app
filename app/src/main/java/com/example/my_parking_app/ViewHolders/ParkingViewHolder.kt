package com.example.my_parking_app.ViewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.my_parking_app.ParkingList
import com.example.my_parking_app.R

class ParkingViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val id: TextView = itemView.findViewById(R.id.lot_id)
    private val date: TextView = itemView.findViewById(R.id.date)
    private val hour: TextView = itemView.findViewById(R.id.hour)



    fun bind(parking: ParkingList){
        id.text = parking.id
        if(parking.vacancy) {
            date.text = parking.date
            hour.text = parking.hour
        }
        else{
            date.setText(R.string.free2)
            hour.text = ""
        }
    }

    }
