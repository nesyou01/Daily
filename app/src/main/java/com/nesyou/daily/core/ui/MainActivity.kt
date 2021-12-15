package com.nesyou.daily.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
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
import com.nesyou.daily.features.task.ui.TasksScreen
import com.nesyou.daily.features.task.ui.add_task.AddTaskScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomBar(navController = navController)
                    },
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Navigation(navController = navController)
                    }
                }

            }
        }
    }
}


@ExperimentalComposeUiApi
@ExperimentalFoundationApi
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
        composable(Screen.AddTask.route) {
            AddTaskScreen(navController)
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
    AnimatedVisibility(
        currentRoute in bottomBarItems.map { it.route },
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier.height(65.dp),
            elevation = 5.dp
        ) {
            bottomBarItems.mapIndexed { i, item ->
                val active = currentRoute == item.route
                BottomNavigationItem(
                    selected = active,
                    onClick = {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = if (active) 15.dp else 0.dp)
                                .animateContentSize(
                                    animationSpec = tween(
                                        200,
                                        easing = LinearEasing
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = if (!active) item.icon else item.activeIcon),
                                contentDescription = null,
                            )
                            if (active)
                                Box(
                                    modifier = Modifier
                                        .size(5.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colors.primary)
                                        .align(Alignment.BottomCenter),
                                )
                        }
                    },
                )
                if (i + 1 == bottomBarItems.size / 2) {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(Screen.AddTask.route)
                        },
                        shape = CircleShape,
                        backgroundColor = MaterialTheme.colors.primary,
                        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp),
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .align(Alignment.CenterVertically),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}