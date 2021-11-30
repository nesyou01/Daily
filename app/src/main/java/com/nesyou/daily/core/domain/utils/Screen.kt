package com.nesyou.daily.core.domain.utils

sealed class Screen(val route: String) {
    object Login : Screen("/login")
    object Splash : Screen("/splash")
}
