package com.firstapp.esehat

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class OTPActivity : AppCompatActivity() {

    private var generatedOtp: String? = null  // store OTP after generation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_otpactivity)

        val verifyButton: Button = findViewById(R.id.verifyButton)
        val otpText: EditText = findViewById(R.id.otpEditText)
        val getOtpButton: Button = findViewById(R.id.getotp)

        // Initially disable verify button
        verifyButton.isEnabled = false

        // 🔹 TextWatcher for OTP field
        otpText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val otp = s.toString()
                verifyButton.isEnabled = otp.length == 4
            }
        })

        // On Verify Button click
        verifyButton.setOnClickListener {
            val otp = otpText.text.toString()
            if (otp == generatedOtp) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Invalid OTP ❌", Toast.LENGTH_SHORT).show()
            }
        }

        // On Get OTP Button click
        getOtpButton.setOnClickListener {
            // generate random 4-digit OTP (1000–9999)
            generatedOtp = Random.nextInt(1000, 9999).toString()
            Toast.makeText(this, "Your OTP is $generatedOtp", Toast.LENGTH_LONG).show()
        }
    }
}
