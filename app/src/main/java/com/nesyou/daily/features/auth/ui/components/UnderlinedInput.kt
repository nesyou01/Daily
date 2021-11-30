package com.nesyou.daily.features.auth.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.nesyou.daily.R

@Composable
fun UnderlinedInput(
    value: String,
    onChange: (String) -> Unit,
    hint: String,
    @DrawableRes icon: Int,
    isPassword: Boolean = false
) {
    var hide by remember { mutableStateOf(isPassword) }
    TextField(
        value = value,
        onValueChange = onChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            unfocusedIndicatorColor = MaterialTheme.colors.secondary,
        ),
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = MaterialTheme.colors.onSecondary
            )
        },
        placeholder = {
            Text(
                hint,
                color = MaterialTheme.colors.secondaryVariant
            )
        },
        trailingIcon = if (isPassword) {
            {
                IconButton(
                    onClick = {
                        hide = !hide
                    },
                ) {
                    Icon(
                        painter = painterResource(id = if (hide) R.drawable.ic_hide else R.drawable.ic_show),
                        contentDescription = "show/hide",
                        modifier = Modifier.size(18.dp),
                        tint =  MaterialTheme.colors.secondaryVariant
                    )
                }
            }
        } else null,
        visualTransformation = if (hide) PasswordVisualTransformation() else VisualTransformation.None
    )
}