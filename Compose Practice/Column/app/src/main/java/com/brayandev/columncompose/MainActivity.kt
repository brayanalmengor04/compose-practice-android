package com.brayandev.columncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*;
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            app()
        }
    }
}

// Layout en columna
@Preview
@Composable
fun app() {
    // Variable para el contador
    // Lo que estamos diciendo es que lo recuerde (remember)
    var counter by rememberSaveable { mutableStateOf(0)}
    var isFavorite by rememberSaveable { mutableStateOf(false) }

    // Esto es como un recycle views
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        item {
            Column(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = "https://github.com/brayanalmengor04.png",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                )
                // Estados
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Image(
                        painter = if (isFavorite) {
                            painterResource(id = R.drawable.ic_favorite_active)
                        } else {
                            painterResource(id = R.drawable.ic_favorite)
                        },
                        contentDescription = "Loading",
                        modifier = Modifier.clickable {
                                isFavorite = true
                              counter++
                        }.padding(start =25.dp).size(35.dp)
                    )
                    Text(text = counter.toString(), fontSize = 20.sp, color = Color.White, modifier = Modifier.padding(start = 4.dp))
                }
                Text(
                    text = "BrayanDEV",
                    fontSize = 32.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Esto es un segundo texto",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center

                )
                Text(
                    text = "Buenas",
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                // Ingreso una fila (ROW)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp), // Espacio de 8.dp entre cada elemento
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    item {
                        Column {
                            Text("Stack:", fontSize = 15.sp, color = Color.White)
                        }
                    }
                    // Para simplificar ahcer tantos Text lo paso una lista y los pinto
                    items(
                        listOf(
                            "Java",
                            "Kotlin",
                            "Laravel",
                            "Php",
                            "Angular",
                            "Spring Boot",
                            "Visual Basic.NET",
                            "C++",
                            "Bootstrap % Tailwing"
                        )
                    ) { funcionFlecha ->
                        Text(funcionFlecha, fontSize = 16.sp, color = Color.White)
                    }
                }

            }

        }
    }
}