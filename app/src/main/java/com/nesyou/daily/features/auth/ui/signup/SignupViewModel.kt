package com.nesyou.daily.features.auth.ui.signup

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.nesyou.daily.core.domain.models.Resource
import com.nesyou.daily.core.domain.utils.validate
import com.nesyou.daily.features.auth.domain.models.InputData
import com.nesyou.daily.features.auth.domain.utils.AuthConstants
import com.nesyou.daily.features.auth.use_cases.SaveUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject
import com.google.firebase.auth.UserProfileChangeRequest


@HiltViewModel
class SignupViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val saveUser: SaveUserDataUseCase
) : ViewModel() {


    var status by mutableStateOf(Resource<Any>())

    var name by mutableStateOf(InputData())
    var password by mutableStateOf(InputData())
    var email by mutableStateOf(InputData())

    operator fun invoke(navController: NavController) {
        validate()
        if (email.error == null && password.error == null && name.error == null) {
            viewModelScope.launch {
                status = Resource.loading()
                status = try {
                    val data =
                        auth.createUserWithEmailAndPassword(email.value, password.value).await()
                    data.user?.updateProfile(
                        UserProfileChangeRequest.Builder().setDisplayName(name.value).build()
                    )?.await()
                    saveUser(data.user!!)
                    navController.navigate(AuthConstants.AFTER_LOGIN_ROUTE)
                    Resource.reset()
                } catch (e: Exception) {
                    Resource.error(e)
                }
            }
        }
    }

    fun validate() {
        name = name.copy(
            error = name.value.validate("Name").required().min(AuthConstants.MIN_NAME_LENGTH)
                .build()
        )
        password = password.copy(
            error = password.value.validate("Password").required()
                .min(AuthConstants.MIN_PASSWORD_LENGTH).build()
        )
        email = email.copy(
            error = email.value.validate("Email").required().isEmail().build()
        )
    }
}

