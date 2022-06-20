package com.example.my_parking_app.Views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entities.LotReservation
import com.example.domain.entities.ReservationDetails
import com.example.my_parking_app.R
import com.example.my_parking_app.adapters.LotRDetAdapter
import com.example.my_parking_app.databinding.LotDetailsFragmentBinding
import com.example.my_parking_app.tools.DeleteDialogCallBack
import com.example.my_parking_app.viewModels.reservationModel.ReservationViewModel
import com.example.my_parking_app.viewModels.reservationModel.ReservationViewModelProvider

class ReservationFragment : Fragment(R.layout.lot_details_fragment), DeleteDialogCallBack {

    private lateinit var binding: LotDetailsFragmentBinding
    private val parkingId: String = "-N0TU3sdjmm3RuXY7jLS"

    private lateinit var onParkingLot: LotReservation

    private val viewModel by lazy { ReservationViewModelProvider(activity).get(ReservationViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LotDetailsFragmentBinding.bind(view)
        binding.resRecycler.layoutManager = LinearLayoutManager(activity)

        arguments?.let { onParkingLot = it.getSerializable("lot") as LotReservation }
        binding.parkingLotNumber.text = onParkingLot.id.toString()
        initRecyclerView(onParkingLot.reservations)

        binding.imgButton.setOnClickListener { onBackButtonSelected() }

        binding.floatingAddButton.setOnClickListener { binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_addReservationFragment) }
    }

    private fun initRecyclerView(resList: List<ReservationDetails>) {
        binding.resRecycler.adapter = LotRDetAdapter(resList) { reservation -> onButtonDeleteSelected(reservation) }
    }

    private fun onButtonDeleteSelected(res: ReservationDetails) {
        val newFragment: DialogFragment = DeleteDialogFragment.newInstance(this, res)
        newFragment.show(parentFragmentManager, "dialog")
    }

    private fun onBackButtonSelected() {
        binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_parkingLotsFragment) // switching screen to parkingLotsFragment
    }

    override fun onDeleteClicked(authCode: String, res: ReservationDetails) {
        viewModel.deleteReservation(parkingId, authCode, res)

        viewModel.success.observe(viewLifecycleOwner) { deleted ->
            if (deleted) {
                binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_parkingLotsFragment)
                Toast.makeText(activity, "reservation deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Incorrect authorization code, try again", Toast.LENGTH_SHORT).show()
            }
        }
    }
}