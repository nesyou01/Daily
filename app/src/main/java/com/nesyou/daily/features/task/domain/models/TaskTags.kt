package com.nesyou.daily.features.task.domain.models

import androidx.compose.ui.graphics.Color


sealed class TaskTag constructor(val name: String, val color: Color) {

    companion object {
        object Office : TaskTag("Office", Color(0xFFF0A58E))
        object Home : TaskTag("Home", Color(0xFF1EC1C3))
        object Urgent : TaskTag("Urgent", Color(0xFFF57C96))
        object Work : TaskTag("Work", Color(0xFF8F81FE))

        val values = arrayOf(Office, Home, Urgent, Work)
    }

}