package com.example.my_parking_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.ReservationDetails
import com.example.my_parking_app.R
import com.example.my_parking_app.ViewHolders.LotResDetViewHolder

class LotRDetAdapter(
    private val itemsList: List<ReservationDetails>,
    private val onClickListener: (ReservationDetails) -> Unit
) : RecyclerView.Adapter<LotResDetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LotResDetViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return LotResDetViewHolder(
            layoutInflater.inflate(R.layout.lot_reservation_detail, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LotResDetViewHolder, position: Int) {
        val item = itemsList[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount(): Int = itemsList.size
}




