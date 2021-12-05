package com.nesyou.daily.core.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val LightColorPalette = lightColors(
    primary = Purple,
    onBackground = DarkPurple,
    secondary = LightGray,
    onSecondary = DarkGray,
    secondaryVariant = Gray,
    error = Red,
    primaryVariant = DarkerPurple,
    surface = BackGray,
    onSurface = OnBackGray
)

@Composable
fun DailyTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}