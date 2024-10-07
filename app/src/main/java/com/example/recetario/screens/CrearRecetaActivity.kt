package com.example.recetario.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recetario.models.Receta

@Composable
fun CrearRecetaActivity(onRecetaCreada: (Receta) -> Unit) {
    var nombre by remember { mutableStateOf(TextFieldValue()) }
    var ingredientes by remember { mutableStateOf(TextFieldValue()) }
    var instrucciones by remember { mutableStateOf(TextFieldValue()) }
    var descripcion by remember { mutableStateOf(TextFieldValue()) }
    var imagen by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Crear Nueva Receta", fontSize = 24.sp)

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre de la receta") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = ingredientes,
            onValueChange = { ingredientes = it },
            label = { Text("Ingredientes") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = instrucciones,
            onValueChange = { instrucciones = it },
            label = { Text("Instrucciones") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = imagen,
            onValueChange = { imagen = it },
            label = { Text("URL de la imagen") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val nuevaReceta = Receta(
                nombre = nombre.text,
                ingredientes = ingredientes.text,
                instrucciones = instrucciones.text,
                descripcion = descripcion.text,
                imagen = imagen.text
            )
            onRecetaCreada(nuevaReceta)
        }) {
            Text("Crear Receta")
        }
    }
}
