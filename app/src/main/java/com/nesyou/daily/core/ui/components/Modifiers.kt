package com.nesyou.daily.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier


object Modifiers {
    fun Modifier.mutedClickable(onClick: () -> Unit): Modifier {
        return this.then(
            Modifier.clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = onClick
            ),
        )
    }

}