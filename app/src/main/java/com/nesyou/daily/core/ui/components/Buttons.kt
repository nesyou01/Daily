package com.nesyou.daily.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nesyou.daily.R

@Composable
fun Buttons(
    save: () -> Unit,
    cancel: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedButton(
            onClick = cancel,
            modifier = Modifier.sizeIn(minWidth = 100.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colors.primary),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
            )
        ) {
            Text(stringResource(R.string.cancel), color = MaterialTheme.colors.primary)
        }
        Spacer(modifier = Modifier.width(15.dp))
        Button(
            onClick = save,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.sizeIn(minWidth = 100.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            )
        ) {
            Text(stringResource(R.string.save))
        }
    }
}