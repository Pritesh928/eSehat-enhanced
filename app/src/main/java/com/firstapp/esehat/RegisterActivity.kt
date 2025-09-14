package com.firstapp.esehat

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var phoneEt: EditText
    private lateinit var continueBtn: Button
    private lateinit var googleBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Safe findViewById — returns nullable, so we can check and avoid crashes
        val phoneView = findViewById<EditText?>(R.id.phoneInput)
        val continueView = findViewById<Button?>(R.id.switchButton)
        val googleView = findViewById<Button?>(R.id.googleBtn)

        if (phoneView == null || continueView == null || googleView == null) {
            Toast.makeText(
                this,
                "View IDs not found in activity_register.xml — check phoneEditText / switchButton / googleBtn",
                Toast.LENGTH_LONG
            ).show()
            // stop further setup to avoid crashes
            return
        }

        phoneEt = phoneView
        continueBtn = continueView
        googleBtn = googleView

        // start disabled until valid input
        continueBtn.isEnabled = false

        phoneEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { /* no-op */ }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { /* no-op */ }

            override fun afterTextChanged(s: Editable?) {
                // keep only digits (user might type + or spaces)
                val digits = s?.toString()?.filter { it.isDigit() } ?: ""
                val isValid = digits.length == 10 // change rule if you need different validation
                continueBtn.isEnabled = isValid

                // show small inline error when user typed something invalid
                phoneEt.error = when {
                    digits.isEmpty() -> null
                    !isValid -> "Enter a 10-digit phone number"
                    else -> null
                }
            }
        })

        continueBtn.setOnClickListener {
            // go to OTP screen
            startActivity(Intent(this, OTPActivity::class.java))
        }

        googleBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
