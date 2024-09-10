package com.example.recetario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.draw.clip





class HomeActivity : ComponentActivity() {

    private val black = Color(0xFF000000)
    private val white = Color(0xFFFFFFFF)
    private val colorAccent = Color(0xFFFF4C4C)
    private val colorBackground = Color(0xFFFFF6E9)
    private val colorButton = Color(0xFFE85C0D)
    private val colorText = Color(0xFF821131)
    private val colorSubtitle = Color(0xFFFFFFFF)
    private val colorCardBackground = Color(0xFFFABC3F)
    private val navigationColor = Color(0xFFC7253E)
    private val colorIconSelected = Color(0xFFFABC3F)
    private val colorIconUnselected = Color(0xFFBDBDBD)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }

    @Composable
    fun HomeScreen() {
        var selectedIndex by remember { mutableStateOf(0) }

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
                        RecipeList(
                            recipes = sampleRecipes(),
                            modifier = Modifier.padding(paddingValues)
                        )
                    }
                }
                1 -> FavoritesSection()
                2 -> ProfileSection()
                3 -> SavedSection()
            }
        }
    }

    @Composable
    fun Header() {
        TopAppBar(
            backgroundColor = colorAccent,
            contentColor = white,
            elevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = R.drawable.minilogo,
                    contentDescription = "Logo de la app",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Recetario",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = white
                )
            }
        }
    }

    @Composable
    fun BottomNavigationBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
        BottomNavigation(
            backgroundColor = navigationColor,
            contentColor = colorText
        ) {
            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Home",
                        tint = if (selectedIndex == 0) colorIconSelected else colorIconUnselected
                    )
                },
                label = { Text("Home", color = if (selectedIndex == 0) colorIconSelected else colorIconUnselected) },
                selected = selectedIndex == 0,
                onClick = { onItemSelected(0) }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Favoritos",
                        tint = if (selectedIndex == 1) colorIconSelected else colorIconUnselected
                    )
                },
                label = { Text("Favoritos", color = if (selectedIndex == 1) colorIconSelected else colorIconUnselected) },
                selected = selectedIndex == 1,
                onClick = { onItemSelected(1) }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Perfil",
                        tint = if (selectedIndex == 2) colorIconSelected else colorIconUnselected
                    )
                },
                label = { Text("Perfil", color = if (selectedIndex == 2) colorIconSelected else colorIconUnselected) },
                selected = selectedIndex == 2,
                onClick = { onItemSelected(2) }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Filled.Bookmark,
                        contentDescription = "Guardados",
                        tint = if (selectedIndex == 3) colorIconSelected else colorIconUnselected
                    )
                },
                label = { Text("Guardados", color = if (selectedIndex == 3) colorIconSelected else colorIconUnselected) },
                selected = selectedIndex == 3,
                onClick = { onItemSelected(3) }
            )
        }
    }

    @Composable
    fun FavoritesSection() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorBackground)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tus recetas favoritas aparecerán aquí",
                color = colorText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun ProfileSection()  {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorBackground)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Foto de perfil
            AsyncImage(
                model = "https://www.pngall.com/wp-content/uploads/5/Profile-Male-PNG.png",
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(60.dp)) // Circular
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre del usuario
            Text(
                text = "Jeremy Muñoz",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorText,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Edad y descripción breve
            Text(
                text = "25 años | Apasionado por la cocina",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de detalles con íconos
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

            // Sección de estadísticas del usuario
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStat(number = "34", label = "Mis Recetas")
                ProfileStat(number = "12", label = "Favoritas")
                ProfileStat(number = "8", label = "Guardadas")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de editar perfil
            Button(
                onClick = {
                    // Acción para editar el perfil
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorButton),
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
            Text(text = number, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = colorText)
            Text(text = label, fontSize = 14.sp, color = Color.Gray)
        }
    }


    @Composable
    fun SavedSection() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorBackground)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tus recetas guardadas aparecerán aquí",
                color = colorText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun SearchAndFilterSection() {
        var searchQuery by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .background(colorBackground)
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
            backgroundColor = colorBackground,
            contentColor = colorAccent
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

    @Composable
    fun RecipeList(recipes: List<Receta>, modifier: Modifier) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(colorBackground)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(recipes) { recipe ->
                    RecipeCard(recipe)
                }
            }
        }
    }

    @Composable
    fun RecipeCard(recipe: Receta) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = 8.dp,
            backgroundColor = colorCardBackground,
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = recipe.imagen,
                    contentDescription = recipe.nombre,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = recipe.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = colorText,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(5.dp))

                Button(
                    onClick = {
                        // Acción para ver detalles de la receta
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorButton),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Ver receta", color = white)
                }
            }
        }
    }

    data class Receta(val nombre: String, val ingredientes: String, val instrucciones: String, val imagen: String)

    @Composable
    fun sampleRecipes(): List<Receta> {
        return listOf(
            Receta(
                "Espaguetis a la Boloñesa",
                "Espaguetis, Carne molida de res, Cebolla, Ajo...",
                "1. Cocina los espaguetis según las instrucciones...",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqC3pTZiOdLb4QKiqDjHPoYuS3BoCfCiXRZA&s"
            ),
            Receta(
                "Tacos de Carnitas",
                "Carne de cerdo (espaldilla o pierna), Cebolla...",
                "1. Cocina la carne de cerdo en una olla a presión...",
                "https://cielitorosado.com/wp-content/uploads/2022/11/CARNITAS-sm.jpg"
            ),
            Receta(
                "Ensalada César",
                "Lechuga romana, Pechuga de pollo a la parrilla...",
                "1. Mezcla el ajo con el huevo, el jugo de limón...",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR05WL051cVLuZ6KCSJPla_Q4mWfWOu7-DiAg&s"
            ),
            Receta(
                "Galletas de Chispas de Chocolate",
                "Harina, Mantequilla, Azúcar, Azúcar moreno...",
                "1. Precalienta el horno a 180°C...",
                "https://www.gourmet.cl/wp-content/uploads/2018/02/Galletas-2-1-570x458.jpg"
            ),
            Receta(
                "Sopa de Lentejas",
                "Lentejas, Cebolla, Ajo, Zanahoria...",
                "1. En una olla grande, sofríe cebolla, ajo, zanahoria...",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVcbOuTPA0vIliy1e6cnld-L4FjfZa2Pezfw&s"
            ),
            Receta(
                "Pizza Margherita",
                "Masa de pizza, Salsa de tomate, Mozzarella fresca...",
                "1. Estira la masa de pizza sobre una superficie enharinada...",
                "https://imag.bonviveur.com/pizza-margarita.jpg"
            ),
            Receta(
                "Pollo al Curry",
                "Pechuga de pollo, Leche de coco, Curry en polvo...",
                "1. Corta las pechugas de pollo en cubos pequeños...",
                "https://i.blogs.es/8c3360/pollo_curry/450_1000.jpg"
            ),
            Receta(
                "Lasagna de Carne",
                "Láminas de lasagna, Carne molida, Salsa de tomate...",
                "1. Precalienta el horno a 180°C...",
                "https://www.recetasdesbieta.com/wp-content/uploads/2018/10/lasagna-original..jpg"
            ),
            Receta(
                "Tostadas de Aguacate",
                "Pan tostado, Aguacate, Aceite de oliva, Limón...",
                "1. Machaca el aguacate en un tazón con un tenedor...",
                "https://i0.wp.com/passionfood.ec/wp-content/uploads/2019/12/TOSTADA-AGUACATE.jpg?fit=880%2C880"
            ),
            Receta(
                "Paella Valenciana",
                "Arroz, Pollo, Conejo, Judías verdes...",
                "1. En una paellera grande, sofríe las piezas de pollo y conejo...",
                "https://imag.bonviveur.com/paella-valenciana-tradicional.jpg"
            ),
            Receta(
                "Brownies de Chocolate",
                "Chocolate negro, Mantequilla, Azúcar, Huevos...",
                "1. Precalienta el horno a 180°C y engrasa un molde...",
                "https://recetasdecocina.elmundo.es/wp-content/uploads/2016/11/brownie-de-chocolate.jpg"
            ),
            Receta(
                "Ceviche de Pescado",
                "Pescado blanco, Limón, Cebolla morada, Cilantro...",
                "1. Corta el pescado en cubos pequeños y colócalo en un tazón...",
                "https://imag.bonviveur.com/ceviche-peruano-de-pescado.jpg"
            ),
            Receta(
                "Churros con Chocolate",
                "Harina, Agua, Mantequilla, Azúcar, Canela...",
                "1. En una cacerola, hierve el agua con la mantequilla y una pizca de sal...",
                "https://www.recetasnestle.com.pe/sites/default/files/srh_recipes/908133d815ef9066e4abfb330e7c33d9.png"
            )
        )

    }
}
