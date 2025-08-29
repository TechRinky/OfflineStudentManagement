package com.example.offlinefirststudents.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.offlinefirststudents.domain.SyncStatus

@Entity(tableName = "students")
data class StudentEntity(
    @PrimaryKey val id: String,
    val fullName: String,
    val studentClass: String,
    val gender: String,
    val schoolId: String,
    val createdAt: String,
    val updatedAt: String,
    val deleted: Boolean,
    val syncStatus: SyncStatus
)
