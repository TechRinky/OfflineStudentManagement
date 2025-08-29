package com.example.offlinefirststudents.di

import android.content.Context
import androidx.room.Room
import com.example.offlinefirststudents.data.local.AppDatabase
import com.example.offlinefirststudents.data.remote.MockRemoteApi
import com.example.offlinefirststudents.data.repo.ScoreCardRepositoryImpl
import com.example.offlinefirststudents.data.repo.StudentRepositoryImpl
import com.example.offlinefirststudents.domain.repo.ScoreCardRepository
import com.example.offlinefirststudents.domain.repo.StudentRepository
import com.example.offlinefirststudents.sync.SyncEngine
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides @Singleton
    fun provideDb(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "offline-students.db")
            .fallbackToDestructiveMigration() // For demo; swap with proper migrations
            .build()

    @Provides @Singleton
    fun provideRemote(): MockRemoteApi = MockRemoteApi()

    @Provides @Singleton
    fun provideSyncEngine(db: AppDatabase, remote: MockRemoteApi): SyncEngine =
        SyncEngine(db, remote)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds abstract fun bindStudentRepo(impl: StudentRepositoryImpl): StudentRepository
    @Binds abstract fun bindScoreRepo(impl: ScoreCardRepositoryImpl): ScoreCardRepository
}
