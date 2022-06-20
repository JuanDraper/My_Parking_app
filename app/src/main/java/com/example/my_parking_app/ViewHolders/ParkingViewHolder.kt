package com.example.my_parking_app.ViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.LotReservation
import com.example.domain.entities.ReservationDetails
import com.example.my_parking_app.databinding.ParkingTextBinding
import com.example.my_parking_app.tools.DateFormatter
import com.example.my_parking_app.tools.Text

class ParkingLotViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ParkingTextBinding.bind(view)
    private lateinit var reservation: ReservationDetails
    private val date: DateFormatter = DateFormatter()

    fun bind(lot: LotReservation, onClickListener: (LotReservation) -> Unit) {

        if (lot.isFree) {
            binding.markAsFree.text = Text.Free
            binding.day.text = ""
            binding.monthAndYear.text = ""
            binding.hourOfDay.text = ""
        } else {
            reservation = lot.reservations[0]
            binding.markAsFree.text = ""
            binding.day.text = date.dayFormat(reservation.startDate)
            binding.monthAndYear.text = date.monthYearFormat(reservation.startDate)
            binding.hourOfDay.text = date.hourFormat(reservation.startDate)
        }

        itemView.setOnClickListener { onClickListener(lot) }

        binding.lotNumber.text = lot.id.toString()
    }
}


