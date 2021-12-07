package com.nesyou.daily.core.ui.components

import android.text.format.DateFormat
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.nesyou.daily.R
import com.nesyou.daily.core.ui.theme.LightBlack
import com.nesyou.daily.core.ui.theme.LighterBlack
import java.time.DayOfWeek
import java.util.*

@ExperimentalFoundationApi
@Composable
fun Calendar(date: Calendar, onDismissRequested: () -> Unit = {}, onSave: (Calendar) -> Unit) {

    val firstDayInSelectedDate = Calendar.getInstance()
    var selectedTime by remember { mutableStateOf(date) }
    val selectedDay = selectedTime.get(Calendar.DAY_OF_MONTH)
    val days = DayOfWeek.values().map { it.name }
    val a = Calendar.getInstance()
    var monthStart by remember { mutableStateOf(0) }
    val daysInMonth = selectedTime.getActualMaximum(Calendar.DAY_OF_MONTH)

    SideEffect {
        firstDayInSelectedDate.set(
            Calendar.DAY_OF_MONTH,
            selectedTime.getActualMinimum(Calendar.DAY_OF_MONTH)
        )
        firstDayInSelectedDate.set(
            Calendar.MONTH,
            selectedTime.get(Calendar.MONTH)
        )
        firstDayInSelectedDate.set(
            Calendar.YEAR,
            selectedTime.get(Calendar.YEAR)
        )

        monthStart = blocksSize(firstDayInSelectedDate)!!
    }
    fun sync() {
        a.let {
            it.set(Calendar.MONTH, selectedTime.get(Calendar.MONTH))
            it.set(Calendar.YEAR, selectedTime.get(Calendar.YEAR))
        }
        selectedTime = a
    }
    Dialog(onDismissRequest = onDismissRequested) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            contentColor = LightBlack
        ) {
            Column(
                modifier = Modifier
                    .sizeIn(maxHeight = 450.dp)
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        DateFormat.format(
                            "MMMM yyyy",
                            selectedTime
                        ) as String,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Row {
                        IconButton(
                            onClick = {
                                sync()
                                a.add(Calendar.MONTH, -1)
                            },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_previous),
                                contentDescription = "previous",
                            )
                        }
                        IconButton(
                            onClick = {
                                sync()
                                a.add(Calendar.MONTH, +1)
                            },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_next),
                                contentDescription = "next",
                            )
                        }
                    }
                }
                LazyVerticalGrid(
                    cells = GridCells.Fixed(7),
                    contentPadding = PaddingValues(horizontal = 30.dp),
                    modifier = Modifier
                        .animateContentSize()
                ) {
                    items(days.size) { i ->
                        Text(
                            days[i].substring(0, 2).lowercase()
                                .replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.h3.copy(
                                fontSize = 17.sp,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier
                                .padding(bottom = 5.dp)
                        )
                    }
                    items(arrayOfNulls<Any?>(monthStart).size) {
                        Text("")
                    }
                    items(daysInMonth) { i ->
                        val day = i + 1
                        val active = selectedDay == day
                        Surface(
                            modifier = Modifier
                                .aspectRatio(1F)
                                .clip(CircleShape)
                                .clickable {
                                    a.set(Calendar.DAY_OF_MONTH, day)
                                    sync()
                                },
                            color = if (active) MaterialTheme.colors.primary else Color.Transparent,
                            shape = CircleShape
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "$day",
                                    color = if (active) MaterialTheme.colors.background else LighterBlack,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
                Buttons(
                    cancel = onDismissRequested,
                    save = {
                        onSave(selectedTime)
                    }
                )
            }
        }
    }
}

private fun blocksSize(calendar: Calendar): Int? {
    return when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> 0
        Calendar.TUESDAY -> 1
        Calendar.WEDNESDAY -> 2
        Calendar.THURSDAY -> 3
        Calendar.FRIDAY -> 4
        Calendar.SATURDAY -> 5
        Calendar.SUNDAY -> 6
        else -> null
    }
}