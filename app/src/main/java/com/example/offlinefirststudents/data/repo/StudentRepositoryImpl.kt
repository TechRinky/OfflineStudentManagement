package com.example.offlinefirststudents.data.repo

import androidx.paging.*
import com.example.offlinefirststudents.data.local.AppDatabase
import com.example.offlinefirststudents.data.mapper.toDomain
import com.example.offlinefirststudents.data.mapper.toEntity
import com.example.offlinefirststudents.domain.SyncStatus
import com.example.offlinefirststudents.domain.model.Student
import com.example.offlinefirststudents.domain.repo.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : StudentRepository {

    override fun paging(): Flow<PagingData<Student>> {
        return Pager(
            PagingConfig(pageSize = 20, enablePlaceholders = false)
        ) { db.studentDao().paging() }
            .flow
            .map { it.map { e -> e.toDomain() } }
    }

    override suspend fun get(id: String): Student? = db.studentDao().getById(id)?.toDomain()

    override suspend fun upsert(student: Student) {
        db.studentDao().upsert(student.copy(syncStatus = SyncStatus.PENDING).toEntity())
    }

    override suspend fun delete(id: String, updatedAt: String) {
        db.studentDao().softDelete(id, updatedAt, SyncStatus.PENDING)
        // Cascade local delete rows marked deleted is automatic via FK on actual DELETE,
        // we keep soft-delete here; actual removal happens when remote confirms deletion.
    }
}
