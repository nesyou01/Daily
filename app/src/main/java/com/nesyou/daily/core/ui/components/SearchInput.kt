package com.nesyou.daily.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.nesyou.daily.R

@Composable
fun SearchInput(
    modifier: Modifier = Modifier
) {

    TextField(
        value = "",
        onValueChange = {},
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .sizeIn(maxWidth = dimensionResource(id = R.dimen.input_max_width))
            .fillMaxWidth()
            .then(modifier),
        shape = MaterialTheme.shapes.medium,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "search",
                tint = MaterialTheme.colors.secondaryVariant
            )
        },
        placeholder = {
            Text(stringResource(R.string.search_for_task), color = MaterialTheme.colors.onSurface)
        }
    )

}