package com.example.offlinefirststudents.domain.repo

import androidx.paging.PagingData
import com.example.offlinefirststudents.domain.model.Student
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    fun paging(): Flow<PagingData<Student>>
    suspend fun get(id: String): Student?
    suspend fun upsert(student: Student)
    suspend fun delete(id: String, updatedAt: String)
}
