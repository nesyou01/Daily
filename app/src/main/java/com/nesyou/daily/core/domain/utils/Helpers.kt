package com.nesyou.daily.core.domain.utils

import androidx.navigation.NavController
import java.sql.Timestamp

object Helpers {

    fun NavController.navigateReplacement(route: String) {
        this.popBackStack()
        this.navigate(route)
    }

    fun currentTimeStamp(): Timestamp = Timestamp(System.currentTimeMillis())

    fun String.capitalizeWord(): String = this.lowercase().replaceFirstChar { char -> char.titlecase() }

}