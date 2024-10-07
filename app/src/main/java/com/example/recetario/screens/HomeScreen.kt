package com.example.recetario.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.recetario.components.*
import com.example.recetario.models.Receta
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

@Composable
fun HomeScreen(database: DatabaseReference) {
    var selectedIndex by remember { mutableStateOf(0) }
    val recipes = remember { mutableStateListOf<Receta>() }

    LaunchedEffect(Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                recipes.clear()
                snapshot.children.mapNotNullTo(recipes) { it.getValue(Receta::class.java) }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Database error: $error")
            }
        })
    }

    Scaffold(
        topBar = { Header() },
        bottomBar = {
            BottomNavigationBar(
                selectedIndex = selectedIndex,
                onItemSelected = { newIndex -> selectedIndex = newIndex }
            )
        }
    ) { paddingValues ->
        when (selectedIndex) {
            0 -> {
                Column {
                    SearchAndFilterSection()
                    RecipeList(recipes = recipes, modifier = Modifier.padding(paddingValues))
                }
            }
            1 -> FavoritesSection()
            2 -> ProfileSection()
            3 -> SavedSection()
        }
    }
}
