package com.example.offlinefirststudents.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.offlinefirststudents.sync.SyncWorker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SyncWorker.schedule(this)
        setContent {
            MaterialTheme {
                val nav = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Offline-First Students") },
                            actions = {
                                TextButton(onClick = { SyncWorker.oneShot(this@MainActivity) }) {
                                    Text("Sync Now")
                                }
                            }
                        )
                    }
                ) { inner ->
                    NavHost(
                        navController = nav,
                        startDestination = "students",
                        modifier = Modifier.padding(inner)
                    ) {
                        composable("students") {
                            StudentsScreen(onOpen = { id -> nav.navigate("student/$id") })
                        }
                        composable(
                            "student/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")!!
                            StudentDetailScreen(studentId = id, onBack = { nav.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}
