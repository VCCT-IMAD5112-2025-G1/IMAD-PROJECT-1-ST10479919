package com.example.chronochef

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userInputTime = findViewById<EditText>(R.id.userInputTime)
        val testInput = findViewById<TextView>(R.id.testingView)

        val morningStartTime = "6:00"
        val morningEndTime = "11:59"
        val afternoonStartTime = "12:00"
        val afternoonEndTime = "17:59"
        val eveningStartTime = "18:00"
        val eveningEndTime = "21:59"

        // Convert string to int and use time as minutes
        val morningStartTimeMinuets = timeToMinutes(morningStartTime)
        val morningEndTimeMinuets = timeToMinutes(morningEndTime)
        val afternoonStartTimeMinutes = timeToMinutes(afternoonStartTime)
        val afternoonEndTimeMinutes = timeToMinutes(afternoonEndTime)
        val eveningStartTimeMinutes = timeToMinutes(eveningStartTime)
        val eveningEndTimeMinutes = timeToMinutes(eveningEndTime)



        userInputTime.setOnClickListener {
            val userTime = userInputTime.text.toString()
            val userTimeMinutes = timeToMinutes(userTime)
            val tofoodActivity = Intent(this, foodActivity::class.java)

            // Checks is input valid
            if (isValidTime(userTime)) {
                // TODO: Change the testInput to go to own screen for food options
                // TODO: Make input clear after switching screen
                when (userTimeMinutes) {
                    in morningStartTimeMinuets..morningEndTimeMinuets -> {
                        testInput.text = "Morning"
                        intent.putExtra("Morning",userInputTime.text.toString())
                    }
                    in afternoonStartTimeMinutes..afternoonEndTimeMinutes -> {
                        testInput.text = "Afternoon"
                        intent.putExtra("Afternoon",userInputTime.text.toString())
                    }
                    in eveningStartTimeMinutes..eveningEndTimeMinutes -> {
                        testInput.text = "Evening"
                        intent.putExtra("Evening",userInputTime.text.toString())
                    }
                    else -> {
                        testInput.text = "Night"
                        intent.putExtra("Night",userInputTime.text.toString())
                    }
                }
                // Moves to foodActivity
                startActivity(tofoodActivity)

            }
            // Notify user of format
            else {
                Toast.makeText(this, "Please enter a valid time: HH/mm", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

// Function to check if user input actually has a time value
private fun isValidTime(Time: String): Boolean {
    return try {
        // This sets the valid format for the time
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        // Ensures that the rule is strict
        timeFormat.isLenient = false
        timeFormat.parse(Time)
        true
    }
    catch (_: Exception){
        false
    }
}
// Converts user input to minuets for ranges and conditionals
private fun timeToMinutes(Time: String): Int {
    // Lets the function wait for user input, else the app crashes from splitting a null
    if (Time.isBlank()) {
        return 0
    }
    // Remove the :, and place the parts into an array
    val splitParts = Time.split(":")
    // Prevents crash due to no ":"
    if (splitParts.size != 2) {
        return 0
    }
    else {
    // Holds the hours in 0th element
    val splitHours = splitParts[0].toInt()
    // Holds the Minuets in 1st element
    val splitMinutes = splitParts[1].toInt()
    // Return the time in Int format
    return splitHours * 60 + splitMinutes
    }
}