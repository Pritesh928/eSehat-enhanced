package com.firstapp.esehat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


class Profilepage : AppCompatActivity() {

    private lateinit var profileName: TextView
    private lateinit var profileEmail: TextView
    private lateinit var profileUsername: TextView
    private lateinit var profilePassword: TextView
    private lateinit var titleName: TextView
    private lateinit var titleUsername: TextView
    private lateinit var editProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilepage)
        val homeButton : ImageButton = findViewById<ImageButton>(R.id.homebtn)
        homeButton.setOnClickListener {
            val intent3 = Intent(this, MainActivity::class.java)
            startActivity(intent3)
        }
        val healthButton : ImageButton = findViewById<ImageButton>(R.id.healthtrackbtn)
        healthButton.setOnClickListener {
            val intent4 = Intent(this, HealthTracker::class.java)
            startActivity(intent4)
        }
        val videoconsultButton : ImageButton = findViewById<ImageButton>(R.id.videoconsult)
        videoconsultButton.setOnClickListener {
            val intent5 = Intent(this, VideoConsult::class.java)
            startActivity(intent5)
        }
        val AIButton : ImageButton = findViewById<ImageButton>(R.id.baymaxAI)
        AIButton.setOnClickListener {
            val intent6 = Intent(this, BaymaxAI::class.java)
            startActivity(intent6)
        }
        val profileButton : ImageButton = findViewById<ImageButton>(R.id.profileBtn)
        profileButton.setOnClickListener {
            val intent7 = Intent(this, Profilepage::class.java)
            startActivity(intent7)
        }

        profileName = findViewById(R.id.profileName)
        profileEmail = findViewById(R.id.profileEmail)
        profileUsername = findViewById(R.id.profileUsername)
        profilePassword = findViewById(R.id.profilePassword)
        titleName = findViewById(R.id.titleName)
        titleUsername = findViewById(R.id.titleUsername)
        editProfile = findViewById(R.id.editButton)

        showAllUserData()

        editProfile.setOnClickListener {
            passUserData()
        }
    }

    private fun showAllUserData() {
        val intent = intent
        val nameUser = intent.getStringExtra("name").orEmpty()
        val emailUser = intent.getStringExtra("email").orEmpty()
        val usernameUser = intent.getStringExtra("username").orEmpty()
        val passwordUser = intent.getStringExtra("password").orEmpty()

        titleName.text = nameUser
        titleUsername.text = usernameUser
        profileName.text = nameUser
        profileEmail.text = emailUser
        profileUsername.text = usernameUser
        profilePassword.text = passwordUser
    }

    private fun passUserData() {
        val userUsername = profileUsername.text.toString().trim()
        val reference: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
        val checkUserDatabase: Query = reference.orderByChild("username").equalTo(userUsername)

        checkUserDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val nameFromDB = snapshot.child(userUsername).child("name").getValue(String::class.java)
                    val emailFromDB = snapshot.child(userUsername).child("email").getValue(String::class.java)
                    val usernameFromDB = snapshot.child(userUsername).child("username").getValue(String::class.java)
                    val passwordFromDB = snapshot.child(userUsername).child("password").getValue(String::class.java)

                    val intent = Intent(this@Profilepage, OTPActivity::class.java)
                    intent.putExtra("name", nameFromDB)
                    intent.putExtra("email", emailFromDB)
                    intent.putExtra("username", usernameFromDB)
                    intent.putExtra("password", passwordFromDB)

                    startActivity(intent)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle DB error if needed
            }
        })
    }
}
