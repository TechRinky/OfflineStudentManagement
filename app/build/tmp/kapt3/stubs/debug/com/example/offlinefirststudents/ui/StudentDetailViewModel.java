package com.example.offlinefirststudents.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0010J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u0010J\u0006\u0010\u001e\u001a\u00020\u001dR\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/offlinefirststudents/ui/StudentDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "students", "Lcom/example/offlinefirststudents/domain/repo/StudentRepository;", "scores", "Lcom/example/offlinefirststudents/domain/repo/ScoreCardRepository;", "(Lcom/example/offlinefirststudents/domain/repo/StudentRepository;Lcom/example/offlinefirststudents/domain/repo/ScoreCardRepository;)V", "_student", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/offlinefirststudents/domain/model/Student;", "cards", "", "Lcom/example/offlinefirststudents/domain/model/ScoreCard;", "getCards", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "currentId", "", "student", "Lkotlinx/coroutines/flow/StateFlow;", "getStudent", "()Lkotlinx/coroutines/flow/StateFlow;", "addScore", "Lkotlinx/coroutines/Job;", "subject", "score", "", "deleteCard", "id", "load", "", "retryFailedCards", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class StudentDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.offlinefirststudents.domain.repo.StudentRepository students = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.offlinefirststudents.domain.repo.ScoreCardRepository scores = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.offlinefirststudents.domain.model.Student> _student = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.offlinefirststudents.domain.model.Student> student = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String currentId;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.offlinefirststudents.domain.model.ScoreCard>> cards = null;
    
    @javax.inject.Inject()
    public StudentDetailViewModel(@org.jetbrains.annotations.NotNull()
    com.example.offlinefirststudents.domain.repo.StudentRepository students, @org.jetbrains.annotations.NotNull()
    com.example.offlinefirststudents.domain.repo.ScoreCardRepository scores) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.offlinefirststudents.domain.model.Student> getStudent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.offlinefirststudents.domain.model.ScoreCard>> getCards() {
        return null;
    }
    
    public final void load(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job addScore(@org.jetbrains.annotations.NotNull()
    java.lang.String subject, int score) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteCard(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    public final void retryFailedCards() {
    }
}