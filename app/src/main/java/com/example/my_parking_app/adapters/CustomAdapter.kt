package com.example.my_parking_app.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_parking_app.OnItemClicked
import com.example.my_parking_app.ParkingList
import com.example.my_parking_app.R
import com.example.my_parking_app.ViewHolders.ParkingViewHolder

class   CustomAdapter(private val mList: List<ParkingList>, private val onItemClicked: OnItemClicked) :
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
