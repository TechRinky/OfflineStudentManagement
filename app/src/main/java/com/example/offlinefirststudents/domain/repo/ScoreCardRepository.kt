package com.example.offlinefirststudents.domain.repo

import com.example.offlinefirststudents.domain.model.ScoreCard
import kotlinx.coroutines.flow.Flow

interface ScoreCardRepository {
    fun forStudent(studentId: String): Flow<List<ScoreCard>>
    suspend fun upsert(card: ScoreCard)
    suspend fun delete(id: String, updatedAt: String)
    suspend fun cascadeDeleteForStudent(studentId: String)
}
