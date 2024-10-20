package com.brayandev.dialogcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.brayandev.dialogcompose.ui.theme.DialogComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DialogComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    // Estado

                    var isshow by rememberSaveable { mutableStateOf(false)}

                    // Esto seria el frame layout
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(onClick = {isshow= true}) {
                            Text(text = "Dialogo de Texto")
                        }
                    }
                    // Dissmis y confirm
                    myDialog(isshow,{isshow= false},{ Log.i("Brayan","Click")})
                }
            }
        }
    }

}
@Composable
fun myDialog(isshow: Boolean,
onDismiss : ()-> Unit,
onConfirm : ()-> Unit
) {

    if (isshow){
        AlertDialog(onDismissRequest = {onDismiss()},
            confirmButton = { TextButton(onClick = {onConfirm()}){
                Text(text = "Confirm Button")
            } },
            dismissButton = { TextButton(onClick = {onDismiss()}){
                Text(text = "Dismiss Button")
            } },
            title = { Text(text = "Titulo del Dialogo") },
            text = { Text(text = "Esto es el cuerpo (Body del Alert Dialog") })
    }

}




