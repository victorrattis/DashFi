package com.vhra.dashfi.domain;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UseCaseSchedulerImpl implements UseCaseScheduler {
    private static final int POOL_SIZE = 2;
    private static final int MAX_POOL_SIZE = 4;
    private static final int TIMEOUT = 30;

    private final ThreadPoolExecutor mThreadPoolExecutor;

    public UseCaseSchedulerImpl() {
        mThreadPoolExecutor = new ThreadPoolExecutor(
                POOL_SIZE,
                MAX_POOL_SIZE,
                TIMEOUT,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(POOL_SIZE));
    }

    @Override
    public void execute(Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }
}
