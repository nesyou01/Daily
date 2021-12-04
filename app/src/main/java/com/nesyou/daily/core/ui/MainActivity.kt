package com.nesyou.daily.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nesyou.daily.R
import com.nesyou.daily.core.domain.models.BottomBarItem
import com.nesyou.daily.core.domain.utils.Screen
import com.nesyou.daily.core.ui.theme.DailyTheme
import com.nesyou.daily.features.auth.ui.login.LoginScreen
import com.nesyou.daily.features.auth.ui.signup.SignupScreen
import com.nesyou.daily.features.auth.ui.splash.SplashScreen
import com.nesyou.daily.features.graphic.ui.GraphicScreen
import com.nesyou.daily.features.home.ui.HomeScreen
import com.nesyou.daily.features.profile.ui.ProfileScreen
import com.nesyou.daily.features.tasks.ui.TasksScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomBar(navController = navController)
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Navigation(navController = navController)
                    }
                }

            }

        }
    }
}


@ExperimentalAnimationApi
@Composable
private fun Navigation(navController: NavHostController) {
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
        composable(Screen.Tasks.route) {
            TasksScreen()
        }
        composable(Screen.Graphic.route) {
            GraphicScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}

@Composable
private fun BottomBar(navController: NavController) {
    val bottomBarItems = arrayOf(
        BottomBarItem(
            route = Screen.Home.route,
            icon = R.drawable.ic_home,
            activeIcon = R.drawable.ic_home_filled
        ),
        BottomBarItem(
            route = Screen.Tasks.route,
            icon = R.drawable.ic_document,
            activeIcon = R.drawable.ic_document_filled
        ),
        BottomBarItem(
            route = Screen.Login.route,
            icon = R.drawable.ic_add,
            replaced = true
        ),
        BottomBarItem(
            route = Screen.Graphic.route,
            icon = R.drawable.ic_activity,
            activeIcon = R.drawable.ic_activity_filled
        ),
        BottomBarItem(
            route = Screen.Profile.route,
            icon = R.drawable.ic_folder,
            activeIcon = R.drawable.ic_folder_filled
        ),
    )

    val currentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.route
    if (currentRoute in bottomBarItems.filter { !it.replaced }
            .map { it.route }) {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                bottomBarItems.map {
                    val active = currentRoute == it.route
                    IconButton(
                        onClick = {
                            navController.navigate(it.route) {
                                launchSingleTop = true
                            }
                        },
                        modifier = if (it.replaced) Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.primary) else Modifier.weight(
                            1F
                        ),
                        enabled = !active
                    ) {
                        Image(
                            painter = painterResource(id = if (!active || it.activeIcon == null) it.icon else it.activeIcon),
                            contentDescription = null,
                        )
                        AnimatedVisibility(visible = active, enter = fadeIn(), exit = fadeOut()) {
                            Box(
                                contentAlignment = Alignment.BottomCenter,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(bottom = 4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(5.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colors.primary),
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}