package com.nesyou.daily.features.task.ui.add_task

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nesyou.daily.R
import com.nesyou.daily.core.domain.models.DialogStatus
import com.nesyou.daily.core.domain.models.Resource
import com.nesyou.daily.core.domain.models.Status
import com.nesyou.daily.core.domain.utils.Helpers.addOrRemove
import com.nesyou.daily.core.domain.utils.Helpers.refresh
import com.nesyou.daily.core.ui.components.*
import com.nesyou.daily.core.ui.components.Modifiers.mutedClickable
import com.nesyou.daily.core.ui.theme.Gray100
import com.nesyou.daily.features.auth.ui.components.ResourceLayout
import com.nesyou.daily.features.task.domain.models.TaskTag
import com.nesyou.daily.features.task.domain.models.TaskType
import com.nesyou.daily.features.task.domain.state.TimePickerState
import com.nesyou.daily.features.task.ui.components.Tag
import com.nesyou.daily.features.task.ui.components.TaskInput

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun AddTaskScreen(navController: NavController, viewModel: AddTaskViewModel = hiltViewModel()) {
    if (viewModel.res.status == Status.SUCCESS) {
        MainDialog(
            text = stringResource(R.string.the_task_successfully),
            onDismissRequest = {
                navController.refresh()
            },
            onSuccess = {
                navController.popBackStack()
            },
            status = DialogStatus.SUCCESS,
            title = stringResource(R.string.success)
        )
    }
    Dialogs(viewModel = viewModel)
    ResourceLayout(res = viewModel.res, onDismissRequest = { viewModel.res = Resource.reset() }) {
        Scaffold(
            topBar = {
                TopBar(
                    title = stringResource(R.string.add_task),
                    onBackRequested = { navController.popBackStack() })
            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = dimensionResource(id = R.dimen.horizontal_padding))
                    .padding(bottom = dimensionResource(id = R.dimen.medium), top = 5.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    SideEffect {
                        if (!viewModel.isValidated()) {
                            viewModel.validate()
                        }
                    }
                    TaskInput(
                        label = stringResource(R.string.title),
                        data = viewModel.title,
                        onValueChange = {
                            viewModel.title = viewModel.title.copy(value = it)
                        }
                    )
                    TaskInput(
                        label = stringResource(R.string.date),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    viewModel.showDateDialog = true
                                },
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_calendar),
                                    contentDescription = "calendar",
                                    tint = Gray100
                                )
                            }
                        },
                        data = viewModel.date,
                        readOnly = true,
                        modifier = Modifier.mutedClickable {
                            viewModel.showDateDialog = true
                        },
                    )
                    Row {
                        TaskInput(
                            data = viewModel.timeFrom,
                            modifier = Modifier
                                .weight(1F)
                                .mutedClickable {
                                    viewModel.timePickerState = TimePickerState.FROM
                                },
                            label = stringResource(R.string.time),
                            readOnly = true,
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        TaskInput(
                            data = viewModel.timeUntil,
                            modifier = Modifier
                                .weight(1F)
                                .mutedClickable {
                                    viewModel.timePickerState = TimePickerState.TO
                                },
                            readOnly = true
                        )
                    }
                    TaskInput(
                        label = stringResource(R.string.description),
                        data = viewModel.description,
                        onValueChange = {
                            viewModel.description = viewModel.description.copy(value = it)
                        }
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.normal))
                    ) {
                        Text(
                            stringResource(R.string.type),
                            style = MaterialTheme.typography.h3.copy(fontSize = 15.sp),
                            color = Gray100
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(35.dp)
                        ) {
                            repeat(TaskType.values().size) { i ->
                                val task = TaskType.values()[i]
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    RadioButton(
                                        selected = task == viewModel.type,
                                        onClick = { viewModel.type = task },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = MaterialTheme.colors.primary
                                        ),
                                        modifier = Modifier.scale(.8F)
                                    )
                                    Text(
                                        task.name.lowercase().replaceFirstChar { it.uppercase() },
                                        style = MaterialTheme.typography.h3.copy(fontSize = 15.sp),
                                        color = MaterialTheme.colors.primaryVariant
                                    )
                                }
                            }
                        }
                        Column {
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.normal)))
                            Text(
                                stringResource(R.string.tags),
                                style = MaterialTheme.typography.h3.copy(fontSize = 15.sp),
                                color = Gray100
                            )
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium)))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium)),
                                modifier = Modifier
                                    .sizeIn(maxWidth = dimensionResource(id = R.dimen.text_max_width))
                                    .align(
                                        Alignment.CenterHorizontally
                                    )
                            ) {
                                TaskTag.values.map { item ->
                                    val selected = viewModel.tags.any { it == item }
                                    Tag(
                                        tag = item,
                                        selected = selected,
                                        onClick = {
                                            viewModel.tags.addOrRemove(item)
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
                ExpandedButton(
                    onClick = { viewModel() },
                    text = stringResource(R.string.create),
                    modifier = Modifier.padding(
                        top = 40.dp,
                    )
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
private fun Dialogs(viewModel: AddTaskViewModel) {
    if (viewModel.timePickerState != TimePickerState.CLOSED) {
        TimePickerDialog(
            viewModel.dateCalendar,
            onSave = {
                viewModel.updateTime(it)
            },
            onCancel = {
                viewModel.timePickerState = TimePickerState.CLOSED
            }
        )
    }
    if (viewModel.showDateDialog) {
        Calendar(
            date = viewModel.dateCalendar,
            onSave = {
                viewModel.updateDate(it)
            },
            onDismissRequested = {
                viewModel.showDateDialog = false
            }
        )
    }
}