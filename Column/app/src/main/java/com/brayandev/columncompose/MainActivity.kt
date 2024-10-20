package com.brayandev.columncompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewContainer()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // Esto es lo que evita el error
@Preview
@Composable
fun viewContainer() {
    Scaffold(
        topBar = {
            SimpleTopAppBar()
        },
        content = { paddingValues ->
            Content(modifier = Modifier.padding(paddingValues))
        },
        floatingActionButton = {
            FAB()
        }
    )
}

@Composable
fun FAB(){
    val context = LocalContext.current
    FloatingActionButton(onClick ={
        Toast.makeText(context,"Github: brayanalmengor04",Toast.LENGTH_SHORT).show()}
    ){
        Text("X")
    }
}


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar() {
    TopAppBar(
        title = { Text(text = "BrayanDEV", color = Color.White) },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.purple_500)
        )
    )
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    var counter by rememberSaveable { mutableStateOf(0) }
    var isFavorite by rememberSaveable { mutableStateOf(false) }

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
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Image(
                        painter = if (isFavorite) {
                            painterResource(id = R.drawable.ic_favorite_active)
                        } else {
                            painterResource(id = R.drawable.ic_favorite)
                        },
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                isFavorite = true
                                counter++
                            }
                            .padding(start = 25.dp)
                            .size(35.dp)
                    )
                    Text(
                        text = counter.toString(),
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 4.dp)
                    )
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

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    item {
                        Column {
                            Text("Stack:", fontSize = 15.sp, color = Color.White)
                        }
                    }
                    items(
                        listOf(
                            "Java", "Kotlin", "Laravel", "Php",
                            "Angular", "Spring Boot", "Visual Basic.NET",
                            "C++", "Bootstrap & Tailwind"
                        )
                    ) { stackItem ->
                        Text(stackItem, fontSize = 16.sp, color = Color.White)
                    }
                }
            }
        }
    }
}
