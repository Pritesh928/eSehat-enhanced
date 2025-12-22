package com.firstapp.esehat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginbutton : Button = findViewById<Button>(R.id.login_button)
        loginbutton.setOnClickListener {
            //intent init
            val intent = Intent(this , MainActivity::class.java)
            //intent start
            startActivity(intent)
        }

        val signupRedirect : TextView = findViewById<TextView>(R.id.signupRedirectText)

        signupRedirect.setOnClickListener {
            //intent init
            val intent1 = Intent(this, RegisterActivity::class.java)
            //start activity
            startActivity(intent1)
        }
    }
}
