package com.example.recetario.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.draw.clip
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import android.content.Intent
import com.example.recetario.MainActivity
import androidx.compose.ui.platform.LocalContext
import com.example.recetario.models.Receta

@Composable
fun ProfileSection() {
    var username by remember { mutableStateOf("Cargando...") }
    var email by remember { mutableStateOf("Cargando...") }
    var recetas by remember { mutableStateOf(listOf<Receta>()) }

    // Referencia al usuario actual
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser
    val uid = currentUser?.uid

    // Recuperar los datos del usuario desde Firebase Realtime Database
    if (uid != null) {
        LaunchedEffect(uid) {
            val database = FirebaseDatabase.getInstance().getReference("users").child(uid)
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userMap = snapshot.value as Map<*, *>?
                    username = userMap?.get("name") as? String ?: "Usuario desconocido"
                    email = userMap?.get("email") as? String ?: "Correo desconocido"
                }

                override fun onCancelled(error: DatabaseError) {
                    // Manejar errores
                }
            })

            // Recuperar las recetas desde Firebase
            val recetasRef = FirebaseDatabase.getInstance().getReference("Recetas")
            recetasRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val recetasList = mutableListOf<Receta>()
                    for (recetaSnapshot in dataSnapshot.children) {
                        val receta = recetaSnapshot.getValue(Receta::class.java)
                        receta?.let { recetasList.add(it) }
                    }
                    recetas = recetasList
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Manejar errores
                }
            })
        }
    }

    // UI del perfil con los datos dinámicos del usuario
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF6E9))
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = "https://www.pngall.com/wp-content/uploads/5/Profile-Male-PNG.png",
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(60.dp))
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar el nombre de usuario desde Firebase
            Text(
                text = username,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF821131)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "25 años | Apasionado por la cocina",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar el correo electrónico desde Firebase
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Email, contentDescription = "Email", tint = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = email,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Phone, contentDescription = "Teléfono", tint = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "+569 4637 8216", // Esto puedes cambiarlo por un campo real si es necesario
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.LocationOn, contentDescription = "Ubicación", tint = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Santiago, Chile",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStat(number = "34", label = "Mis Recetas")
                ProfileStat(number = "12", label = "Favoritas")
                ProfileStat(number = "8", label = "Guardadas")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón para editar perfil
            Button(
                onClick = { /* Acción para editar el perfil */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE85C0D)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Icon(Icons.Filled.Edit, contentDescription = "Editar", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Editar Perfil", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Button Cerrar sesion
            val context = LocalContext.current // Esto debe estar fuera de onClick
            Button(
                onClick = {
                    auth.signOut() // Cierra la sesión del usuario
                    // Navegar a la pantalla de inicio de sesión o cualquier otra actividad
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Icon(Icons.Filled.ExitToApp, contentDescription = "Cerrar Sesión", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cerrar Sesión", color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sección "Mis Recetas" con lista de recetas desde Firebase
            MisRecetasSection(recetas = recetas)
        }

        // Botón flotante para agregar recetas
        FloatingActionButton(
            onClick = { /* Acción para agregar recetas */ },
            backgroundColor = Color(0xFFE85C0D),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Agregar Receta", tint = Color.White)
        }
    }
}

@Composable
fun ProfileStat(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = number, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF821131))
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
    }
}

@Composable
fun MisRecetasSection(recetas: List<Receta>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Título "Mis Recetas"
        Text(
            text = "Mis Recetas",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(8.dp),
            color = Color(0xFF821131)
        )

        // Mostrar una lista de tarjetas de recetas
        recetas.forEach { receta ->
            RecetaCard(
                receta = receta,
                onVerClick = { /* Acción al ver la receta */ },
                onEditarClick = { /* Acción al editar la receta */ },
                onEliminarClick = { /* Acción al eliminar la receta */ }
            )
        }
    }
}
