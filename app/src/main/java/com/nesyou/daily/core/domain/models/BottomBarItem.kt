package com.nesyou.daily.core.domain.models

import androidx.annotation.DrawableRes

data class BottomBarItem(
    @DrawableRes val icon: Int,
    @DrawableRes val activeIcon: Int? = null,
    val route: String,
    val replaced: Boolean = false
)
