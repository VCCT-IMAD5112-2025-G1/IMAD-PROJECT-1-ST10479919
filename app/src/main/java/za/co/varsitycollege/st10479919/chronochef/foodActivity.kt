package za.co.varsitycollege.st10479919.chronochef

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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
        val returnButton = findViewById<Button>(R.id.returnButton)

        // Change what happens based on what user input
        // TODO: Add food for user
        when (userInput) {
            "Morning" -> {
                cardHeader.text = "Breakfast"
                menuList.text = "Eggs and bacon" +
                        "Mussi with yogart" +
                        "Fruit salad" +
                        "Chicken & Broccoli"
            }
            "Afternoon" -> {
                cardHeader.text = "Lunch"
                menuList.text = "Tea and Biscuits" +
                        "Ramen" +
                        "Chicken Grilled Cheese" +
                        "Chicken Fried Rice"
            }
            "Evening" -> {
                cardHeader.text = "Dinner"
                menuList.text = "Garlic steak" +
                        "Wraps" +
                        "Butter chicken" +
                        "Chicken Caesar Pasta Salad"
            }
            else -> {
                cardHeader.text = "Night snack"
                menuList.text = "Protein smoothie" +
                        "Hot cereal" +
                        "Crackers and cheese" +
                        "Yogurt"
            }
        }

        // Button to return to MainActivity

        returnButton.setOnClickListener {
            val toMainActivity = Intent(this,MainActivity::class.java)
            startActivity(toMainActivity)
        }


    }
}
