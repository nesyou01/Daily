package com.nesyou.daily.features.task.domain.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.nesyou.daily.features.task.domain.models.TaskTag
import com.nesyou.daily.features.task.domain.models.TaskType
import java.sql.Timestamp


class TaskDataConverter {

    @TypeConverter
    fun fromTimestamp(time: Timestamp): Long =
        time.time


    @TypeConverter
    fun toTimestamp(time: Long): Timestamp =
        Timestamp(time)

    @TypeConverter
    fun fromListOfTaskTags(data: String): List<TaskTag> =
         Gson().fromJson<List<TaskTag>>(data, List::class.java)


    @TypeConverter
    fun toListOfTaskTags(data: List<TaskTag>): String =
        Gson().toJson(data)


    @TypeConverter
    fun fromTaskType(type: TaskType): Int =
        type.ordinal


    @TypeConverter
    fun toTaskType(typeId: Int): TaskType =
        TaskType.values()[typeId]
}