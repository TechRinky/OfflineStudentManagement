package com.example.offlinefirststudents.sync;

import androidx.hilt.work.WorkerAssistedFactory;
import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface SyncWorker_AssistedFactory extends WorkerAssistedFactory<SyncWorker> {
}
