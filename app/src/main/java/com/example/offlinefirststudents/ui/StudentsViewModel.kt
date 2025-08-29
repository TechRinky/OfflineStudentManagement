package com.example.offlinefirststudents.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.offlinefirststudents.domain.SyncStatus
import com.example.offlinefirststudents.domain.model.Student
import com.example.offlinefirststudents.domain.repo.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class StudentsViewModel @Inject constructor(
    private val repo: StudentRepository
) : ViewModel() {

    val students: Flow<PagingData<Student>> = repo.paging().cachedIn(viewModelScope)

    fun addOrUpdate(name: String, klass: String, gender: String, schoolId: String) = viewModelScope.launch {
        val now = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        val id = UUID.randomUUID().toString()
        val s = Student(
            id = id,
            fullName = name.ifEmpty { "Student ${Random.nextInt(1000)}" },
            studentClass = klass.ifEmpty { "10" },
            gender = gender.ifEmpty { "Other" },
            schoolId = schoolId.ifEmpty { "S1" },
            createdAt = now,
            updatedAt = now,
            deleted = false,
            syncStatus = SyncStatus.PENDING
        )
        repo.upsert(s)
    }

    fun deleteStudent(id: String) = viewModelScope.launch {
        val now = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        repo.delete(id, now)
    }

    fun retryAllFailed() {
        // Enqueue a one-shot sync
        // SyncWorker is triggered from Activity; here we could expose an event.
    }
}
