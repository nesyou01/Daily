package com.nesyou.daily.features.task.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nesyou.daily.features.task.data.local.entities.TaskEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    suspend fun all(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg task: TaskEntity)

}