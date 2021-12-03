package com.nesyou.daily.features.auth.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.nesyou.daily.R
import com.nesyou.daily.features.auth.domain.models.InputData

@ExperimentalAnimationApi
@Composable
fun UnderlinedInput(
    data: InputData,
    onChange: (String) -> Unit,
    hint: String,
    @DrawableRes icon: Int,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Email
) {
    var hide by remember { mutableStateOf(isPassword) }
    Column {
        TextField(
            value = data.value,
            onValueChange = onChange,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                unfocusedIndicatorColor = MaterialTheme.colors.secondary,
            ),
            modifier = Modifier
                .sizeIn(maxWidth = dimensionResource(id = R.dimen.input_max_width))
                .fillMaxWidth(),
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
                            tint = MaterialTheme.colors.secondaryVariant
                        )
                    }
                }
            } else null,
            visualTransformation = if (hide) PasswordVisualTransformation() else VisualTransformation.None,
            singleLine = true,
            isError = data.error != null,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
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