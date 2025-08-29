package com.example.offlinefirststudents.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.offlinefirststudents.domain.SyncStatus
import com.example.offlinefirststudents.domain.model.ScoreCard
import com.example.offlinefirststudents.domain.model.Student
import com.example.offlinefirststudents.domain.repo.ScoreCardRepository
import com.example.offlinefirststudents.domain.repo.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StudentDetailViewModel @Inject constructor(
    private val students: StudentRepository,
    private val scores: ScoreCardRepository
) : ViewModel() {

    private val _student = MutableStateFlow<Student?>(null)
    val student: StateFlow<Student?> = _student.asStateFlow()

    private var currentId: String? = null

    val cards = MutableStateFlow<List<ScoreCard>>(emptyList())

    fun load(id: String) {
        currentId = id
        viewModelScope.launch {
            _student.value = students.get(id)
        }
        viewModelScope.launch {
            scores.forStudent(id).collect { cards.value = it }
        }
    }

    fun addScore(subject: String, score: Int) = viewModelScope.launch {
        val sid = currentId ?: return@launch
        val now = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        val c = ScoreCard(
            id = UUID.randomUUID().toString(),
            studentId = sid,
            subject = subject.ifEmpty { "Maths" },
            score = score,
            createdAt = now,
            updatedAt = now,
            deleted = false,
            syncStatus = SyncStatus.PENDING
        )
        scores.upsert(c)
    }

    fun deleteCard(id: String) = viewModelScope.launch {
        val now = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        scores.delete(id, now)
    }

    fun retryFailedCards() {
        // No-op; background worker will pick them
    }
}
