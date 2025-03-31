package za.co.varsitycollege.st10479919.chronochef

import android.annotation.SuppressLint
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
    @SuppressLint("SetTextI18n")
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
        when (userInput) {
            "Morning" -> {
                cardHeader.text = "Breakfast\n"
                menuList.text = "Eggs and bacon\n" +
                        "Muesli with yogurt\n" +
                        "Fruit salad\n" +
                        "Chicken & Broccoli\n"
            }
            "Afternoon" -> {
                cardHeader.text = "Lunch"
                menuList.text = "Ramen\n" +
                        "Chicken Grilled Cheese\n" +
                        "Chicken Fried Rice\n"
            }
            "Afternoon-Snack" -> {
                cardHeader.text = "Afternoon Snack"
                menuList.text = "Tea and Biscuits\n" +
                        "Chips\n" +
                        "Bagels\n" +
                        "Apples\n"
            }
            "Evening" -> {
                cardHeader.text = "Dinner"
                menuList.text = "Garlic steak\n" +
                        "Wraps\n" +
                        "Butter chicken\n" +
                        "Chicken Caesar Pasta Salad\n"
            }
            "Mid-NightSnack" -> {
                cardHeader.text = "Night Snack"
                menuList.text = "yogurt with berries\n" +
                        "cheese and crackers\n" +
                        "Nuts\n"
            }
            else -> {
                cardHeader.text = "Night snack"
                menuList.text = "Protein smoothie\n" +
                        "Hot cereal\n" +
                        "Crackers and cheese\n" +
                        "Yogurt\n"
            }
        }

        // Button to return to MainActivity

        returnButton.setOnClickListener {
            val toMainActivity = Intent(this,MainActivity::class.java)
            startActivity(toMainActivity)
        }


    }
}
