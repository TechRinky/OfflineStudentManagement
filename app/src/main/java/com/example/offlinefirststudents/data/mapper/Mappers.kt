package com.example.offlinefirststudents.data.mapper

import com.example.offlinefirststudents.data.local.entity.ScoreCardEntity
import com.example.offlinefirststudents.data.local.entity.StudentEntity
import com.example.offlinefirststudents.domain.SyncStatus
import com.example.offlinefirststudents.domain.model.ScoreCard
import com.example.offlinefirststudents.domain.model.Student

fun StudentEntity.toDomain() = Student(id, fullName, studentClass, gender, schoolId, createdAt, updatedAt, deleted, syncStatus)
fun Student.toEntity() = StudentEntity(id, fullName, studentClass, gender, schoolId, createdAt, updatedAt, deleted, syncStatus)

fun ScoreCardEntity.toDomain() = ScoreCard(id, studentId, subject, score, createdAt, updatedAt, deleted, syncStatus)
fun ScoreCard.toEntity() = ScoreCardEntity(id, studentId, subject, score, createdAt, updatedAt, deleted, syncStatus)

fun statusForPending(isDeleted: Boolean) = if (isDeleted) SyncStatus.PENDING else SyncStatus.PENDING
