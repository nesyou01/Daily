package com.nesyou.daily.features.task.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nesyou.daily.features.task.domain.models.TaskTag
import com.nesyou.daily.features.task.domain.models.TaskType
import com.nesyou.daily.features.task.domain.utils.TaskConstants.ROOM_TASKS_TABLE
import java.sql.Timestamp

@Entity(tableName = ROOM_TASKS_TABLE)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val date: Timestamp,
    val from: Timestamp,
    val until: Timestamp,
    val description: String,
    val type: TaskType,
    val tags: List<TaskTag>,
)
