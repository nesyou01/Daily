package com.nesyou.daily.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import com.nesyou.daily.core.domain.models.DialogStatus
import com.nesyou.daily.R

@Composable
fun MainDialog(
    title: String = "Error",
    text: String?,
    onDismissRequest: () -> Unit,
    onSuccess: () -> Unit = {},
    status: DialogStatus = DialogStatus.FAIL
) {

    val icon = when (status) {
        DialogStatus.FAIL -> R.drawable.ic_fail
        DialogStatus.SUCCESS -> R.drawable.ic_success
        DialogStatus.WARNING -> R.drawable.ic_warning
    }

    val bubblesColor = when (status) {
        DialogStatus.FAIL -> Color(0xFF801336)
        DialogStatus.SUCCESS -> Color(0xFF005E38)
        DialogStatus.WARNING -> Color(0xFFCC561E)
    }

    val backColor = when (status) {
        DialogStatus.FAIL -> Color(0xFFC72C41)
        DialogStatus.SUCCESS -> Color(0xFF03A65A)
        DialogStatus.WARNING -> Color(0xFFF88F01)
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .size(65.dp)
                    .offset(18.dp, 18.dp)
                    .zIndex(2F)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(backColor),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_red_bubbles),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 18.dp)
                        .align(Alignment.Bottom),
                    tint = bubblesColor
                )
                Column(
                    modifier = Modifier.weight(1F)
                ) {
                    Text(text = title, style = MaterialTheme.typography.h1, color = Color.White)
                    Text(
                        text = text ?: stringResource(R.string.unexpected_error),
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )
                }
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = onDismissRequest) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_round_close_24),
                            contentDescription = "Close",
                            tint = Color.White
                        )
                    }
                    if (status != DialogStatus.FAIL) {
                        IconButton(onClick = onSuccess) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_round_check_24),
                                contentDescription = "Check",
                                tint = Color.White
                            )
                        }
                    } else {
                        Text("")
                    }
                }
            }
        }
    }
}