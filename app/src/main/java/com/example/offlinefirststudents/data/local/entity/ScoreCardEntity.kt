package com.example.offlinefirststudents.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.offlinefirststudents.domain.SyncStatus

@Entity(
    tableName = "scorecards",
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("studentId")]
)
data class ScoreCardEntity(
    @PrimaryKey val id: String,
    val studentId: String,
    val subject: String,
    val score: Int,
    val createdAt: String,
    val updatedAt: String,
    val deleted: Boolean,
    val syncStatus: SyncStatus
)
