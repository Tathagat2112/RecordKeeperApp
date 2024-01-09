package com.tathagatpanwar.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tathagatpanwar.recordkeeper.databinding.FragmentRunningBinding

class RunningFragment : Fragment() {

    private lateinit var binding: FragmentRunningBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
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
        binding.container5km.setOnClickListener { launchRunningRecord("5km") }
        binding.container10km.setOnClickListener { launchRunningRecord("10km") }
        binding.containerHalfMarathon.setOnClickListener { launchRunningRecord("Half Marathon") }
        binding.containerMarathon.setOnClickListener { launchRunningRecord("Marathon") }
    }

    // Function to display the records that have been set on the main screen,
    // and displaying "No record found" when the user hasn't set one
    private fun displayRecords() {
        val runningPreferences = requireContext().getSharedPreferences("running", Context.MODE_PRIVATE)

        binding.textView5kmValue.text = runningPreferences.getString("5km record", "No record found")
        binding.textView5kmDate.text = runningPreferences.getString("5km date", "No date found")
        binding.textView10kmValue.text = runningPreferences.getString("10km record", "No record found")
        binding.textView10kmDate.text = runningPreferences.getString("10km date", "No date found")
        binding.textViewHalfMarathonValue.text = runningPreferences.getString("Half-Marathon record", "No record found")
        binding.textViewHalfMarathonDate.text = runningPreferences.getString("Half-Marathon date", "No date found")
        binding.textViewMarathonValue.text = runningPreferences.getString("Marathon record", "No record found")
        binding.textViewMarathonDate.text = runningPreferences.getString("Marathon date", "No date found")
    }

    private fun launchRunningRecord(distance: String) {
        val intent = Intent(context, EditRunningRecordActivity::class.java)
        intent.putExtra("Distance", distance)
        startActivity(intent)
    }
}