package com.example.offlinefirststudents.domain.model

import com.example.offlinefirststudents.domain.SyncStatus

data class ScoreCard(
    val id: String,
    val studentId: String,
    val subject: String,
    val score: Int,
    val createdAt: String,
    val updatedAt: String,
    val deleted: Boolean = false,
    val syncStatus: SyncStatus = SyncStatus.PENDING
)
