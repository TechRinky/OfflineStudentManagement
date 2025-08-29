package com.example.offlinefirststudents.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.offlinefirststudents.data.local.dao.ScoreCardDao
import com.example.offlinefirststudents.data.local.dao.StudentDao
import com.example.offlinefirststudents.data.local.entity.ScoreCardEntity
import com.example.offlinefirststudents.data.local.entity.StudentEntity

@Database(
    entities = [StudentEntity::class, ScoreCardEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(SyncStatusConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun scoreCardDao(): ScoreCardDao
}
