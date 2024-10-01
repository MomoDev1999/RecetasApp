package com.example.recetario.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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

@Composable
fun ProfileSection() {
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

        Text(
            text = "Jeremy Muñoz",
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

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Email, contentDescription = "Email", tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "jeremymuñoz@example.com",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Phone, contentDescription = "Teléfono", tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "+569 4637 8216",
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
    }
}

@Composable
fun ProfileStat(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = number, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF821131))
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
    }
}
