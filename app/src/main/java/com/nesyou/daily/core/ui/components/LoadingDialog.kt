package com.nesyou.daily.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun LoadingDialog() {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Surface(

            shape = MaterialTheme.shapes.medium,
            color = Color.White
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(17.dp),
            )
        }
    }
}