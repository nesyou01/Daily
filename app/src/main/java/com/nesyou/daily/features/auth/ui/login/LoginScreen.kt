package com.nesyou.daily.features.auth.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nesyou.daily.R
import com.nesyou.daily.features.auth.ui.components.AuthLayout
import com.nesyou.daily.features.auth.ui.components.SocialWithButton
import com.nesyou.daily.features.auth.ui.components.UnderlinedInput

@Composable
fun LoginScreen() {
    AuthLayout {
        Text(
            stringResource(id = R.string.login),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.medium))
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            UnderlinedInput(
                icon = R.drawable.ic_message,
                hint = stringResource(R.string.email_address),
                onChange = {},
                value = "asdasd"
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium)))
            UnderlinedInput(
                icon = R.drawable.ic_lock,
                hint = stringResource(R.string.password),
                isPassword = true,
                value = "sdfsdf",
                onChange = {},
            )
            TextButton(
                modifier = Modifier.align(Alignment.End),
                onClick = { /*TODO*/ },
            ) {
                Text(stringResource(R.string.forgot_password))
            }
            SocialWithButton(
                onClick = {},
                buttonText = stringResource(id = R.string.login)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(R.string.do_not_have_account))
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    stringResource(R.string.sign_up),
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}