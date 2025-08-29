package com.example.offlinefirststudents.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\u0005H\'J\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\tH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ&\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\"\u0010\u0014\u001a\u00020\u00032\u0012\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0016\"\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u0017\u00a8\u0006\u0018"}, d2 = {"Lcom/example/offlinefirststudents/data/local/dao/ScoreCardDao;", "", "deleteById", "", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByStudent", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/offlinefirststudents/data/local/entity/ScoreCardEntity;", "studentId", "pendingOnce", "states", "Lcom/example/offlinefirststudents/domain/SyncStatus;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "softDelete", "updatedAt", "status", "(Ljava/lang/String;Ljava/lang/String;Lcom/example/offlinefirststudents/domain/SyncStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsert", "scoreCards", "", "([Lcom/example/offlinefirststudents/data/local/entity/ScoreCardEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface ScoreCardDao {
    
    @androidx.room.Query(value = "SELECT * FROM scorecards WHERE studentId = :studentId AND deleted = 0 ORDER BY updatedAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.offlinefirststudents.data.local.entity.ScoreCardEntity>> getByStudent(@org.jetbrains.annotations.NotNull()
    java.lang.String studentId);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsert(@org.jetbrains.annotations.NotNull()
    com.example.offlinefirststudents.data.local.entity.ScoreCardEntity[] scoreCards, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE scorecards SET deleted = 1, syncStatus = :status, updatedAt = :updatedAt WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object softDelete(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String updatedAt, @org.jetbrains.annotations.NotNull()
    com.example.offlinefirststudents.domain.SyncStatus status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM scorecards WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM scorecards WHERE syncStatus IN (:states) OR deleted = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object pendingOnce(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.example.offlinefirststudents.domain.SyncStatus> states, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.offlinefirststudents.data.local.entity.ScoreCardEntity>> $completion);
}