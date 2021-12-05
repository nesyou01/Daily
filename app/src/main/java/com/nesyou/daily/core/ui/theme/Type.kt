package com.nesyou.daily.core.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nesyou.daily.R

private val HindSiliguriFontFamily = FontFamily(
    Font(R.font.hindsiliguri_medium, FontWeight.Medium),
    Font(R.font.hindsiliguri_regular, FontWeight.Normal),
    Font(R.font.hindsiliguri_semibold, FontWeight.SemiBold)
)
val Typography = Typography(
    defaultFontFamily = HindSiliguriFontFamily,
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.5.sp,
        lineHeight = 19.sp
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    h1 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 35.sp,
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 23.sp,
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
    ),
    h3 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        color = DarkPurple
    )
)