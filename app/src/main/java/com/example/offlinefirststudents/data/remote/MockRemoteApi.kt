package com.example.offlinefirststudents.data.remote

import com.example.offlinefirststudents.domain.model.ScoreCard
import com.example.offlinefirststudents.domain.model.Student
import kotlinx.coroutines.delay
import java.util.concurrent.ConcurrentHashMap

/**
 * In-app pretend server with simple conflict rules:
 * - latest updatedAt wins
 * - remote deletion overrides local
 */
class MockRemoteApi {
    private val students = ConcurrentHashMap<String, Student>()
    private val cards = ConcurrentHashMap<String, ScoreCard>()

    suspend fun upsertStudent(s: Student): Student {
        delay(200) // pretend network
        val existing = students[s.id]
        if (existing == null || existing.updatedAt <= s.updatedAt) {
            students[s.id] = s.copy(syncStatus = com.example.offlinefirststudents.domain.SyncStatus.SYNCED)
        }
        return students[s.id]!!
    }

    suspend fun deleteStudent(id: String, updatedAt: String) {
        delay(150)
        val prev = students[id]
        if (prev == null || prev.updatedAt <= updatedAt) {
            students[id] = prev?.copy(deleted = true, updatedAt = updatedAt)
                ?: Student(id, "", "", "", "", updatedAt, updatedAt, deleted = true)
            // cascade remote
            cards.values.filter { it.studentId == id }.forEach {
                cards[it.id] = it.copy(deleted = true, updatedAt = updatedAt)
            }
        }
    }

    suspend fun upsertScoreCard(c: ScoreCard): ScoreCard {
        delay(200)
        val existing = cards[c.id]
        if (existing == null || existing.updatedAt <= c.updatedAt) {
            cards[c.id] = c.copy(syncStatus = com.example.offlinefirststudents.domain.SyncStatus.SYNCED)
        }
        return cards[c.id]!!
    }

    suspend fun deleteScoreCard(id: String, updatedAt: String) {
        delay(150)
        val prev = cards[id]
        if (prev == null || prev.updatedAt <= updatedAt) {
            cards[id] = prev?.copy(deleted = true, updatedAt = updatedAt)
                ?: ScoreCard(id, "", "", 0, updatedAt, updatedAt, deleted = true)
        }
    }

    // For resolving remote deletions
    suspend fun isStudentDeleted(id: String): Boolean = students[id]?.deleted == true
    suspend fun isScoreDeleted(id: String): Boolean = cards[id]?.deleted == true
}
