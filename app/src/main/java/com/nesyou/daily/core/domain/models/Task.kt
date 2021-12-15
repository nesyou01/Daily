package com.nesyou.daily.core.domain.models

import com.nesyou.daily.features.task.data.local.entities.TaskEntity
import com.nesyou.daily.features.task.domain.models.TaskTag
import com.nesyou.daily.features.task.domain.models.TaskType
import java.sql.Timestamp

data class Task(
    val creatorId: String,
    val title: String,
    val date: Timestamp,
    val from: Timestamp,
    val until: Timestamp,
    val description: String,
    val type: TaskType,
    val tags: List<TaskTag>,
) {
    fun toTaskEntity(): TaskEntity = TaskEntity(
        until = until,
        from = from,
        tags = tags,
        description = description,
        date = date,
        title = title,
        type = type
    )
}