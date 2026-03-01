package com.example.historyrepublic.ui.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.historyrepublic.R
import com.example.historyrepublic.domain.Hero
import com.example.historyrepublic.ui.herolist.HeroListViewModel
import com.example.historyrepublic.ui.herolist.UIState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapaHeroesScreen(
    navcontroller: NavHostController,
    heroListViewModel: HeroListViewModel = hiltViewModel()
) {

    val state by heroListViewModel.state.collectAsState()

    when (state) {
        is UIState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text("❌ Error loading heroes")

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = { heroListViewModel.getHeroes() }) {
                        Text("Retry")
                    }
                }
            }
        }
        UIState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), Alignment.Center) { CircularProgressIndicator() }
        }
        is UIState.Success -> {

            // 1. Extraemos la lista de héroes
            val heroes = (state as UIState.Success<List<Hero>>).data

            // 2. Posición inicial: Usamos al primer héroe de la lista (si existe)
            val initialLocation = if (heroes.isNotEmpty()) {
                LatLng(heroes.first().latitude, heroes.first().longitude)
            } else {
                LatLng(51.1657, 10.4515) // Coordenada por defecto si la lista está vacía
            }

            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(initialLocation, 5f)
            }

            // 3. El componente principal del Mapa
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                // 4. Iteramos sobre la lista real de héroes
                heroes.forEachIndexed { index, heroe ->
                    val offsetLat = if (index > 0) (index * 0.005) else 0.0
                    val offsetLng = if (index > 0) (index * 0.005) else 0.0

                    val posicionSeparada = LatLng(heroe.latitude + offsetLat, heroe.longitude + offsetLng)

                    MarkerComposable(
                        state = MarkerState(position = posicionSeparada),
                        title = heroe.nameHero,
                        onClick = {
                            navcontroller.navigate("heroDetail/${heroe.id}")
                            true }
                    ) {
                        // Usamos un Box para darle fondo circular al icono
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    painter = painterResource(R.drawable.logo_historyrepublic),
                                    contentDescription = "logo",
                                    // Asegura que la imagen llene el círculo cortando los bordes sobrantes sin aplastarse
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(35.dp) // Nota: Poner solo un 50.dp aplica para ancho y alto automáticamente
                                        .clip(CircleShape) // ¡Esta es la magia que lo hace redondo!
                                )
                                Text(heroe.title)
                            }
                        }
                    }
                }
            }
        }
    }




}