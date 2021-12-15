package com.nesyou.daily.features.task.data

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.nesyou.daily.core.domain.models.Task
import com.nesyou.daily.core.domain.utils.Constants
import com.nesyou.daily.features.task.data.local.TaskDao
import com.nesyou.daily.features.task.data.local.entities.TaskEntity
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val dao: TaskDao,
    private val firestore: FirebaseFirestore
) {
    suspend fun insert(task: Task): DocumentReference {
        dao.insert(task.toTaskEntity())
        return firestore.collection(Constants.TASKS_COLLECTION).add(task).await()
    }

    suspend fun all(): List<TaskEntity> {
        return dao.all()
    }
}