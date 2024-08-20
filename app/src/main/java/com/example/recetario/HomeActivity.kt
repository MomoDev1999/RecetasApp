package com.example.recetario

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    data class Receta(val nombre: String, val ingredientes: String, val instrucciones: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recetas = arrayOf(
            Receta(
                "Espaguetis a la Boloñesa",
                "Espaguetis, Carne molida de res, Cebolla, Ajo, Tomate triturado, Zanahoria, Apio, Aceite de oliva, Sal, Pimienta, Orégano, Albahaca\n",
                "1. Cocina los espaguetis según las instrucciones del paquete. 2. En una sartén grande, calienta aceite de oliva y sofríe cebolla, ajo, zanahoria y apio hasta que estén tiernos. 3. Añade la carne molida y cocina hasta que esté dorada. 4. Incorpora el tomate triturado y condimenta con sal, pimienta, orégano y albahaca. Cocina a fuego lento durante 20 minutos. 5. Sirve la salsa sobre los espaguetis cocidos."
            ),
            Receta(
                "Tacos de Carnitas",
                "Carne de cerdo (espaldilla o pierna), Cebolla, Ajo, Comino, Orégano, Pimentón, Sal, Pimienta, Tortillas de maíz, Limón, Cilantro\n",
                "1. Cocina la carne de cerdo en una olla a presión con cebolla, ajo, comino, orégano, pimentón, sal y pimienta durante 1 hora o hasta que esté tierna. 2. Desmenuza la carne y fríela en una sartén hasta que esté crujiente. 3. Calienta las tortillas y sirve la carne con limón y cilantro al gusto."
            ),
            Receta(
                "Ensalada César",
                "Lechuga romana, Pechuga de pollo a la parrilla, Pan tostado (crutones), Queso parmesano, Ajo, Huevo, Aceite de oliva, Jugo de limón, Anchoas (opcional), Sal, Pimienta\n",
                "1. Mezcla el ajo con el huevo, el jugo de limón y el aceite de oliva para hacer el aderezo. 2. Bate bien y añade sal y pimienta al gusto. 3. En un bol grande, mezcla la lechuga con el aderezo. 4. Añade el pollo en tiras, los crutones y el queso parmesano rallado. 5. Sirve inmediatamente."
            ),
            Receta(
                "Galletas de Chispas de Chocolate",
                "Harina, Mantequilla, Azúcar, Azúcar moreno, Huevos, Extracto de vainilla, Polvo de hornear, Sal, Chispas de chocolate\n",
                "1. Precalienta el horno a 180°C. 2. Bate la mantequilla con el azúcar y el azúcar moreno hasta que esté cremosa. 3. Añade los huevos uno a uno y mezcla bien. 4. Incorpora la harina, el polvo de hornear y la sal, y mezcla hasta obtener una masa homogénea. 5. Agrega las chispas de chocolate y mezcla. 6. Forma bolitas de masa y colócalas en una bandeja para hornear. 7. Hornea durante 10-12 minutos o hasta que los bordes estén dorados."
            ),
            Receta(
                "Sopa de Lentejas",
                "Lentejas, Cebolla, Ajo, Zanahoria, Apio, Tomate, Caldo de verduras, Laurel, Comino, Sal, Pimienta\n",
                "1. En una olla grande, sofríe cebolla, ajo, zanahoria y apio hasta que estén tiernos. 2. Añade las lentejas, el tomate picado y el caldo de verduras. 3. Agrega el laurel, el comino, sal y pimienta. 4. Cocina a fuego lento durante 30-40 minutos o hasta que las lentejas estén tiernas. 5. Retira el laurel antes de servir."
            )
        )




        val recetasTexto = recetas.joinToString(separator = "\n\n") { receta ->
            "${receta.nombre}\n\nIngredientes:\n${receta.ingredientes}\n\nInstrucciones:\n${receta.instrucciones}"
        }

        val recipesTextView: TextView = findViewById(R.id.recipes_text_view)
        recipesTextView.text = recetasTexto
    }
}
