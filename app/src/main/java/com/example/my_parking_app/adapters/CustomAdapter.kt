package com.example.my_parking_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.LotReservation
import com.example.my_parking_app.R
import com.example.my_parking_app.ViewHolders.ParkingLotViewHolder

class CustomAdapter(
    private val itemsList: List<LotReservation>,
    private val onClickListener: (LotReservation) -> Unit
) : RecyclerView.Adapter<ParkingLotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingLotViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ParkingLotViewHolder(layoutInflater.inflate(R.layout.parking_text, parent, false))
    }

    override fun onBindViewHolder(holder: ParkingLotViewHolder, position: Int) {
        val item = itemsList[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount(): Int = itemsList.size
}