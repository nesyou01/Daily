package com.nesyou.daily.features.task.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nesyou.daily.core.ui.theme.Gray100
import com.nesyou.daily.features.auth.domain.models.InputData

@ExperimentalAnimationApi
@Composable
fun TaskInput(
    modifier: Modifier = Modifier,
    label: String? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    data: InputData,
    readOnly: Boolean = false,
    onValueChange: (String) -> Unit = {},
) {
    val focused = remember { mutableStateOf(false) }
    Column(
        modifier = modifier
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent {
                    focused.value = it.isFocused
                }
                ,
            value = data.value,
            onValueChange = onValueChange,
            singleLine = true,
            cursorBrush = SolidColor(MaterialTheme.colors.primary),
            decorationBox = { innerTextField ->
                Box {
                    label?.let {
                        Box(
                            modifier = Modifier.matchParentSize()
                        ) {
                            Text(
                                it,
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .let {
                                        if (focused.value || data.value.isNotBlank()) it.fillMaxHeight() else it.wrapContentHeight()
                                    }
                                    .animateContentSize(),
                                style = MaterialTheme.typography.h3.copy(fontSize = 15.sp),
                                color = Gray100
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .height(TextFieldDefaults.MinHeight)
                            .align(Alignment.CenterStart)
                    ) {

                        Box(Modifier.weight(1f)) {
                            innerTextField()
                        }

                        if (trailingIcon != null) trailingIcon()
                    }
                    Divider(
                        color = Color(0xFFE8E9F3),
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
            },
            readOnly = readOnly,
            enabled = !readOnly,
            textStyle = MaterialTheme.typography.h3.copy(
                fontSize = 16.sp, color = MaterialTheme.colors.primaryVariant
            ),
        )
        AnimatedVisibility(visible = data.error != null) {
            data.error?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}