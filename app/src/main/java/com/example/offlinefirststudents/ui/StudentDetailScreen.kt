package com.example.offlinefirststudents.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.offlinefirststudents.domain.SyncStatus

@Composable
fun StudentDetailScreen(studentId: String, onBack:()->Unit, vm: StudentDetailViewModel = hiltViewModel()) {
    LaunchedEffect(studentId) { vm.load(studentId) }
    val student = vm.student.collectAsState().value
    val cards = vm.cards.collectAsState(emptyList()).value

    Column(Modifier.fillMaxSize().padding(12.dp)) {
        Row {
            Button(onClick = onBack) { Text("Back") }
            Spacer(Modifier.width(8.dp))
            Text(student?.fullName ?: "Loading...", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.weight(1f))
            if (student != null) StatusBadge(student.syncStatus)
        }
        Spacer(Modifier.height(12.dp))

        var subject by remember { mutableStateOf("") }
        var score by remember { mutableStateOf("") }
        Row {
            OutlinedTextField(subject, onValueChange = { subject = it }, label = { Text("Subject") }, modifier = Modifier.weight(1f))
            Spacer(Modifier.width(8.dp))
            OutlinedTextField(score, onValueChange = { score = it.filter { ch -> ch.isDigit() } }, label = { Text("Score") }, modifier = Modifier.weight(1f))
        }
        Row {
            Button(onClick = { vm.addScore(subject.trim(), score.toIntOrNull() ?: 0); subject=""; score="" }) { Text("Add Score") }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { vm.retryFailedCards() }) { Text("Retry Failed") }
        }

        Spacer(Modifier.height(8.dp))
        Divider()
        Spacer(Modifier.height(8.dp))

        LazyColumn {
            items(cards.size) { idx ->
                val c = cards[idx]
                Row(Modifier.fillMaxWidth().padding(8.dp)) {
                    Text("${c.subject}: ${c.score}", modifier = Modifier.weight(1f))
                    StatusBadge(c.syncStatus)
                    Spacer(Modifier.width(8.dp))
                    TextButton(onClick = { vm.deleteCard(c.id) }) { Text("Delete") }
                }
                Divider()
            }
        }
    }
}
