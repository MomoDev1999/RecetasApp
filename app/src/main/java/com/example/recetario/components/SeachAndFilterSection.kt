package com.example.recetario.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background

@Composable
fun SearchAndFilterSection() {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(Color(0xFFFFF6E9))
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            placeholder = { Text("Busca recetas...") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TabSection()
    }
}

@Composable
fun TabSection() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Últimas", "Populares")
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color(0xFFFFF6E9),
        contentColor = Color(0xFFFF4C4C)
    ) {
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index },
                text = { Text(title) }
            )
        }
    }
}
