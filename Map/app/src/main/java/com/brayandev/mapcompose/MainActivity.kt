package com.brayandev.mapcompose

import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.widget.ZoomControls
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.brayandev.mapcompose.ui.theme.MapComposeTheme
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.currentCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MapComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()

                ) {
                    myGoogleMaps()
                }
            }
        }
    }
}

// Funcionalidades por ejemplo
// 1. Marcar una ubicacion
// 2. Propiedades de la api
// 3. UI setting
@Preview
@Composable
fun myGoogleMaps() {
    val marker = LatLng(8.9476, -79.7568)
    // Estado recuerdame siempre este setting
    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.HYBRID)) }
    val uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = false)) }

    GoogleMap(modifier = Modifier.fillMaxSize(),
        properties = properties,
        uiSettings = uiSettings
        ) {
        // Esta es la forma de hacer Marker(position= marker)
        Marker(
            state = MarkerState(position = marker),
            title = "Titulo de la api -> La Chorrera Panama",
            snippet = "Probando la api nativa de google maps, esto seria la propieda snippet de google maps api "
        )
    }
}