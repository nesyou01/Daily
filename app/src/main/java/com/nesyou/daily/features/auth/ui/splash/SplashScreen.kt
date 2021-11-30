package com.nesyou.daily.features.auth.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.nesyou.daily.R
import com.nesyou.daily.core.domain.utils.Screen
import com.nesyou.daily.core.ui.components.ExpandedButton
import com.nesyou.daily.core.ui.components.PrimaryTextButton
import com.nesyou.daily.features.auth.ui.components.AuthLayout

@Composable
fun SplashScreen(navController: NavController) {
    AuthLayout(
        alignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "splash img",
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium)),
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.medium))
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "logo"
            )
            Text(
                stringResource(R.string.plan_what_you_will_do),
                textAlign = TextAlign.Center,
                modifier = Modifier.sizeIn(maxWidth = dimensionResource(id = R.dimen.text_max_width))
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium)),
        ) {
            ExpandedButton(
                text = stringResource(R.string.login),
                onClick = {
                    navController.navigate(Screen.Login.route)
                }
            )
            PrimaryTextButton(
                text = stringResource(R.string.sign_up),
                onClick = {
                    navController.navigate(Screen.Signup.route)
                },
            )
        }
    }
}