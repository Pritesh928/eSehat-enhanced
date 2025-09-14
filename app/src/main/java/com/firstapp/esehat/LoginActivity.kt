package com.firstapp.esehat

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var loginBtn: Button
    private lateinit var registerBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Initialize views
        emailEt = findViewById(R.id.emailEditText)
        passwordEt = findViewById(R.id.passwordEditText)
        loginBtn = findViewById(R.id.btnLogin)
        registerBtn = findViewById(R.id.btnRegister)

        // Initially disable login button
        loginBtn.isEnabled = false

        // One TextWatcher that listens to both fields
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val email = emailEt.text.toString().trim()
                val pass = passwordEt.text.toString()

                val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
                val isPassValid = pass.length >= 6

                // Set errors if invalid
                if (email.isNotEmpty() && !isEmailValid) {
                    emailEt.error = "Invalid email format"
                } else {
                    emailEt.error = null
                }

                if (pass.isNotEmpty() && !isPassValid) {
                    passwordEt.error = "Password must be ≥ 6 chars"
                } else {
                    passwordEt.error = null
                }

                // Enable login button only when both valid
                loginBtn.isEnabled = isEmailValid && isPassValid
            }
        }

        emailEt.addTextChangedListener(watcher)
        passwordEt.addTextChangedListener(watcher)

        // Login button action
        loginBtn.setOnClickListener {
            val intent9 = Intent(this, MainActivity::class.java)
            startActivity(intent9)
            Toast.makeText(this, "Login Successful!!", Toast.LENGTH_LONG).show()
        }

        // Register button action
        registerBtn.setOnClickListener {
            val intent10 = Intent(this, RegisterActivity::class.java)
            startActivity(intent10)
        }
    }
}
