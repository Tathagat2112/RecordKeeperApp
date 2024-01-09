package com.tathagatpanwar.recordkeeper.cycling

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.tathagatpanwar.recordkeeper.databinding.ActivityEditCyclingRecordBinding
import com.tathagatpanwar.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditCyclingRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditCyclingRecordBinding
    private val cyclingPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            "cycling",
            Context.MODE_PRIVATE
        )
    }
    private val distance: String? by lazy { intent.getStringExtra("Distance") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding the activity to the main activity
        binding = ActivityEditCyclingRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting the title of the toolbar
        title = "$distance Record"

        // Calling the function to display the previously set record when we are trying to edit it
        displayRecord()

        // Creating a button click listener to save the record and date
        // and close the edit screen when save button is clicked
        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }

        // Creating a button click listener to clear the previous record and date
        // and close the edit screen when clear button is clicked
        binding.buttonDelete.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    // Function to display the previous record while editing
    private fun displayRecord() {
        binding.editTextRecord.setText(cyclingPreferences.getString("$distance record", null))
        binding.editTextDate.setText(cyclingPreferences.getString("$distance date", null))
    }

    // Function to save the record to the app's memory
    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

        cyclingPreferences.edit {
            putString("$distance record", record)
            putString("$distance date", date)
        }
    }

    // Function to clear the previous set record
    private fun clearRecord() {
        cyclingPreferences.edit{
            remove("$distance record")
            remove("$distance date")
        }
    }
}