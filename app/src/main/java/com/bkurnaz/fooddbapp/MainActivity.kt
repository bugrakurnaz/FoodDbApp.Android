package com.bkurnaz.fooddbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bkurnaz.fooddbapp.ui.screens.CategoriesScreen
import com.bkurnaz.fooddbapp.ui.theme.FoodDbAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDbAppTheme {
                FoodDbAppApp()
            }
        }
    }
}

@Composable
fun FoodDbAppApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.CATEGORIES) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {},
        bottomBar = {},
        content = { innerPadding ->
            androidx.compose.foundation.layout.Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // 🧭 LEFT NAVIGATION RAIL
                NavigationRail(
                    modifier = Modifier.padding(vertical = 8.dp),
                    header = {
                        Text(
                            text = "Views",
                            modifier = Modifier
                                .padding(12.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                ) {
                    AppDestinations.entries.forEach { destination ->
                        NavigationRailItem(
                            icon = { Icon(destination.icon, contentDescription = destination.label) },
                            label = { Text(destination.label) },
                            selected = currentDestination == destination,
                            onClick = { currentDestination = destination }
                        )
                    }
                }

                // 🖥️ MAIN CONTENT
                when (currentDestination) {
                    AppDestinations.CATEGORIES -> CategoriesScreen()
                    AppDestinations.FAVORITES -> PlaceholderScreen("Favorites")
                    AppDestinations.PROFILE -> PlaceholderScreen("Profile")
                }
            }
        }
    )
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    CATEGORIES("Categories", Icons.Default.MoreVert),
    FAVORITES("Favorites", Icons.Default.Favorite),
    PROFILE("Profile", Icons.Default.Person),
}

@Composable
fun PlaceholderScreen(title: String) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun FoodDbAppPreview() {
    FoodDbAppTheme {
        FoodDbAppApp()
    }
}