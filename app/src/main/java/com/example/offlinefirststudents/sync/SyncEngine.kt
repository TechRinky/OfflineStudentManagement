package com.example.offlinefirststudents.sync

import android.util.Log
import com.example.offlinefirststudents.data.local.AppDatabase
import com.example.offlinefirststudents.data.mapper.toDomain
import com.example.offlinefirststudents.data.mapper.toEntity
import com.example.offlinefirststudents.data.remote.MockRemoteApi
import com.example.offlinefirststudents.domain.SyncStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SyncEngine @Inject constructor(
    private val db: AppDatabase,
    private val remote: MockRemoteApi
) {
    suspend fun syncNow(): SyncStatus = withContext(Dispatchers.IO) {
        try {
            val pendingStudents = db.studentDao().pendingOnce(
                listOf(SyncStatus.PENDING, SyncStatus.FAILED)
            )
            Log.e("pendinstudents",pendingStudents.toString())

            val pendingScoreCards = db.scoreCardDao().pendingOnce(
                listOf(SyncStatus.PENDING, SyncStatus.FAILED)
            )

            // 🔄 Sync Students
            for (s in pendingStudents) {
                if (s.deleted) {
                    // remote delete
                    remote.deleteStudent(s.id, s.updatedAt)
                    // local hard delete
                    db.studentDao().deleteById(s.id)
                } else {
                    val synced = remote.upsertStudent(s.toDomain())
                    db.studentDao().upsert(
                        synced.copy(syncStatus = SyncStatus.SYNCED).toEntity()
                    )
                }
            }

            // 🔄 Sync ScoreCards
            for (c in pendingScoreCards) {
                if (c.deleted) {
                    remote.deleteScoreCard(c.id, c.updatedAt)
                    db.scoreCardDao().deleteById(c.id)
                } else {
                    val synced = remote.upsertScoreCard(c.toDomain())
                    db.scoreCardDao().upsert(
                        synced.copy(syncStatus = SyncStatus.SYNCED).toEntity()
                    )
                }
            }

             SyncStatus.SYNCED
        } catch (e: Exception) {

             SyncStatus.FAILED
        }
    }
}
