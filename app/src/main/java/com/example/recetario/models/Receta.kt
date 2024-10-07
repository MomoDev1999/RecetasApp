package com.example.recetario.models

data class Receta(
    val nombre: String = "",
    val ingredientes: String = "",
    val instrucciones: String = "",
    val descripcion: String = "",
    val imagen: String = ""
)
