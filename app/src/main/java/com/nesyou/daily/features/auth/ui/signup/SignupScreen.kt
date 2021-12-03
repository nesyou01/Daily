package com.nesyou.daily.features.auth.ui.signup

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
fun SignupScreen(navController: NavController, viewModel: SignupViewModel = hiltViewModel()) {
    ResourceLayout(
        res = viewModel.status,
        onDismissRequest = {
            viewModel.status = Resource.reset()
        },
    ) {
        AuthLayout {
            Text(
                stringResource(id = R.string.sign_up),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.medium))
            )
            Column {
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .sizeIn(maxWidth = dimensionResource(id = R.dimen.input_max_width))
                        .align(
                            Alignment.CenterHorizontally
                        )
                ) {
                    UnderlinedInput(
                        icon = R.drawable.ic_profile,
                        hint = stringResource(R.string.full_name),
                        onChange = { v ->
                            viewModel.let {
                                it.name = it.name.copy(value = v)
                                if (it.name.error != null) {
                                    viewModel.validate()
                                }
                            }
                        },
                        data = viewModel.name,
                        keyboardType = KeyboardType.Text
                    )
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
                }
                SocialWithButton(
                    onClick = {
                        viewModel(navController)
                    },
                    buttonText = stringResource(id = R.string.sign_up),
                    navController = navController,
                    onLoginError = {
                        viewModel.status = Resource.error(it)
                    },
                    modifier = Modifier.padding(vertical = 25.dp)
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
}
