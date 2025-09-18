package com.firstapp.esehat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {

    private lateinit var cardParacetamol: View
    private lateinit var cardMetformin: View
    private lateinit var circleViews: List<View>
    private lateinit var tvProgressCount: TextView
    private lateinit var tvMedicationsLeft: TextView

    private var takenMedications = 0
    private var currentCircleIndex = 0
    private var totalMedications = 0   // dynamic instead of fixed 8
    private var todayMedications = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupNavigation()

        cardParacetamol = findViewById(R.id.card_paracetamol)
        cardMetformin = findViewById(R.id.card_metformin)

        tvProgressCount = findViewById(R.id.tv_progress_count)
        tvMedicationsLeft = findViewById(R.id.tv_medications_left)

        val btnParacetamolSkipped: Button = findViewById(R.id.btn_paracetamol_skipped)
        val btnParacetamolTaken: Button = findViewById(R.id.btn_paracetamol_taken)
        val btnMetforminSkipped: Button = findViewById(R.id.btn_metformin_skipped)
        val btnMetforminTaken: Button = findViewById(R.id.btn_metformin_taken)

        circleViews = listOf(
            findViewById(R.id.circle1),
            findViewById(R.id.circle2),
            findViewById(R.id.circle3),
            findViewById(R.id.circle4),
            findViewById(R.id.circle5),
            findViewById(R.id.circle6),
            findViewById(R.id.circle7),
            findViewById(R.id.circle8)
        )

        // count how many medication cards are visible
        totalMedications = listOf(cardParacetamol, cardMetformin).count { it.visibility == View.VISIBLE }
        todayMedications = totalMedications

        // Initial UI
        updateUI()

        // Button actions
        btnParacetamolSkipped.setOnClickListener {
            cardParacetamol.visibility = View.GONE
            updateProgressCircleg(taken = false, skipped = true)
        }
        btnParacetamolTaken.setOnClickListener {
            cardParacetamol.visibility = View.GONE
            updateProgressCircleg(taken = true)
        }

        btnMetforminSkipped.setOnClickListener {
            cardMetformin.visibility = View.GONE
            updateProgressCircleg(taken = false, skipped = true)
        }
        btnMetforminTaken.setOnClickListener {
            cardMetformin.visibility = View.GONE
            updateProgressCircleg(taken = true)
        }

        // Navigation setup
        setupNavigation()
    }
    private fun updateProgressCircle(taken: Boolean, skipped: Boolean = false) {
        if (taken) {
            takenMedications++
        }
        if (skipped) {
            takenMedications++
        }
        updateUI()

    }

    private fun updateProgressCircleg(taken: Boolean, skipped: Boolean = false) {
        if (currentCircleIndex < circleViews.size) {
            val circle = circleViews[currentCircleIndex]

            when {
                taken -> {
                    circle.setBackgroundResource(R.drawable.circle_completed) // green
                    takenMedications++
                }
                skipped -> {
                    circle.setBackgroundResource(R.drawable.skipped_circle) // red
                    // don't increment takenMedications here
                }
                else -> {
                    circle.setBackgroundResource(R.drawable.circle_incomplete) // grey
                }
            }

            todayMedications--
            currentCircleIndex++
            updateUI()
        }
    }


    private fun updateUI() {
        // Update progress fraction dynamically
        tvProgressCount.text = "$takenMedications/$totalMedications"

        // Update medications left message
        tvMedicationsLeft.text = if (todayMedications > 0) {
            "You have $todayMedications medications left for today."
        } else {
            "All medications completed for today!"
        }
    }

    private fun setupNavigation() {
        val videoconsult_upButton: Button = findViewById(R.id.videoconsult_up)
        videoconsult_upButton.setOnClickListener {
            val intent01 = Intent(this, VideoConsult::class.java)
            startActivity(intent01)
        }

        val BaymaxAI_upButton: Button = findViewById(R.id.baymaxAI_up)
        BaymaxAI_upButton.setOnClickListener {
            val intent02 = Intent(this, BaymaxAI::class.java)
            startActivity(intent02)
        }

        val homeButton: ImageButton = findViewById(R.id.homebtn)
        homeButton.setOnClickListener {
            val intent3 = Intent(this, MainActivity::class.java)
            startActivity(intent3)
        }

        val healthButton: ImageButton = findViewById(R.id.healthtrackbtn)
        healthButton.setOnClickListener {
            val intent4 = Intent(this, HealthTracker::class.java)
            startActivity(intent4)
        }

        val videoconsultButton: ImageButton = findViewById(R.id.videoconsult)
        videoconsultButton.setOnClickListener {
            val intent5 = Intent(this, VideoConsult::class.java)
            startActivity(intent5)
        }

        val AIButton: ImageButton = findViewById(R.id.baymaxAI)
        AIButton.setOnClickListener {
            val intent6 = Intent(this, BaymaxAI::class.java)
            startActivity(intent6)
        }

        val profileButton: ImageButton = findViewById(R.id.profileBtn)
        profileButton.setOnClickListener {
            val intent7 = Intent(this, Profilepage::class.java)
            startActivity(intent7)
        }
    }
}
