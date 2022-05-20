package com.example.my_parking_app

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.my_parking_app.databinding.FragmentAddReservationBinding
import java.util.*

class ReservationAddFragment : Fragment(R.layout.fragment_add_reservation) {

    private lateinit var binding: FragmentAddReservationBinding
    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        binding = FragmentAddReservationBinding.bind(view)

        binding.apply {
            StartDate.setOnClickListener {
                pickDate(R.style.dateStart_)
            }

            EndDate.setOnClickListener {
                pickDate(R.style.dateEnd)
            }
        }
        val spinnerList = listOf(
            "Parking lot 1",
            "Parking lot 2",
            "Parking lot 3",
            "Parking lot 4",
            "Parking lot 5"
        )
        val adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            spinnerList
        )
        binding.spinner.apply {
            this.adapter = adapter
            onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        Toast.makeText(
                            activity,
                            "${parent?.getItemAtPosition(position).toString()} selected",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }
        }
    }

    private fun pickDate(style: Int = R.style.dateStart_) {
        val cal = Calendar.getInstance()
        val dateTimeListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            Toast.makeText(
                activity,
                "$day/$month/$year",
                Toast.LENGTH_LONG
            ).show()


        }
        DatePickerDialog(
            requireContext(),
            style,
            dateTimeListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
        val hourListener = TimePickerDialog.OnTimeSetListener { _, hour, minutes ->
            Toast.makeText(
                activity,
                "$hour:$minutes",
                Toast.LENGTH_LONG
            ).show()
        }

        TimePickerDialog(
            activity,
            style,
            hourListener,cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),false
        ).show()


    }

    }


