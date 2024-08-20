package com.example.recetario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RememberPasswordActivity : AppCompatActivity() {
    private val validEmails = arrayOf(
        "example1@example.com",
        "example2@example.com",
        "example3@example.com"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remember_password)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val sendButton = findViewById<Button>(R.id.sendButton)

        sendButton.setOnClickListener {
            val email = emailEditText.text.toString()

            when {
                email.isEmpty() -> {
                    Toast.makeText(this, "Por favor ingresa un email", Toast.LENGTH_SHORT).show()
                }
                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show()
                }
                !validEmails.contains(email) -> {
                    Toast.makeText(this, "El email no está registrado", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "Se ha enviado tu contraseña por correo electrónico", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
