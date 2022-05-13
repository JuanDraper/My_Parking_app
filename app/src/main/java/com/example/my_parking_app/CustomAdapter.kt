package com.example.my_parking_app
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: List<ParkingList>, private val onItemClicked: OnItemClicked) :
    RecyclerView.Adapter<ParkingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.parking_text, parent, false)
        return ParkingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        val parking = mList[position]
        holder.bind(parking)
        holder.itemView.setOnClickListener{
            onItemClicked.onLotClick(position)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}
