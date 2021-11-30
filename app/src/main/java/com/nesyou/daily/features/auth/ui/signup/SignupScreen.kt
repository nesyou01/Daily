package com.nesyou.daily.features.auth.ui.signup

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.nesyou.daily.R
import com.nesyou.daily.core.domain.utils.Helpers.navigateReplacement
import com.nesyou.daily.core.domain.utils.Screen
import com.nesyou.daily.core.ui.components.LoadingDialog
import com.nesyou.daily.features.auth.ui.components.AuthLayout
import com.nesyou.daily.features.auth.ui.components.SocialWithButton
import com.nesyou.daily.features.auth.ui.components.UnderlinedInput

@Composable
fun SignupScreen(navController: NavController) {
    LoadingDialog()
    AuthLayout {
        Text(
            stringResource(id = R.string.sign_up),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.medium))
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.large)),
        ) {
            UnderlinedInput(
                icon = R.drawable.ic_profile,
                hint = stringResource(R.string.username),
                onChange = {},
                value = "asdasd"
            )
            UnderlinedInput(
                icon = R.drawable.ic_message,
                hint = stringResource(R.string.email_address),
                onChange = {},
                value = "asdasd"
            )
            UnderlinedInput(
                icon = R.drawable.ic_lock,
                hint = stringResource(R.string.password),
                isPassword = true,
                value = "sdfsdf",
                onChange = {},
            )
            SocialWithButton(
                onClick = {},
                buttonText = stringResource(id = R.string.sign_up)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(R.string.have_any_account))
            TextButton(
                onClick = {
                    navController.navigateReplacement(Screen.Login.route)
                },
            ) {
                Text(
                    stringResource(R.string.sign_in),
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}