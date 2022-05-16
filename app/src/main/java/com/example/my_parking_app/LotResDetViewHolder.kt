package com.example.my_parking_app

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class LotResDetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val StartDate: TextView = itemView.findViewById(R.id.StartingDate)
        private val StartHour: TextView = itemView.findViewById(R.id.StartingHour)
        private val FinishDate: TextView = itemView.findViewById(R.id.FinishDate)
        private val FinishHour: TextView = itemView.findViewById(R.id.FinishHOur)
                fun bind(reservationDetail: RerservationDetails){
                StartDate.text = reservationDetail.StartDate
                StartHour.text = reservationDetail.StartHour
                FinishDate.text = reservationDetail.FinishDate
                FinishHour.text = reservationDetail.FinishHour
        }


        }
