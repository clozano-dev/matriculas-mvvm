package com.clozanodev.matriculas.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import androidx.navigation.NavController
import com.clozanodev.matriculas.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(8.dp)
    ) {
        val currentRoute = currentRoute(navController)

        // Definimos los elementos del menú con animaciones y cambios de color suaves
        BottomNavigationItem(
            icon = {
                Icon(Icons.Filled.Home, contentDescription = null)
            },
            label = {
                Text(text = stringResource(R.string.home))
            },
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            alwaysShowLabel = false,
            selectedContentColor = MaterialTheme.colorScheme.background,
            unselectedContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
        )

        BottomNavigationItem(
            icon = {
                Icon(painterResource(R.drawable.stats), contentDescription = null)
            },
            label = {
                Text(text = stringResource(R.string.statistics))
            },
            selected = currentRoute == "statistics",
            onClick = {
                navController.navigate("statistics") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            alwaysShowLabel = false,
            selectedContentColor = MaterialTheme.colorScheme.background,
            unselectedContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
        )

        BottomNavigationItem(
            icon = {
                Icon(Icons.Filled.Info, contentDescription = null)
            },
            label = {
                Text(text = stringResource(R.string.rules))
            },
            selected = currentRoute == "rules",
            onClick = {
                navController.navigate("rules") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            alwaysShowLabel = false,
            selectedContentColor = MaterialTheme.colorScheme.background,
            unselectedContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
        )
    }
}


@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
