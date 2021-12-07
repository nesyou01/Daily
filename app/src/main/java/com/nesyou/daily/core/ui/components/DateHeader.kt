package com.nesyou.daily.core.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.gestures.OverScrollConfiguration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nesyou.daily.R
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalFoundationApi
@Composable
fun DateHeader(date: Calendar, onClick: (Calendar) -> Unit) {

    val scope = rememberCoroutineScope()
    val state = rememberLazyListState(
        initialFirstVisibleItemIndex = date.get(Calendar.DAY_OF_MONTH) - 1
    )
    SideEffect {
        scope.launch {
            state.animateScrollToItem(date.get(Calendar.DAY_OF_MONTH) - 2)
        }
    }
    CompositionLocalProvider(
        LocalOverScrollConfiguration provides OverScrollConfiguration()
    ) {
        LazyRow(
            state = state,
            modifier = Modifier.background(MaterialTheme.colors.background),
            contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.horizontal_padding))
        ) {
            items(date.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                val cal = Calendar.getInstance()
                cal.set(Calendar.DAY_OF_MONTH, it + 1)
                cal.set(Calendar.MONTH, date.get(Calendar.MONTH))
                cal.set(Calendar.YEAR, date.get(Calendar.YEAR))
                val day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)!!
                val isActive =
                    it + 1 == date.get(Calendar.DAY_OF_MONTH)
                Surface(
                    contentColor = if (!isActive) MaterialTheme.colors.primaryVariant else Color.White,
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .clickable(
                            onClick = {
                                onClick(cal)
                            }
                        ),
                    color = if (isActive) MaterialTheme.colors.primary else Color.Transparent
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(60.dp)
                            .padding(vertical = 15.dp, horizontal = 5.dp),
                    ) {
                        Text(
                            day.substring(0, 2).uppercase(),
                            style = MaterialTheme.typography.h3.copy(fontSize = 19.sp)
                        )
                        Text(
                            "${cal.get(Calendar.DAY_OF_MONTH)}".padStart(2, '0'),
                        )
                    }
                }
            }
        }
    }
}