package com.nesyou.daily.features.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.nesyou.daily.features.auth.domain.utils.AuthConstants
import com.nesyou.daily.features.auth.use_cases.SaveUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@HiltViewModel
class SocialLoginViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val saveUser: SaveUserDataUseCase
) : ViewModel() {

    operator fun invoke(credential: AuthCredential, navController: NavController) {
        viewModelScope.launch {
            val data = auth.signInWithCredential(credential).await()
            saveUser(data.user!!)
            navController.navigate(AuthConstants.AFTER_LOGIN_ROUTE)
        }
    }

}