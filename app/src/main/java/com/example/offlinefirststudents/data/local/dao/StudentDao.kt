package com.example.offlinefirststudents.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.offlinefirststudents.data.local.entity.StudentEntity
import com.example.offlinefirststudents.domain.SyncStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * FROM students WHERE deleted = 0 ORDER BY updatedAt DESC")
    fun paging(): PagingSource<Int, StudentEntity>

    @Query("SELECT * FROM students WHERE id = :id")
    suspend fun getById(id: String): StudentEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg students: StudentEntity)

    @Query("UPDATE students SET deleted = 1, syncStatus = :status, updatedAt = :updatedAt WHERE id = :id")
    suspend fun softDelete(id: String, updatedAt: String, status: SyncStatus)

    @Query("DELETE FROM students WHERE id = :id")
    suspend fun deleteById(id: String)   // ✅ new hard delete

    @Query("SELECT * FROM students WHERE syncStatus IN (:states) OR deleted = 1")
    fun pending(states: List<SyncStatus>): Flow<List<StudentEntity>>

    @Query("SELECT * FROM students WHERE syncStatus IN (:states) OR deleted = 1")
    suspend fun pendingOnce(states: List<SyncStatus>): List<StudentEntity>
}
