package com.nesyou.daily.core.ui.components

import android.widget.TimePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nesyou.daily.R
import com.nesyou.daily.core.ui.theme.LightBlack
import java.util.*


@ExperimentalComposeUiApi
@Composable
fun TimePickerDialog(value: Calendar, onSave: (Calendar) -> Unit, onCancel: () -> Unit = {}) {

    val cal = Calendar.getInstance()

    Dialog(onDismissRequest = {}, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            contentColor = LightBlack,
        ) {
            Column(
                modifier = Modifier
                    .sizeIn(
                        maxHeight = dimensionResource(id = R.dimen.dialog_max_height),
                        minWidth = 320.dp
                    )
                    .width(IntrinsicSize.Min)
                    .background(MaterialTheme.colors.background)
                    .wrapContentSize()
                    .padding(bottom = 15.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                AndroidView(
                    factory = { ctx ->
                        TimePicker(ctx).apply {
                            setIs24HourView(true)
                            setOnTimeChangedListener { _, hourOfDay, minute ->
                                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                                cal.set(Calendar.MINUTE, minute)
                            }
                        }
                    },
                    update = {
                        it.hour = value[Calendar.HOUR_OF_DAY]
                        it.minute = value[Calendar.MINUTE]
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Buttons(
                    save = {
                        onSave(cal)
                    },
                    cancel = onCancel,
                )
            }
        }
    }
}