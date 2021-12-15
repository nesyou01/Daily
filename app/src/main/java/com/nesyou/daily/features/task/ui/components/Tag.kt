package com.nesyou.daily.features.task.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nesyou.daily.features.task.domain.models.TaskTag

@Composable
fun RowScope.Tag(tag: TaskTag, selected: Boolean = false, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .weight(1F)
            .background(if (!selected) tag.color.copy(alpha = 0.3F) else Color.Transparent)
            .clickable(
                onClick = onClick
            )
            .border(
                .5.dp,
                if (selected) tag.color else Color.Transparent,
                CircleShape
            )
            .padding(vertical = 7.5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            tag.name,
            color = tag.color,
            style = MaterialTheme.typography.h6
        )
    }

}