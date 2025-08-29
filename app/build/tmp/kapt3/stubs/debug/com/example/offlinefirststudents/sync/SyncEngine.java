package com.example.offlinefirststudents.sync;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/example/offlinefirststudents/sync/SyncEngine;", "", "db", "Lcom/example/offlinefirststudents/data/local/AppDatabase;", "remote", "Lcom/example/offlinefirststudents/data/remote/MockRemoteApi;", "(Lcom/example/offlinefirststudents/data/local/AppDatabase;Lcom/example/offlinefirststudents/data/remote/MockRemoteApi;)V", "syncNow", "Lcom/example/offlinefirststudents/domain/SyncStatus;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class SyncEngine {
    @org.jetbrains.annotations.NotNull()
    private final com.example.offlinefirststudents.data.local.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.offlinefirststudents.data.remote.MockRemoteApi remote = null;
    
    @javax.inject.Inject()
    public SyncEngine(@org.jetbrains.annotations.NotNull()
    com.example.offlinefirststudents.data.local.AppDatabase db, @org.jetbrains.annotations.NotNull()
    com.example.offlinefirststudents.data.remote.MockRemoteApi remote) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncNow(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.offlinefirststudents.domain.SyncStatus> $completion) {
        return null;
    }
}