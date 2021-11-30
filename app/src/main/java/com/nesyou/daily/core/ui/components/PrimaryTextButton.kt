package com.nesyou.daily.core.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun PrimaryTextButton(
    onClick: () -> Unit,
    text: String
) {
    TextButton(onClick =onClick) {
        Text(text, style = MaterialTheme.typography.h4)
    }
}