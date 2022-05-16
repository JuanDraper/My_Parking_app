package com.example.my_parking_app
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class LotRDetAdapter(private val RDList: List<RerservationDetails>, private val onItemClicked: OnItemClicked):
    RecyclerView.Adapter<LotResDetViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LotResDetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lot_reservation_detail, parent, false)
        return LotResDetViewHolder(view)

    }

    override fun onBindViewHolder(holder: LotResDetViewHolder, position: Int) {
        val details = RDList[position]
        holder.bind(details)
        holder.DeleteBTn.setOnClickListener {
            onItemClicked!!.onLotClick(position)
            removeItem(position)
        }
    }

    override fun getItemCount(): Int {
        return RDList.size
    }
    private fun removeItem(position: Int) {
        val list = mutableListOf<RerservationDetails>()
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}

