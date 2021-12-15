package com.nesyou.daily.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nesyou.daily.features.task.data.local.TaskDao
import com.nesyou.daily.features.task.data.local.entities.TaskEntity
import com.nesyou.daily.features.task.domain.utils.TaskDataConverter

@Database(
    entities = [TaskEntity::class],
    version = 2
)
@TypeConverters(TaskDataConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}