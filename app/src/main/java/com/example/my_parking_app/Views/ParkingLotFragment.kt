package com.example.my_parking_app.Views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entities.LotReservation
import com.example.my_parking_app.R
import com.example.my_parking_app.adapters.CustomAdapter
import com.example.my_parking_app.databinding.ParkingLotFragmentBinding
import com.example.my_parking_app.viewModels.LotViewModel
import com.example.my_parking_app.viewModels.LotViewModelProvider

class LotFragment : Fragment(R.layout.parking_lot_fragment) {

    private lateinit var binding: ParkingLotFragmentBinding
    private lateinit var lotReservationList: List<LotReservation>
    private val parkingId: String = "-N0TU3sdjmm3RuXY7jLS"

    private val viewModel by lazy { LotViewModelProvider(activity).get(LotViewModel::class.java) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ParkingLotFragmentBinding.bind(view)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        lotReservationList = listOf()
        initiateLiveData()
        viewModel.lotReservationLiveData.observe(viewLifecycleOwner) {
            lotReservationList = it
            updateProgressBar(viewModel.availabilityHandler(lotReservationList))
            updateRecyclerView(lotReservationList)
        }

        binding.floatingAddButton.setOnClickListener { binding.root.findNavController().navigate(R.id.action_parkingLotsFragment_to_addReservationFragment) }
    }

    private fun updateRecyclerView(newList: List<LotReservation>) {
        binding.recyclerView.adapter = CustomAdapter(newList) { parkingLot -> onParkingSpotSelected(parkingLot) }
    }

    private fun updateProgressBar(parkingAvailability: Int) {
        binding.progressBar.max = lotReservationList.size
        binding.progressBar.progress = lotReservationList.size - parkingAvailability
        binding.freeLotsQuantity.text = parkingAvailability.toString()
        binding.busyLotsQuantity.text = (lotReservationList.size - parkingAvailability).toString()
    }

    private fun onParkingSpotSelected(parkingLot: LotReservation) {
        val navController = binding.root.findNavController()
        val bundle = Bundle()
        bundle.putSerializable("lot", parkingLot)
        navController.navigate(R.id.action_parkingLotFragment_to_lotDetails_fragment, bundle)
    }
    private fun initiateLiveData() {
        viewModel.lotReservationCreation(parkingId, false)
        viewModel.lotReservationCreation(parkingId, true)
    }
}