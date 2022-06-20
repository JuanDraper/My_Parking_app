package com.example.my_parking_app.Views

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.domain.entities.ReservationDetails
import com.example.my_parking_app.R
import com.example.my_parking_app.databinding.FragmentAddReservationBinding
import com.example.my_parking_app.viewModels.addReservationModel.AddViewModel
import com.example.my_parking_app.viewModels.addReservationModel.AddViewModelProvider
import java.text.SimpleDateFormat
import java.util.*
import com.example.my_parking_app.viewModels.addReservationModel.AddViewModel.States

class AddReservationFragment: Fragment(R.layout.fragment_add_reservation) {

    private lateinit var binding: FragmentAddReservationBinding
    private var parkinLot: Int = 0
    private lateinit var startDate: Calendar
    private lateinit var endDate: Calendar
    private lateinit var authCode: String

    private val viewModel by lazy{
        activity?.let { AddViewModelProvider(it).get(AddViewModel::class.java) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddReservationBinding.bind(view)


        binding.backButton.setOnClickListener{
            binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_parkingLotsFragment)  // switching screen to reservationsFragment
        }


        val spinner = binding.root.findViewById<Spinner>(R.id.lotListButton)
        val lotList = resources.getStringArray(R.array.lotsNumbers)
        val adapter = activity?.let { ArrayAdapter(it,R.layout.spinner_item_layout,lotList) }

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                ViewParent: AdapterView<*>?,
                view: View?,
                position: Int,
                p3: Long,
            ) {
                parkinLot = position
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }


        binding.startDateButton.setOnClickListener{
            pickStartDateTime()
        }

        binding.endDateButton.setOnClickListener{
            pickEndDateTime()
        }
        binding.authCodeButton.setOnClickListener{
            authCode = binding.authCodeButton.text.toString()
        }




        binding.saveButton.setOnClickListener{
            viewModel!!.addReservation(ReservationDetails("",startDate.timeInMillis,endDate.timeInMillis,authCode,parkinLot ))
        }
        viewModel?.addReservationLiveData?.observe(viewLifecycleOwner){ added ->
            when (added) {
              States.Successful -> {
                    Toast.makeText(activity, " reservation saved", Toast.LENGTH_SHORT).show()
                    binding.root.findNavController().popBackStack()
                }
                States.Occupied -> {
                    Toast.makeText(activity, "date already used", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(activity, "Unexpected error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun pickStartDateTime( ) {
        startDate = Calendar.getInstance()

        val timeListener = TimePickerDialog.OnTimeSetListener { _, hours, minutes ->
            startDate.set(Calendar.MINUTE, minutes)
            startDate.set(Calendar.HOUR, hours)

            val myFormat = " dd-MM-yyyy hh:mm "
            val sdf = SimpleDateFormat(myFormat, Locale.UK)
            (sdf.format(startDate.time))

            binding.startDateButton.hint = (sdf.format(startDate.time))
        }

        val dateListener = DatePickerDialog.OnDateSetListener { _,year,month,day ->
            startDate.set(Calendar.YEAR, year)
            startDate.set(Calendar.MONTH, month)
            startDate.set(Calendar.DAY_OF_MONTH, day)
        }

        TimePickerDialog(
            activity,
            timeListener,
            startDate.get(Calendar.HOUR_OF_DAY),
            startDate.get(Calendar.MINUTE),
            false
        ).show()

        DatePickerDialog(
            requireContext(),
            dateListener,
            startDate.get(Calendar.YEAR),
            startDate.get(Calendar.MONTH),
            startDate.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun pickEndDateTime( ) {
        endDate = Calendar.getInstance()

        val timeListener = TimePickerDialog.OnTimeSetListener { _, hours, minutes ->
            endDate.set(Calendar.MINUTE, minutes)
            endDate.set(Calendar.HOUR, hours)

            val myFormat = " dd-MM-yyyy hh:mm "
            val sdf = SimpleDateFormat(myFormat, Locale.UK)
            (sdf.format(endDate.time))

            binding.endDateButton.hint = (sdf.format(endDate.time))
        }
        val dateListener = DatePickerDialog.OnDateSetListener { _,year,month,day ->
            endDate.set(Calendar.YEAR, year)
            endDate.set(Calendar.MONTH, month)
            endDate.set(Calendar.DAY_OF_MONTH, day)
        }

        TimePickerDialog(
            activity,
            timeListener,
            endDate.get(Calendar.HOUR_OF_DAY),
            endDate.get(Calendar.MINUTE),
            false
        ).show()

        DatePickerDialog(
            requireContext(),
            dateListener,
            endDate.get(Calendar.YEAR),
            endDate.get(Calendar.MONTH),
            endDate.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}