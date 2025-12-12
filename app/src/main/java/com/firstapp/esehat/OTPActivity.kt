package com.firstapp.esehat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class OTPActivity: AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editUsername: EditText
    private lateinit var editPassword: EditText
    private lateinit var saveButton: Button

    private lateinit var nameUser: String
    private lateinit var emailUser: String
    private lateinit var usernameUser: String
    private lateinit var passwordUser: String

    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpactivity)
        
        val BackButton : Button = findViewById<Button>(R.id.saveButton)
        BackButton.setOnClickListener {
            val intent66 = Intent(this, VideoConsult::class.java)
            startActivity(intent66)
        }

        reference = FirebaseDatabase.getInstance().getReference("users")

        editName = findViewById(R.id.editName)
        editEmail = findViewById(R.id.editEmail)
        editUsername = findViewById(R.id.editUsername)
        editPassword = findViewById(R.id.editPassword)
        saveButton = findViewById(R.id.saveButton)

        showData()

        saveButton.setOnClickListener {
            if (isNameChanged() || isPasswordChanged() || isEmailChanged()) {
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No Changes Found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isNameChanged(): Boolean {
        return if (nameUser != editName.text.toString()) {
            reference.child(usernameUser).child("name").setValue(editName.text.toString())
            nameUser = editName.text.toString()
            true
        } else {
            false
        }
    }

    private fun isEmailChanged(): Boolean {
        return if (emailUser != editEmail.text.toString()) {
            reference.child(usernameUser).child("email").setValue(editEmail.text.toString())
            emailUser = editEmail.text.toString()
            true
        } else {
            false
        }
    }
    private fun isPasswordChanged(): Boolean {
        return if (passwordUser != editPassword.text.toString()) {
            reference.child(usernameUser).child("password").setValue(editPassword.text.toString())
            passwordUser = editPassword.text.toString()
            true
        } else {
            false
        }
    }

    private fun showData() {
        val intent: Intent = intent

        nameUser = intent.getStringExtra("name").orEmpty()
        emailUser = intent.getStringExtra("email").orEmpty()
        usernameUser = intent.getStringExtra("username").orEmpty()
        passwordUser = intent.getStringExtra("password").orEmpty()

        editName.setText(nameUser)
        editEmail.setText(emailUser)
        editUsername.setText(usernameUser)
        editPassword.setText(passwordUser)
    }
}
