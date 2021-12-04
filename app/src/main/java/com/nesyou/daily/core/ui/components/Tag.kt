package com.nesyou.daily.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tag(
    label: String,
    color: Color = Color(0xFF8F99EB),
) {
    Surface(
        color = color.copy(alpha = .2F),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            label,
            color = color,
            modifier = Modifier.padding(horizontal = 10.dp),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}