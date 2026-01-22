package com.example.historyrepublic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.historyrepublic.domain.Hero
import com.example.historyrepublic.ui.herolist.HeroListScreen
import com.example.historyrepublic.ui.herolist.HeroListViewModel
import com.example.historyrepublic.ui.theme.HistoryRepublicTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val heroListViewModel: HeroListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HistoryRepublicTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HeroListScreen(heroListViewModel.heros, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

private fun generateHeros() = (0 until 10).map { Hero("id$it", "Name$it", "Title$it", "Information$it","image$it", "Url$it") }