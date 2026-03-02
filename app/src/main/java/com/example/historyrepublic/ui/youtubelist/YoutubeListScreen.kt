package com.example.historyrepublic.ui.youtubelist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.historyrepublic.domain.Hero
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.historyrepublic.ui.herolist.HeroListViewModel
import com.example.historyrepublic.ui.herolist.UIState

@Composable
fun ItemVideoOptimizado(video: Hero) {
    val context = LocalContext.current

    // Construimos la URL de la miniatura
    val thumbnailUrl = "https://img.youtube.com/vi/${video.youtubeVideo}/hqdefault.jpg"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable {
                // Acción al hacer clic: Abrir YouTube
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/shorts/${video.youtubeVideo}")
                )
                context.startActivity(intent)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Contenedor para la imagen y el ícono de Play
            Box(contentAlignment = Alignment.Center) {
                AsyncImage(
                    model = thumbnailUrl,
                    contentDescription = "Miniatura del video ${video.title}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f), // Mantiene la proporción de video
                    contentScale = ContentScale.Crop
                )

                // Ícono de Play semitransparente encima de la imagen
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = Color.Black.copy(alpha = 0.6f),
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Reproducir",
                        tint = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            // Título del video
            Text(
                text = video.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun YoutubeList(
    hero: List<Hero>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
    ) {
        items(hero) { video ->
            ItemVideoOptimizado(video)
        }
    }
}

@Composable
fun YoutubeListScreen(
    navcontroller: NavHostController,
    heroListViewModel: HeroListViewModel = hiltViewModel()
) {
    val state by heroListViewModel.state.collectAsState()

    // 1. FORZAMOS LA CARGA DE DATOS AL ABRIR LA PANTALLA
    LaunchedEffect(Unit) {
        heroListViewModel.getHeroes()
    }

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
        UIState.Loading -> { Box(modifier = Modifier.fillMaxSize(), Alignment.Center) { CircularProgressIndicator() } }
        is UIState.Success -> {
            val data = (state as UIState.Success).data
            YoutubeList(data)
        }
    }
}
