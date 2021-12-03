package com.nesyou.daily.features.auth.ui.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultRegistryOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.facebook.*
import com.facebook.login.LoginManager
import com.nesyou.daily.R
import com.nesyou.daily.core.ui.components.ExpandedButton
import com.facebook.login.LoginResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import com.nesyou.daily.BuildConfig
import com.nesyou.daily.features.auth.domain.utils.AuthResultContract
import com.nesyou.daily.features.auth.ui.SocialLoginViewModel


@Composable
fun SocialWithButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonText: String,
    navController: NavController,
    onLoginError: (Exception) -> Unit = {},
    viewModel: SocialLoginViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val signInRequestCode = 1
    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account == null) {
                    onLoginError(Exception("Google sign in failed"))
                } else {
                    viewModel(
                        GoogleAuthProvider.getCredential(account.idToken, null),
                        navController
                    )
                }
            } catch (e: ApiException) {
                onLoginError(e)
            }
        }
    if (BuildConfig.DEBUG) {
        FacebookSdk.setIsDebugEnabled(true)
        FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)
    }

    Column(
        modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.medium)).then(modifier),
        verticalArrangement = Arrangement.spacedBy(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExpandedButton(
            text = buttonText,
            onClick = onClick,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.weight(1F)
            )
            Text(
                stringResource(R.string.or_with), style = TextStyle(
                    fontSize = 13.sp,
                    color = MaterialTheme.colors.secondaryVariant
                ),
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.medium))
            )
            Divider(
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.weight(1F)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            OutlinedIconButton(
                onClick = {
                    authResultLauncher.launch(signInRequestCode)
                },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "google"
                )
            }
            OutlinedIconButton(
                onClick = {
                    val callbackManager = CallbackManager.Factory.create()
                    val loginManager = LoginManager.getInstance()
                    loginManager.logIn(
                        context as ActivityResultRegistryOwner,
                        callbackManager,
                        listOf("email")
                    )
                    loginManager.registerCallback(
                        callbackManager,
                        object : FacebookCallback<LoginResult?> {
                            override fun onCancel() {
                                return
                            }

                            override fun onError(error: FacebookException) {
                                onLoginError(error)
                            }

                            override fun onSuccess(result: LoginResult?) {
                                try {
                                    result?.let {
                                        viewModel(
                                            FacebookAuthProvider.getCredential(it.accessToken.token),
                                            navController
                                        )
                                    }
                                } catch (e: Exception) {
                                    onLoginError(e)
                                }
                            }
                        },
                    )
                },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "facebook"
                )
            }
        }
    }
}
