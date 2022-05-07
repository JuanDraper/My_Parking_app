package com.example.my_parking_app
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: List<parkingList>, private val onClick: (parkingList) -> Unit) :
    RecyclerView.Adapter<CustomAdapter.ParkingViewHolder>(){

    class ParkingViewHolder(itemView: View, val onClick: (parkingList) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val parkingTextView: TextView = itemView.findViewById(R.id.parking_text)
        private var currentParking: parkingList? = null

        init {
            itemView.setOnClickListener{
                currentParking?.let {
                    onClick(it)
                }
            }

        }

        fun bind(parking: parkingList){
            currentParking = parking
            parkingTextView.text = parking.id.toString()
        }

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.parking_text, parent, false)
        return ParkingViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        val parking = mList[position]
        holder.bind(parking)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}
