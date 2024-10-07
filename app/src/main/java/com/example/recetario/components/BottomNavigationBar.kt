package com.example.recetario.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

@Composable
fun BottomNavigationBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    BottomNavigation(
        backgroundColor = Color(0xFFC7253E),
        contentColor = Color(0xFF821131)
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = if (selectedIndex == 0) Color(0xFFFABC3F) else Color(0xFFBDBDBD)
                )
            },
            label = { Text("Home") },
            selected = selectedIndex == 0,
            onClick = { onItemSelected(0) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favoritos",
                    tint = if (selectedIndex == 1) Color(0xFFFABC3F) else Color(0xFFBDBDBD)
                )
            },
            label = { Text("Favoritos") },
            selected = selectedIndex == 1,
            onClick = { onItemSelected(1) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Perfil",
                    tint = if (selectedIndex == 2) Color(0xFFFABC3F) else Color(0xFFBDBDBD)
                )
            },
            label = { Text("Perfil") },
            selected = selectedIndex == 2,
            onClick = { onItemSelected(2) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.Bookmark,
                    contentDescription = "Guardados",
                    tint = if (selectedIndex == 3) Color(0xFFFABC3F) else Color(0xFFBDBDBD)
                )
            },
            label = { Text("Guardados") },
            selected = selectedIndex == 3,
            onClick = { onItemSelected(3) }
        )
    }
}
