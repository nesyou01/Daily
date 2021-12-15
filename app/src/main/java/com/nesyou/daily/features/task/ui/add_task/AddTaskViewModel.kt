package com.nesyou.daily.features.task.ui.add_task

import android.app.Application
import android.text.format.DateFormat
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.nesyou.daily.R
import com.nesyou.daily.core.domain.models.Resource
import com.nesyou.daily.core.domain.models.Task
import com.nesyou.daily.core.domain.utils.Helpers.toTimeStamp
import com.nesyou.daily.core.domain.utils.validate
import com.nesyou.daily.features.auth.domain.models.InputData
import com.nesyou.daily.features.task.data.TaskRepository
import com.nesyou.daily.features.task.domain.models.TaskTag
import com.nesyou.daily.features.task.domain.models.TaskType
import com.nesyou.daily.features.task.domain.state.TimePickerState
import com.nesyou.daily.features.task.domain.utils.TaskConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val app: Application,
    private val auth: FirebaseAuth,
    private val repo: TaskRepository
) :
    ViewModel() {
    var res by mutableStateOf(Resource<Any>())

    var dateCalendar: Calendar by mutableStateOf(Calendar.getInstance())
    private var fromCalendar: Calendar = Calendar.getInstance()
    private var untilCalendar: Calendar = Calendar.getInstance()

    var showDateDialog by mutableStateOf(false)
    var timePickerState by mutableStateOf(TimePickerState.CLOSED)

    var title by mutableStateOf(InputData())
    var date by mutableStateOf(InputData())
    var timeFrom by mutableStateOf(InputData())
    var timeUntil by mutableStateOf(InputData())
    var description by mutableStateOf(InputData())
    var type by mutableStateOf(TaskType.PERSONAL)
    var tags = mutableStateListOf<TaskTag>()

    fun isValidated(): Boolean =
        title.error == null && date.error == null && timeFrom.error == null && timeUntil.error == null && description.error == null

    operator fun invoke() {
        validate()
        if (isValidated()) {
            fromCalendar.syncDate()
            untilCalendar.syncDate()
            res = try {
                res = Resource.loading()
                viewModelScope.launch(Dispatchers.IO) {
                    repo.insert(
                        Task(
                            title = title.value,
                            date = dateCalendar.toTimeStamp(),
                            creatorId = auth.currentUser?.uid!!,
                            description = description.value,
                            tags = tags,
                            type = type,
                            from = fromCalendar.toTimeStamp(),
                            until = untilCalendar.toTimeStamp()
                        )
                    )
                }
                Resource.success()
            } catch (e: Exception) {
                Resource.error(e)
            }
        }
    }

    fun validate() {
        val c = Calendar.getInstance()
        c.add(Calendar.MINUTE, -2)
        title = title.copy(
            error = title.value.validate(app.getString(R.string.title)).required()
                .min(TaskConstants.TITLE_MIN_LENGTH).build()
        )
        date = date.copy(
            error = if (dateCalendar.after(c) || date.value.isBlank()) date.value.validate(
                app.getString(
                    R.string.date
                )
            ).required().build() else app.getString(
                R.string.task_at_past
            )
        )
        timeFrom = timeFrom.copy(
            error = timeFrom.value.validate(app.getString(R.string.time)).required().build()
        )
        timeUntil = timeUntil.copy(
            error = timeUntil.value.validate(app.getString(R.string.finish_time)).required().build()
        )
        description = description.copy(
            error = description.value.validate(app.getString(R.string.description)).required()
                .min(TaskConstants.DESCRIPTION_MIN_LENGTH).build()
        )
    }

    fun updateTime(cal: Calendar) {
        val time = DateFormat.format(
            "HH:mm  'PM'",
            cal
        ) as String
        if (timePickerState == TimePickerState.FROM) {
            fromCalendar = cal
            timeFrom = timeFrom.copy(value = time)
        } else {
            untilCalendar = cal
            timeUntil = timeFrom.copy(value = time)
        }
        timePickerState = TimePickerState.CLOSED
    }

    fun updateDate(cal: Calendar) {
        dateCalendar = cal
        date = date.copy(
            value = DateFormat.format(
                "dd MMMM yyyy",
                dateCalendar
            ) as String
        )
    }

    private fun Calendar.syncDate() {
        this[Calendar.DAY_OF_MONTH] = dateCalendar[Calendar.DAY_OF_MONTH]
        this[Calendar.YEAR] = dateCalendar[Calendar.YEAR]
        this[Calendar.MONTH] = dateCalendar[Calendar.MONTH]
    }
}