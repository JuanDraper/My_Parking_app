package com.example.my_parking_app
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: List<ParkingList>, private val onClick: (ParkingList) -> Unit) :
    RecyclerView.Adapter<CustomAdapter.ParkingViewHolder>(){

    class ParkingViewHolder(itemView: View, val onClick: (ParkingList) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val id: TextView = itemView.findViewById(R.id.lot_id)
        private val date: TextView = itemView.findViewById(R.id.date)
        private val hour: TextView = itemView.findViewById(R.id.hour)
        private var currentParking: ParkingList? = null

        init {
            itemView.setOnClickListener{
                currentParking?.let {
                    onClick(it)
                }
            }

        }

        fun bind(parking: ParkingList){
            currentParking = parking
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
