package com.nesyou.daily.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nesyou.daily.R

@Composable
fun TopBar(
    title: String,
    onBackRequested: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentPadding = PaddingValues(horizontal = 15.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            IconButton(onClick = onBackRequested, modifier = Modifier.align(Alignment.CenterStart)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "back",
                    tint = MaterialTheme.colors.primaryVariant
                )
            }
            Text(
                title,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primaryVariant
            )
        }
    }
}