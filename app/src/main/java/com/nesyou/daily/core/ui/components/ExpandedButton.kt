package com.nesyou.daily.core.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.nesyou.daily.R

@Composable
fun ExpandedButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .sizeIn(maxWidth = dimensionResource(id = R.dimen.max_width))
            .fillMaxWidth()
            .then(modifier),
        contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.medium)),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text, style = MaterialTheme.typography.h4)
    }
}