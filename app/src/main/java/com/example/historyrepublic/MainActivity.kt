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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.historyrepublic.domain.Hero
import com.example.historyrepublic.ui.herolist.HeroListScreen
import com.example.historyrepublic.ui.herolist.HeroListState
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
                var navcontroller = rememberNavController()
                NavHost(navController = navcontroller, startDestination = "herolist") {
                    composable("herolist") {
                       MainScreen(heroListViewModel)
                    }
                }
            }
        }
    }
}

@Composable
private fun MainScreen(heroListViewModel: HeroListViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val state by heroListViewModel.state.collectAsState()
        when (state) {
            is HeroListState.Success -> {
                HeroListScreen((state as HeroListState.Success).heros, modifier = Modifier.padding(innerPadding))
            }
            else -> {}
        }

    }
}