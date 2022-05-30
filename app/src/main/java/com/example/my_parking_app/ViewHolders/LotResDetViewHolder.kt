package com.example.my_parking_app.ViewHolders

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.my_parking_app.R
import com.example.my_parking_app.RerservationDetails

class LotResDetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val StartDate: TextView = itemView.findViewById(R.id.StartingDate)
        private val StartHour: TextView = itemView.findViewById(R.id.StartingHour)
        private val FinishDate: TextView = itemView.findViewById(R.id.FinishDate)
        private val FinishHour: TextView = itemView.findViewById(R.id.FinishHour)
        val DeleteBTn: Button = itemView.findViewById(R.id.deleteBtn)
                fun bind(reservationDetail: RerservationDetails){
                StartDate.text = reservationDetail.StartDate
                StartHour.text = reservationDetail.StartHour
                FinishDate.text = reservationDetail.FinishDate
                FinishHour.text = reservationDetail.FinishHour
        }


        }