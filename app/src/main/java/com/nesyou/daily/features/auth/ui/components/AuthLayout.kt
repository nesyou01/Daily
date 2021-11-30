package com.nesyou.daily.features.auth.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.nesyou.daily.R

@Composable
fun AuthLayout(
    alignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = dimensionResource(id = R.dimen.horizontal_padding),
                vertical = dimensionResource(id = R.dimen.medium)
            ),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = alignment,
        content = content
    )
}