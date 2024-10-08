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
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    // Definimos los colores aquí
    private val black = Color(0xFF000000)
    private val white = Color(0xFFFFFFFF)
    private val colorAccent = Color(0xFFE85C0D)
    private val colorBackground = Color(0xFFF3EFE8)
    private val colorButton = Color(0xFFE85C0D)
    private val colorText = Color(0xFFFFFFFF)
    private val colorTitle = Color(0xFF821131)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializamos FirebaseAuth
        auth = FirebaseAuth.getInstance()

        setContent {
            RegisterScreen()
        }
    }

    @Composable
    fun RegisterScreen() {
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }

        // Estructura de la pantalla de registro
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorBackground)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo),  // Usa tu recurso de logo
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

            // Campos de texto
            InputField(
                value = username,
                onValueChange = { username = it },
                label = "Nombre de Usuario"
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                value = email,
                onValueChange = { email = it },
                label = "Correo Electrónico",
                isEmail = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                value = password,
                onValueChange = { password = it },
                label = "Contraseña",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Confirmar Contraseña",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de registro
            Button(
                onClick = {
                    if (validateRegistration(username, email, password, confirmPassword)) {
                        registerUser(username, email, password)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorButton)
            ) {
                Text("Registrar", color = colorText)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Texto de login
            ClickableText(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = colorAccent, fontWeight = FontWeight.Bold)) {
                        append("¿Ya tienes una cuenta? Inicia sesión")
                    }
                },
                onClick = {
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
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
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
        )
    }

    // Función para validar el formulario
    private fun validateRegistration(username: String, email: String, password: String, confirmPassword: String): Boolean {
        return when {
            username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
                false
            }
            !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() -> {
                Toast.makeText(this, "Correo electrónico inválido", Toast.LENGTH_SHORT).show()
                false
            }
            password.length < 6 -> {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                false
            }
            password != confirmPassword -> {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }

    // Función para registrar el usuario en Firebase
    private fun registerUser(username: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registro exitoso, guardamos el nombre de usuario en Realtime Database
                    val user = auth.currentUser
                    val uid = user?.uid

                    // Creamos un objeto con los datos del usuario
                    val userMap = mapOf(
                        "uid" to uid,
                        "name" to username,
                        "email" to email
                    )

                    // Guardamos los datos del usuario en Realtime Database
                    val database = FirebaseDatabase.getInstance().reference
                    database.child("users").child(uid!!).setValue(userMap)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Registro exitoso y datos guardados", Toast.LENGTH_SHORT).show()
                                // Navegar a HomeActivity
                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Error en el registro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
