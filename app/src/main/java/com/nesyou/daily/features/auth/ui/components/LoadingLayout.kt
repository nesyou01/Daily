package com.nesyou.daily.features.auth.ui.components

import androidx.compose.runtime.Composable
import com.nesyou.daily.core.domain.models.Resource
import com.nesyou.daily.core.domain.models.Status
import com.nesyou.daily.core.ui.components.LoadingDialog
import com.nesyou.daily.core.ui.components.MainDialog

@Composable
fun ResourceLayout(
    res: Resource<Any>,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    if (res.status == Status.LOADING) {
        LoadingDialog()
    }

    if (res.status == Status.ERROR) {
        MainDialog(text = res.error?.message, onDismissRequest = onDismissRequest)
    }

    content()
}