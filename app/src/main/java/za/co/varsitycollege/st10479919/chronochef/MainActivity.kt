package za.co.varsitycollege.st10479919.chronochef

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Locale
import za.co.varsitycollege.st10479919.chronochef.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.testingView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userInputTime = findViewById<EditText>(R.id.userInputTime)
        val userReference = findViewById<TextView>(R.id.userReference)

        userInputTime.setOnClickListener {
            val userTime = userInputTime.text.toString()
            val userTimeMinutes = timeToMinutes(userTime)


            // Checks is input valid
            if (isValidTime(userTime)) {

                when (userTimeMinutes) {
                    in timeToMinutes("6:00")..timeToMinutes("11:59") -> {
                        userReference.text = "Morning"
                    }
                    in timeToMinutes("12:00")..timeToMinutes("13:00") -> {
                        userReference.text = "Afternoon"
                    }
                    in timeToMinutes("13:01")..timeToMinutes("15:00") -> {
                        userReference.text = "Afternoon-Snack"
                    }
                    in timeToMinutes("15:01")..timeToMinutes("15:59") ->{
                        userReference.text = "Mid-Afternoon"
                    }
                    in timeToMinutes("16:00")..timeToMinutes("20:00") -> {
                        userReference.text = "Evening"
                    }
                    in timeToMinutes("20:01")..timeToMinutes("22:00") -> {
                        userReference.text = "Mid-NightSnack"
                    }
                    else -> {
                        userReference.text = "Night"
                    }

                }
                val toFoodActivity = Intent(this, foodActivity::class.java)
                // Takes user input to place in other activity
                toFoodActivity.putExtra("userInput",userReference.text.toString())
                // Moves to FoodActivity
                startActivity(toFoodActivity)

                // Clear text to make screen empty
                userInputTime.text.clear()
                userReference.visibility = View.GONE
            }
            // Notify user of format
            else {
                Toast.makeText(this, "Please enter a valid time: HH/mm" + " 24 hour format", Toast.LENGTH_SHORT).show()
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