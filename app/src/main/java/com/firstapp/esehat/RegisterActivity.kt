package com.firstapp.esehat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val signup_button : Button = findViewById<Button>(R.id.signup_button)

        signup_button.setOnClickListener {
            //Intent init
            val intent = Intent(this , OTPActivity::class.java)
            //start intent
            startActivity(intent)
        }
        val loginRedirect : TextView = findViewById<TextView>(R.id.loginRedirectText)
        loginRedirect.setOnClickListener {
            val intent1 = Intent(this, LoginActivity::class.java)
            startActivity(intent1)
        }

    }
}
