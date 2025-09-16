package com.firstapp.esehat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var logoutBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        logoutBtn = findViewById(R.id.logoutBtn)


        logoutBtn.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
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
