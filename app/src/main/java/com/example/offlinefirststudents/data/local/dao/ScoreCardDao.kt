package com.example.offlinefirststudents.data.local.dao

import androidx.room.*
import com.example.offlinefirststudents.data.local.entity.ScoreCardEntity
import com.example.offlinefirststudents.domain.SyncStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreCardDao {
    @Query("SELECT * FROM scorecards WHERE studentId = :studentId AND deleted = 0 ORDER BY updatedAt DESC")
    fun getByStudent(studentId: String): Flow<List<ScoreCardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg scoreCards: ScoreCardEntity)

    @Query("UPDATE scorecards SET deleted = 1, syncStatus = :status, updatedAt = :updatedAt WHERE id = :id")
    suspend fun softDelete(id: String, updatedAt: String, status: SyncStatus)

    @Query("DELETE FROM scorecards WHERE id = :id")
    suspend fun deleteById(id: String)   // ✅ new hard delete

    @Query("SELECT * FROM scorecards WHERE syncStatus IN (:states) OR deleted = 1")
    suspend fun pendingOnce(states: List<SyncStatus>): List<ScoreCardEntity>
}
