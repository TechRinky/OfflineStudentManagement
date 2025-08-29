package com.example.offlinefirststudents.data.local

import androidx.room.TypeConverter
import com.example.offlinefirststudents.domain.SyncStatus

class SyncStatusConverter {
    @TypeConverter
    fun toString(value: SyncStatus): String = value.name

    @TypeConverter
    fun fromString(value: String): SyncStatus = runCatching { SyncStatus.valueOf(value) }.getOrDefault(SyncStatus.PENDING)
}
