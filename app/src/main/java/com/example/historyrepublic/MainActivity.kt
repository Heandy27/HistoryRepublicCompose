package com.example.historyrepublic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.historyrepublic.ui.herodetail.HeroDetailScreen
import com.example.historyrepublic.ui.herolist.HeroListScreen
import com.example.historyrepublic.ui.herolist.HeroListViewModel
import com.example.historyrepublic.ui.herolist.UIState
import com.example.historyrepublic.ui.theme.HistoryRepublicTheme
import com.example.historyrepublic.ui.theme.NavigationScreenSealed
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.TopAppBarDefaults


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val heroListViewModel: HeroListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HistoryRepublicTheme {
                var navcontroller = rememberNavController()
                NavHost(navController = navcontroller, startDestination = NavigationScreenSealed.HeroList.route) {
                    // Pantalla Lista
                    composable(NavigationScreenSealed.HeroList.route) {
                        MainScreen(heroListViewModel, navcontroller)
                    }
                    // Pantalla detalle
                    composable(NavigationScreenSealed.HeroDetail.route) { backStackEntry ->

                        val heroId = backStackEntry.arguments?.getString("heroId") ?: ""

                        HeroDetailScreen(heroId, navcontroller)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreen(
    heroListViewModel: HeroListViewModel,
    navcontroller: NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "All Heroes",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }

    ) { innerPadding ->

        val state by heroListViewModel.state.collectAsState()

        when (state) {

            is UIState.Success -> {
                HeroListScreen(
                    hero = (state as UIState.Success).data,
                    modifier = Modifier.padding(innerPadding),
                    onHeroClick = { heroId ->
                        navcontroller.navigate(
                            NavigationScreenSealed.HeroDetail.createRoute(heroId)
                        )
                    }
                )
            }

            else -> {}
        }
    }
}
