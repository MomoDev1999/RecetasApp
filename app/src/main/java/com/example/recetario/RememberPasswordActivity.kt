package com.example.recetario

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

class RememberPasswordActivity : ComponentActivity() {

    // Definimos los colores aquí, igual que en las otras pantallas
    private val colorAccent = Color(0xFFE85C0D) // Usando el mismo color acento
    private val colorBackground = Color(0xFFF3EFE8) // Fondo similar a las otras pantallas
    private val colorButton = Color(0xFFE85C0D) // Color del botón igual que en otras pantallas
    private val colorText = Color(0xFFFFFFFF) // Texto blanco para los botones
    private val colorTitle = Color(0xFF821131) // Mismo color para los títulos

    // Correos válidos simulados
    private val validEmails = arrayOf(
        "example1@example.com",
        "example2@example.com",
        "example3@example.com"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RememberPasswordScreen()
        }
    }

    @Composable
    fun RememberPasswordScreen() {
        var email by remember { mutableStateOf("") }

        // Estructura de la pantalla de recuperación de contraseña
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorBackground)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen del logo
            Image(
                painter = painterResource(id = R.drawable.logo),  // Usa tu recurso de logo
                contentDescription = "Logo de la aplicación",
                modifier = Modifier
                    .size(240.dp)
                    .padding(top = 16.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Título de la pantalla
            Text(
                text = "Recuperar Contraseña",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorTitle
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Campo de texto para el correo electrónico
            InputField(
                value = email,
                onValueChange = { email = it },
                label = "Correo Electrónico"
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón para enviar el correo
            Button(
                onClick = {
                    validateEmail(email)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorButton)
            ) {
                Text("Enviar", color = colorText)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para volver a la pantalla principal
            Button(
                onClick = { onBackPressed() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorButton)
            ) {
                Text("Volver", color = colorText)
            }
        }
    }

    @Composable
    fun InputField(
        value: String,
        onValueChange: (String) -> Unit,
        label: String
    ) {
        // Campo de texto alineado con el estilo de las otras vistas
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
        )
    }

    // Validación del correo electrónico
    private fun validateEmail(email: String) {
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
