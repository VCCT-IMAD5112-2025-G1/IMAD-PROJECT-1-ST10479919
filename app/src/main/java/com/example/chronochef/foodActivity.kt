package com.example.chronochef

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class foodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_food)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.testingView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Get user input from other Activity
        val bundle: Bundle? = intent.extras
        val userInput: String? = bundle?.getString("userInput")

        val menuList = findViewById<TextView>(R.id.testKey)
        val cardHeader = findViewById<TextView>(R.id.cardHeader)

        // Change what happens based on what user input
        // TODO: Add food for user
        when (userInput) {
            "Morning" -> {
                cardHeader.text = "Breakfast"
                menuList.text = "Eggs and bacon"
            }
            "Afternoon" -> {
                cardHeader.text = "Lunch"
                menuList.text = "Tea and Biscuits"
            }
            "Evening" -> {
                cardHeader.text = "Dinner"
                menuList.text = "Evening"
            }
            else -> {
                cardHeader.text = "Night snack"
                menuList.text = "Night"
            }
        }

    }
}
