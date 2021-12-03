package com.nesyou.daily.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nesyou.daily.core.domain.utils.Screen
import com.nesyou.daily.core.ui.theme.DailyTheme
import com.nesyou.daily.features.auth.ui.login.LoginScreen
import com.nesyou.daily.features.auth.ui.signup.SignupScreen
import com.nesyou.daily.features.auth.ui.splash.SplashScreen
import com.nesyou.daily.features.home.ui.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalAnimationApi
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

@ExperimentalAnimationApi
@Composable
fun Navigation(navController: NavHostController) {
    val initRoute = if (Firebase.auth.currentUser == null) {
        Screen.Splash.route
    } else {
        Screen.Home.route
    }
    NavHost(navController = navController, startDestination = initRoute) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Signup.route) {
            SignupScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}