package com.example.historyrepublic.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.historyrepublic.domain.Hero
import com.example.historyrepublic.ui.herolist.HeroListViewModel
import com.example.historyrepublic.ui.herolist.UIState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
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
                LatLng(heroes[0].latitude, heroes[0].longitude)
            } else {
                LatLng(0.0, 0.0) // Coordenada por defecto si la lista está vacía
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
                heroes.forEach { heroe ->
                    Marker(
                        state = MarkerState(position = LatLng(heroe.latitude, heroe.longitude)),
                        title = heroe.nameHero,
                        snippet = heroe.title,
                        onClick = {
                            // Navegamos al detalle usando el ID del héroe
                            navcontroller.navigate("heroDetail/${heroe.id}")
                            true // Marcamos como manejado para que no se abra el globo por defecto si no quieres
                        }
                    )
                }
            }
        }
    }




}