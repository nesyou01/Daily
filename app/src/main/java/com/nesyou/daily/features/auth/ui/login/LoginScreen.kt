package com.nesyou.daily.features.auth.ui.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nesyou.daily.R
import com.nesyou.daily.core.domain.models.Resource
import com.nesyou.daily.core.domain.utils.Helpers.navigateReplacement
import com.nesyou.daily.core.domain.utils.Screen
import com.nesyou.daily.features.auth.ui.components.AuthLayout
import com.nesyou.daily.features.auth.ui.components.ResourceLayout
import com.nesyou.daily.features.auth.ui.components.SocialWithButton
import com.nesyou.daily.features.auth.ui.components.UnderlinedInput

@ExperimentalAnimationApi
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    ResourceLayout(
        res = viewModel.res,
        onDismissRequest = {
            viewModel.res = Resource.reset()
        },
    ) {
        AuthLayout {
            Text(
                stringResource(id = R.string.login),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.medium))
            )
            Column {
                Column(
                    modifier = Modifier
                        .sizeIn(maxWidth = dimensionResource(id = R.dimen.input_max_width))
                        .align(
                            Alignment.CenterHorizontally
                        )
                ) {
                    UnderlinedInput(
                        icon = R.drawable.ic_message,
                        hint = stringResource(R.string.email_address),
                        onChange = { v ->
                            viewModel.let {
                                it.email = it.email.copy(value = v)
                                if (it.email.error != null) {
                                    viewModel.validate()
                                }
                            }
                        },
                        data = viewModel.email
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium)))
                    UnderlinedInput(
                        icon = R.drawable.ic_lock,
                        hint = stringResource(R.string.password),
                        isPassword = true,
                        onChange = { v ->
                            viewModel.let {
                                it.password = it.password.copy(value = v)
                                if (it.password.error != null) {
                                    viewModel.validate()
                                }
                            }
                        },
                        data = viewModel.password,
                        keyboardType = KeyboardType.Password
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    TextButton(
                        modifier = Modifier.align(Alignment.End),
                        onClick = { /*TODO*/ },
                    ) {
                        Text(stringResource(R.string.forgot_password))
                    }
                }

                SocialWithButton(
                    onClick = {
                        viewModel(navController)
                    },
                    buttonText = stringResource(id = R.string.login),
                    onLoginError = {
                        viewModel.res = Resource.error(it)
                    },
                    navController = navController,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(R.string.do_not_have_account))
                TextButton(
                    onClick = {
                        navController.navigateReplacement(Screen.Signup.route)
                    },
                ) {
                    Text(
                        stringResource(R.string.sign_up),
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}