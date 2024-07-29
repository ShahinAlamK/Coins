package com.iconic.coins.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.iconic.coins.presentation.coins.CoinScreen
import com.iconic.coins.presentation.details.CoinDetailScreen
import com.iconic.coins.ui.theme.CoinsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinsTheme {
                Navigation()
            }
        }
    }
}


@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = "coin_screen"

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = "coin_screen") {
            CoinScreen(
                navigateToDetail = {
                    navController.navigate("detail_screen/$it")
                }
            )
        }
        composable(
            route = "detail_screen/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )

        ) {
            val id = it.arguments?.getString("id")
            Log.d("COIN_ID", "Navigation: $id")
            CoinDetailScreen()
        }


    }
}