package com.example.bumiajimobileview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var loginBtn: Button

    private val validUsername = "admin"
    private val validPassword = "password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginBtn = findViewById(R.id.login_btn)

        loginBtn.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            Log.i("Test Credential", "Username: $username and Password: $password")

            if (username == validUsername && password == validPassword) {
                val intent = Intent(this, MachineList::class.java)
                startActivity(intent)
            } else {
                // Handle incorrect credentials
                Log.i("Login", "Invalid username or password")
            }
        }
    }
}
