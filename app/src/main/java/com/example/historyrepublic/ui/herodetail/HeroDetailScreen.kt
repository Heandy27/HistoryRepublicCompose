package com.example.historyrepublic.ui.herodetail

import android.webkit.WebView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil3.compose.AsyncImage
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.historyrepublic.data.network.model.SingleHeroResponse
import com.example.historyrepublic.domain.Hero
import com.example.historyrepublic.ui.herolist.UIState

@Composable
fun HeroDetailScreen(
    heroId: String,
    navcontroller: NavHostController,
    viewModel: HeroDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(heroId) {
        viewModel.fetchHeroById(heroId)
    }

    when (state) {

        is UIState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), Alignment.Center) { CircularProgressIndicator() }

        }

        is UIState.Success -> {

            val hero = (state as UIState.Success<SingleHeroResponse>).data

            Column {
                AndroidView(
                    factory = { context ->
                        WebView(context)
                    },
                    update = { webView ->
                        webView.loadUrl(hero.url)
                    }
                )
            }
        }

        is UIState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text("❌ Error loading hero")

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = { viewModel.fetchHeroById(id = heroId) }) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}



