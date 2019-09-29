package com.vhra.dashfi.utils;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DiskIOThreadExecutor  implements Executor {
    private final Executor mDiskIO;

    public DiskIOThreadExecutor() {
        mDiskIO = Executors.newFixedThreadPool(1);
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mDiskIO.execute(command);
    }
}