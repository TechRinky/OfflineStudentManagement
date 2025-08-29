package com.example.offlinefirststudents.data.repo

import com.example.offlinefirststudents.data.local.AppDatabase
import com.example.offlinefirststudents.data.mapper.toDomain
import com.example.offlinefirststudents.data.mapper.toEntity
import com.example.offlinefirststudents.domain.SyncStatus
import com.example.offlinefirststudents.domain.model.ScoreCard
import com.example.offlinefirststudents.domain.repo.ScoreCardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ScoreCardRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : ScoreCardRepository {
    override fun forStudent(studentId: String): Flow<List<ScoreCard>> =
        db.scoreCardDao().getByStudent(studentId).map { it.map { e -> e.toDomain() } }

    override suspend fun upsert(card: ScoreCard) {
        db.scoreCardDao().upsert(card.copy(syncStatus = SyncStatus.PENDING).toEntity())
    }

    override suspend fun delete(id: String, updatedAt: String) {
        db.scoreCardDao().softDelete(id, updatedAt, SyncStatus.PENDING)
    }

    override suspend fun cascadeDeleteForStudent(studentId: String) {
        db.scoreCardDao().deleteById(studentId)
    }
}
