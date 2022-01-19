package com.example.davalebamesame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private lateinit var userEmail: EditText
    private lateinit var userPassword : EditText
    private lateinit var LogInButton :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_LogIn)

        init()

        LogInButton.setOnClickListener {

            val email = userEmail.text.toString()
            val password = userPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Somthing went wrong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, profileActivity:: class.java))
                    finish()
                }
            }



        }



    }

    private fun init () {
        userEmail = findViewById(R.id.userEmail)
        userPassword = findViewById(R.id.userPassword)
        LogInButton = findViewById(R.id.LogInButton)
    }


}