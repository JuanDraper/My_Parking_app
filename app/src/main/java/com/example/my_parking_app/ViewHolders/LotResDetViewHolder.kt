package com.example.my_parking_app.ViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.ReservationDetails
import com.example.my_parking_app.databinding.LotReservationDetailBinding
import com.example.my_parking_app.tools.DateFormatter

class LotResDetViewHolder(view:View):RecyclerView.ViewHolder(view) {

        private val binding = LotReservationDetailBinding.bind(view)
        private val dateFormat: DateFormatter = DateFormatter()

        fun bind(actualReservation: ReservationDetails, onDeleteButtonListener:(ReservationDetails) -> Unit){
                binding.endHour.text = dateFormat.hourFormat(actualReservation.endDate)
                binding.endDay.text = dateFormat.dayFormat(actualReservation.endDate)
                binding.endDate.text = dateFormat.monthYearFormat(actualReservation.endDate)

                binding.dayStart.text = dateFormat.dayFormat(actualReservation.startDate)
                binding.hourStart.text = dateFormat.hourFormat(actualReservation.startDate)
                binding.startDate.text = dateFormat.monthYearFormat(actualReservation.startDate)

                binding.deleteButton.setOnClickListener{ onDeleteButtonListener(actualReservation)}

        }
}



