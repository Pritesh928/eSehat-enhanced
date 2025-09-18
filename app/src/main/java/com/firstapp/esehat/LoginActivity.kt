package com.firstapp.esehat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {
    
    private lateinit var loginUsername: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var signupRedirectText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginUsername = findViewById(R.id.login_username)
        loginPassword = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.login_button)
        signupRedirectText = findViewById(R.id.signupRedirectText)

        loginButton.setOnClickListener {
            if (!validateUsername() or !validatePassword()) {
                // do nothing if validation fails
            } else {
                checkUser()
            }
        }

        signupRedirectText.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateUsername(): Boolean {
        val value = loginUsername.text.toString()
        return if (value.isEmpty()) {
            loginUsername.error = "Username cannot be empty"
            false
        } else {
            loginUsername.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val value = loginPassword.text.toString()
        return if (value.isEmpty()) {
            loginPassword.error = "Password cannot be empty"
            false
        } else {
            loginPassword.error = null
            true
        }
    }

    private fun checkUser() {
        val userUsername = loginUsername.text.toString().trim()
        val userPassword = loginPassword.text.toString().trim()

        val reference: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
        val checkUserDatabase: Query = reference.orderByChild("username").equalTo(userUsername)

        checkUserDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    loginUsername.error = null
                    val passwordFromDB = snapshot.child(userUsername).child("password").getValue(String::class.java)

                    if (passwordFromDB == userPassword) {
                        loginUsername.error = null

                        val nameFromDB = snapshot.child(userUsername).child("name").getValue(String::class.java)
                        val emailFromDB = snapshot.child(userUsername).child("email").getValue(String::class.java)
                        val usernameFromDB = snapshot.child(userUsername).child("username").getValue(String::class.java)

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.putExtra("name", nameFromDB)
                        intent.putExtra("email", emailFromDB)
                        intent.putExtra("username", usernameFromDB)
                        intent.putExtra("password", passwordFromDB)

                        startActivity(intent)
                    } else {
                        loginPassword.error = "Invalid Credentials"
                        loginPassword.requestFocus()
                    }
                } else {
                    loginUsername.error = "User does not exist"
                    loginUsername.requestFocus()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}
