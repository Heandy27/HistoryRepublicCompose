package com.example.historyrepublic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp


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
    val state by heroListViewModel.state.collectAsState()
    // ✅ Texto que escribe el usuario
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            Column() {

                TopAppBar(
                    title = {
                        Text(
                            text = "Heroes",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )

                // ✅ Search Field
                TextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        heroListViewModel.updateSearch(it)
                                    },
                    placeholder = { Text("Search...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .clipToBounds(),
                    shape = RoundedCornerShape(20.dp),
                    singleLine = true
                )
            }

        }

    ) { innerPadding ->



        when (state) {
            is UIState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), Alignment.Center) { CircularProgressIndicator() }
            }

            is UIState.Success -> {
                HeroListScreen(
                    hero = (state as UIState.Success).data,
                    modifier = Modifier.padding(innerPadding),
                    onHeroClick = { heroId ->
                       // heroListViewModel.clearSearch()
                        searchText = ""
                        navcontroller.navigate(
                            NavigationScreenSealed.HeroDetail.createRoute(heroId)
                        )
                    }
                )
            }

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
        }
    }
}
