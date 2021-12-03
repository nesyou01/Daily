package com.nesyou.daily.features.auth.ui.login

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.nesyou.daily.core.domain.models.Resource
import com.nesyou.daily.core.domain.utils.validate
import com.nesyou.daily.features.auth.domain.models.InputData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject
import android.widget.Toast

import com.nesyou.daily.core.ui.MainActivity

import com.google.firebase.auth.FirebaseUser

import com.google.firebase.auth.AuthResult

import androidx.annotation.NonNull
import com.facebook.CallbackManager

import com.google.android.gms.tasks.OnCompleteListener

import com.google.firebase.auth.AuthCredential
import com.nesyou.daily.features.auth.domain.utils.AuthConstants


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth: FirebaseAuth,
) : ViewModel() {


    var res by mutableStateOf(Resource<Any>())

    var password by mutableStateOf(InputData())
    var email by mutableStateOf(InputData())

    operator fun invoke(navController: NavController) {
        validate()
        if (password.error == null && email.error == null) {
            viewModelScope.launch {
                res = Resource.loading()
                res = try {
                    auth.signInWithEmailAndPassword(email.value, password.value).await()
                    navController.navigate(AuthConstants.AFTER_LOGIN_ROUTE)
                    Resource.reset()
                } catch (e: Exception) {
                    Resource.error(e)
                }
            }
        }
    }

    fun validate() {
        password = password.copy(
            error = password.value.validate("Password").required().build()
        )
        email = email.copy(
            error = email.value.validate("Email").required().isEmail().build()
        )
    }
}
