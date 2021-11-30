package com.nesyou.daily.core.domain.utils

import androidx.navigation.NavController

object Helpers {

    fun NavController.navigateReplacement(route: String) {
        this.popBackStack()
        this.navigate(route)
    }

}