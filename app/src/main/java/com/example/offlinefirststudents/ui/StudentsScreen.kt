package com.example.offlinefirststudents.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems




import com.example.offlinefirststudents.domain.SyncStatus



@Composable
fun StudentsScreen(onOpen: (String)->Unit, vm: StudentsViewModel = hiltViewModel()) {
    val students = vm.students.collectAsLazyPagingItems()
    var name by remember { mutableStateOf("") }
    var klass by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Other") }
    var schoolId by remember { mutableStateOf("S1") }

    Column(Modifier.fillMaxSize().padding(12.dp)) {
        Text("Add / Update Student", style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(name, onValueChange = { name = it }, label = { Text("Full name") }, modifier = Modifier.weight(1f))
            Spacer(Modifier.width(8.dp))
            OutlinedTextField(klass, onValueChange = { klass = it }, label = { Text("Class") }, modifier = Modifier.weight(1f))
        }
        Row {
            OutlinedTextField(gender, onValueChange = { gender = it }, label = { Text("Gender") }, modifier = Modifier.weight(1f))
            Spacer(Modifier.width(8.dp))
            OutlinedTextField(schoolId, onValueChange = { schoolId = it }, label = { Text("School Id") }, modifier = Modifier.weight(1f))
        }
        Row {
            Button(onClick = { vm.addOrUpdate(name.trim(), klass.trim(), gender.trim(), schoolId.trim()); name=""; klass="" }) { Text("Save") }
            Spacer(Modifier.width(8.dp))
            if (students.itemCount > 0) Button(onClick = { vm.retryAllFailed() }) { Text("Retry Failed") }
        }
        Spacer(Modifier.height(12.dp))
        Divider()
        Spacer(Modifier.height(12.dp))

        LazyColumn {
            items(students.itemCount) { index ->
                val s = students[index]
                if (s != null) {
                    StudentRow(
                        name = s.fullName,
                        cls = s.studentClass,
                        status = s.syncStatus,
                        onClick = { onOpen(s.id) },
                        onDelete = { vm.deleteStudent(s.id) }
                    )
                    Divider()
                }
            }
        }

    }
}

@Composable
private fun StudentRow(name: String, cls: String, status: SyncStatus, onClick:()->Unit, onDelete:()->Unit) {
    Row(Modifier.fillMaxWidth().clickable { onClick() }.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text(name, style = MaterialTheme.typography.titleMedium, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text("Class: $cls", style = MaterialTheme.typography.bodySmall)
        }
        StatusBadge(status)
        Spacer(Modifier.width(8.dp))
        TextButton(onClick = onDelete) { Text("Delete") }
    }
}

@Composable
fun StatusBadge(status: SyncStatus) {
    val (label, color) = when(status) {
        SyncStatus.SYNCED -> "SYNCED" to MaterialTheme.colorScheme.primary
        SyncStatus.PENDING -> "PENDING" to MaterialTheme.colorScheme.tertiary
        SyncStatus.FAILED -> "FAILED" to MaterialTheme.colorScheme.error
    }
    Surface(color = color.copy(alpha = 0.15f), shape = MaterialTheme.shapes.small) {
        Text(label, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
    }
}
