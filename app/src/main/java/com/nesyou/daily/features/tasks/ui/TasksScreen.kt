package com.nesyou.daily.features.tasks.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nesyou.daily.R
import com.nesyou.daily.core.ui.components.SearchInput
import java.util.*

@ExperimentalFoundationApi
@Composable
fun TasksScreen() {
    val calendar = Calendar.getInstance()
    val horizontalPadding = dimensionResource(id = R.dimen.horizontal_padding)
    LazyColumn {
        item {
            SearchInput(
                modifier = Modifier.padding(horizontal = horizontalPadding, vertical = 15.dp)
            )
        }
        item {
            Row(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(horizontal = horizontalPadding, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    stringResource(id = R.string.task),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.primaryVariant
                )
            }
        }
        stickyHeader {

        }
    }
}