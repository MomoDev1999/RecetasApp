package com.example.recetario.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recetario.models.Receta
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun RecipeCard(recipe: Receta) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
        backgroundColor = Color(0xFFFABC3F),
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = recipe.imagen ?: "",
                contentDescription = recipe.nombre ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = recipe.nombre ?: "Nombre no disponible",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color(0xFF821131),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = { /* Acción para ver detalles de la receta */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE85C0D)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Ver receta", color = Color.White)
            }
        }
    }
}
