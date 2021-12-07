package com.nesyou.daily.features.tasks.ui

import android.text.format.DateFormat
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.gestures.OverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nesyou.daily.R
import com.nesyou.daily.core.domain.utils.Helpers
import com.nesyou.daily.core.ui.components.Calendar
import com.nesyou.daily.core.ui.components.SearchInput
import com.nesyou.daily.core.ui.components.DateHeader
import com.nesyou.daily.core.ui.components.TaskCard
import java.util.*
import kotlin.collections.HashMap


@ExperimentalFoundationApi
@Composable
fun TasksScreen(viewModel: TasksViewModel = hiltViewModel()) {
    // TODO: delete this
    val dataExample = HashMap<String, Array<String>>()
    dataExample["07:00"] = arrayOf("aaaaaaaaa", "aaaa")
    dataExample["08:00"] = arrayOf("bbbbbbbbbb")
    dataExample["09:00"] = arrayOf()
    dataExample["10:00"] = arrayOf("cccccccccc", "asdsad")
    val sortedMap = dataExample.toSortedMap(compareBy { it })
    //******************
    val horizontalPadding = dimensionResource(id = R.dimen.horizontal_padding)
    if (viewModel.showCalendar) {
        Calendar(
            viewModel.activeDate,
            onSave = {
                viewModel.activeDate = it
                viewModel.showCalendar = false
            },
            onDismissRequested = {
                viewModel.showCalendar = false
            },
        )
    }
    CompositionLocalProvider(
        LocalOverScrollConfiguration provides  null
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        stringResource(id = R.string.task),
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.primaryVariant
                    )
                    TextButton(
                        onClick = { viewModel.showCalendar = true },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_calendar),
                            contentDescription = "calendar"
                        )
                        Text(
                            " ${
                                (DateFormat.format(
                                    "MMMM",
                                    viewModel.activeDate
                                ) as String)
                            } ${viewModel.activeDate.get(Calendar.YEAR)}"
                        )
                    }
                }
            }
            stickyHeader {
                DateHeader(date = viewModel.activeDate, onClick = { viewModel.activeDate = it })
            }
            item {
                Row(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(horizontal = horizontalPadding, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        Helpers.dateFormatter(LocalContext.current, viewModel.activeDate),
                        style = MaterialTheme.typography.h3.copy(fontSize = 21.sp),
                        color = MaterialTheme.colors.primaryVariant
                    )
                }
            }
            items(sortedMap.size) {
                Column {
                    Divider(
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier.padding(horizontal = horizontalPadding),
                    )
                    CompositionLocalProvider(
                        LocalOverScrollConfiguration provides OverScrollConfiguration()
                    ) {
                        LazyRow(
                            modifier = Modifier.fillParentMaxWidth(),
                            contentPadding = PaddingValues(horizontal = horizontalPadding),
                        ) {
                            val a = sortedMap.keys.elementAt(it)
                            val item = sortedMap.getValue(a)
                            item {
                                Text(
                                    a,
                                    style = MaterialTheme.typography.h4,
                                    modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                )
                            }
                            if (item.isEmpty()) {
                                item {
                                    Text("Empty", Modifier.padding(vertical = 15.dp))
                                }
                            } else {
                                items(item.size) {

                                    TaskCard(
                                        modifier = Modifier.padding(
                                            vertical = 15.dp,
                                            horizontal = 7.dp
                                        ),
                                        onClick = {}
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}