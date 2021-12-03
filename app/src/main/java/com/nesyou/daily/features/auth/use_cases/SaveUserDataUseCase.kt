package com.nesyou.daily.features.auth.use_cases

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.nesyou.daily.core.domain.models.User
import com.nesyou.daily.core.domain.utils.Constants
import com.nesyou.daily.core.domain.utils.Helpers
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class SaveUserDataUseCase @Inject constructor(
    private val store: FirebaseFirestore
) {

    suspend operator fun invoke(user: FirebaseUser) {
        store.collection(Constants.USERS_COLLECTION).document(user.uid).set(
            User(
                updateAt = Helpers.currentTimeStamp(),
                cover = if (user.photoUrl == null) null else user.photoUrl.toString(),
                name = user.displayName!!
            )
        ).await()
    }

}