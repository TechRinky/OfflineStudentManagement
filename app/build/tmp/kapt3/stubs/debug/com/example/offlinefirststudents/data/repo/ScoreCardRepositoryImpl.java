package com.example.offlinefirststudents.data.repo;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u001e\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/offlinefirststudents/data/repo/ScoreCardRepositoryImpl;", "Lcom/example/offlinefirststudents/domain/repo/ScoreCardRepository;", "db", "Lcom/example/offlinefirststudents/data/local/AppDatabase;", "(Lcom/example/offlinefirststudents/data/local/AppDatabase;)V", "cascadeDeleteForStudent", "", "studentId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "id", "updatedAt", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "forStudent", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/offlinefirststudents/domain/model/ScoreCard;", "upsert", "card", "(Lcom/example/offlinefirststudents/domain/model/ScoreCard;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ScoreCardRepositoryImpl implements com.example.offlinefirststudents.domain.repo.ScoreCardRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.offlinefirststudents.data.local.AppDatabase db = null;
    
    @javax.inject.Inject()
    public ScoreCardRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.offlinefirststudents.data.local.AppDatabase db) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.offlinefirststudents.domain.model.ScoreCard>> forStudent(@org.jetbrains.annotations.NotNull()
    java.lang.String studentId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object upsert(@org.jetbrains.annotations.NotNull()
    com.example.offlinefirststudents.domain.model.ScoreCard card, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String updatedAt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object cascadeDeleteForStudent(@org.jetbrains.annotations.NotNull()
    java.lang.String studentId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}