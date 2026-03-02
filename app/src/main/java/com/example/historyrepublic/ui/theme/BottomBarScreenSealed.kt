package com.example.historyrepublic.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreenSealed(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreenSealed(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Map : BottomBarScreenSealed(
        route = "map",
        title = "Map",
        icon = Icons.Default.LocationOn
    )

    object Videos: BottomBarScreenSealed(
        route = "videos",
        title = "Videos",
        icon = Icons.Default.PlayCircle
    )
}