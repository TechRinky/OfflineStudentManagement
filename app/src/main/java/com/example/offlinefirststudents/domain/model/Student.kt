package com.example.offlinefirststudents.domain.model

import com.example.offlinefirststudents.domain.SyncStatus

data class Student(
    val id: String,
    val fullName: String,
    val studentClass: String,
    val gender: String,
    val schoolId: String,
    val createdAt: String,
    val updatedAt: String,
    val deleted: Boolean = false,
    val syncStatus: SyncStatus = SyncStatus.PENDING
)
