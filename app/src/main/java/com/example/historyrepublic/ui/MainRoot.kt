package com.example.historyrepublic.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.historyrepublic.MainScreen
import com.example.historyrepublic.ui.herodetail.HeroDetailScreen
import com.example.historyrepublic.ui.map.MapHeroesScreen
import com.example.historyrepublic.ui.theme.BottomBarScreenSealed
import com.example.historyrepublic.ui.theme.NavigationScreenSealed
import com.example.historyrepublic.ui.youtubelist.YoutubeListScreen

@Composable
fun MainRoot() {
    val navController = rememberNavController()
    val items = listOf(BottomBarScreenSealed.Home, BottomBarScreenSealed.Map, BottomBarScreenSealed.Videos)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Iteramos las routas del tab menu
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Esto evita que se amontonen pantallas al pulsar el tab
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Aquí conectamos tus rutas
        NavHost(
            navController = navController,
            startDestination = BottomBarScreenSealed.Home.route,
            modifier = Modifier.padding(innerPadding) // Evita que el tab bar tape la lista
        ) {

            // Lista de heroes
            composable(BottomBarScreenSealed.Home.route) {
                // LLAMAMOS A TU MAINSCREEN ACTUAL
                MainScreen(navController)
            }

            // Mapa
            composable(BottomBarScreenSealed.Map.route) {
               MapHeroesScreen(navController)
            }

            // Videos

            composable(BottomBarScreenSealed.Videos.route) {
                YoutubeListScreen(navController)
            }

            // Detalle de heroes
            composable(NavigationScreenSealed.HeroDetail.route) { backStackEntry ->
                val heroId = backStackEntry.arguments?.getString("heroId") ?: ""
                HeroDetailScreen(heroId, navController)
            }
        }
    }
}