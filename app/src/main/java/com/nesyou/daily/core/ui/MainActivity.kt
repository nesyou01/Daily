package com.nesyou.daily.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nesyou.daily.core.domain.utils.Screen
import com.nesyou.daily.core.ui.theme.DailyTheme
import com.nesyou.daily.features.auth.ui.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Login.route) {

        }
        composable(Screen.Splash.route) {
            SplashScreen()
        }
    }
}