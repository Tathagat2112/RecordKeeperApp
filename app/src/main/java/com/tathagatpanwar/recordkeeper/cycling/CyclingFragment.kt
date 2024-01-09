package com.tathagatpanwar.recordkeeper.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tathagatpanwar.recordkeeper.databinding.FragmentCyclingBinding
import com.tathagatpanwar.recordkeeper.databinding.FragmentRunningBinding

class CyclingFragment : Fragment() {

    private lateinit var binding: FragmentCyclingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCyclingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Calling the listener function
        setupClickListeners()
    }

    // When we press back on keyboard through the emulator, the record would then still be updated
    // because we put the function to display the records in the onResume() function
    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    // Function to launch the edit record screen when the user clicks the respective sections
    private fun setupClickListeners() {
        binding.containerLongestRide.setOnClickListener { launchRunningRecord("Longest Ride") }
        binding.containerBiggestClimb.setOnClickListener { launchRunningRecord("Biggest Climb") }
        binding.containerBestAverageSpeed.setOnClickListener { launchRunningRecord("Best Average Speed") }
    }

    // Function to display the records that have been set on the main screen,
    // and displaying "No record found" when the user hasn't set one
    private fun displayRecords() {
        val cyclingPreferences = requireContext().getSharedPreferences("cycling", Context.MODE_PRIVATE)

        binding.textViewLongestRideValue.text = cyclingPreferences.getString("Longest Ride record", "No record found")
        binding.textViewLongestRideDate.text = cyclingPreferences.getString("Longest Ride date", "No date found")
        binding.textViewBiggestClimbValue.text = cyclingPreferences.getString("Biggest Climb record", "No record found")
        binding.textViewBiggestClimbDate.text = cyclingPreferences.getString("Biggest Climb date", "No date found")
        binding.textViewBestAverageSpeedValue.text = cyclingPreferences.getString("Best Average Speed record", "No record found")
        binding.textViewBestAverageSpeedDate.text = cyclingPreferences.getString("Best Average Speed date", "No date found")
    }

    private fun launchRunningRecord(distance: String) {
        val intent = Intent(context, EditCyclingRecordActivity::class.java)
        intent.putExtra("Distance", distance)
        startActivity(intent)
    }
}