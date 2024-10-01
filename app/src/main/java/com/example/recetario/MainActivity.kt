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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.util.PatternsCompat
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import com.google.firebase.auth.FirebaseAuth


class MainActivity : ComponentActivity() {

    // Definimos los colores aquí
    private val black = Color(0xFF000000)
    private val white = Color(0xFFFFFFFF)
    private val colorAccent = Color(0xFFE85C0D)
    private val colorBackground = Color(0xFFF3EFE8)
    private val colorButton = Color(0xFFE85C0D)
    private val colorText = Color(0xFFFFFFFF)
    private val colorTitle = Color(0xFF821131)

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContent {
            LoginScreen()
        }
    }

    @Composable
    fun LoginScreen() {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        // Estructura de la pantalla de inicio de sesión
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorBackground)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo de la aplicación",
                modifier = Modifier
                    .size(240.dp)
                    .padding(top = 16.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre de la aplicación
            Text(
                text = "RecetarioApp",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorTitle
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Campo de texto para el correo electrónico
            InputField(
                value = email,
                onValueChange = { email = it },
                label = "Correo Electrónico",
                isEmail = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para la contraseña
            InputField(
                value = password,
                onValueChange = { password = it },
                label = "Contraseña",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de inicio de sesión
            Button(
                onClick = {
                    loginUser(email, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorButton)
            ) {
                Text("Iniciar Sesión", color = colorText)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Texto de registro con clic para ir a RegisterActivity
            ClickableText(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = colorAccent, fontWeight = FontWeight.Bold)) {
                        append("¿No tienes una cuenta? Regístrate")
                    }
                },
                onClick = {
                    // Navegar a RegisterActivity
                    val intent = Intent(this@MainActivity, RegisterActivity::class.java)
                    startActivity(intent)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Texto de recuperar contraseña
            ClickableText(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = colorAccent, fontWeight = FontWeight.Bold)) {
                        append("¿Olvidaste tu contraseña?")
                    }
                },
                onClick = {
                    // Navegar a RememberPasswordActivity
                    val intent = Intent(this@MainActivity, RememberPasswordActivity::class.java)
                    startActivity(intent)
                }
            )
        }
    }

    @Composable
    fun InputField(
        value: String,
        onValueChange: (String) -> Unit,
        label: String,
        isPassword: Boolean = false,
        isEmail: Boolean = false
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
    }

    private fun loginUser(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Correo electrónico inválido", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Fallo en la autenticación: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
