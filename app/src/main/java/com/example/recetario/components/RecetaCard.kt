package com.example.recetario.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.ui.draw.clip
import com.example.recetario.models.Receta

@Composable
fun RecetaCard(receta: Receta, onVerClick: () -> Unit, onEditarClick: () -> Unit, onEliminarClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFF6E9))
        ) {
            // Imagen de la receta
            AsyncImage(
                model = receta.imagen,
                contentDescription = receta.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Título de la receta
            Text(
                text = receta.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color(0xFF821131)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botón con tres puntos (menú desplegable)
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Opciones")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(onClick = {
                        expanded = false
                        onVerClick()
                    }) {
                        Text("Ver")
                    }
                    DropdownMenuItem(onClick = {
                        expanded = false
                        onEditarClick()
                    }) {
                        Text("Editar")
                    }
                    DropdownMenuItem(onClick = {
                        expanded = false
                        onEliminarClick()
                    }) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}
