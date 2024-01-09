package com.tathagatpanwar.recordkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.tathagatpanwar.recordkeeper.cycling.CyclingFragment
import com.tathagatpanwar.recordkeeper.databinding.ActivityMainBinding
import com.tathagatpanwar.recordkeeper.running.RunningFragment

class MainActivity : AppCompatActivity(), OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Showing the running screen when the app is opened
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }

        // Switching between different navigation buttons
        binding.bottomNav.setOnItemSelectedListener(this)
    }

    // Function to create an options menu (three dots side menu)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    // Function to respond to menu option clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.reset_running -> {
            Toast.makeText(this, "Reset the running records", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.reset_cycling -> {
            Toast.makeText(this, "Reset the cycling records", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.reset_all -> {
            Toast.makeText(this, "Reset all the records", Toast.LENGTH_SHORT).show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    // Function to replace the screen to the Running screen
    private fun onRunningClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }
        return true
    }

    // Function to replace the screen to the Cycling screen
    private fun onCyclingClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CyclingFragment())
        }
        return true
    }

    // Function to respond to the clicks when the user wants to switch between different screens (Running and Cycling)
    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.nav_running -> onRunningClicked()
        R.id.nav_cycling -> onCyclingClicked()
        else -> false
    }
}