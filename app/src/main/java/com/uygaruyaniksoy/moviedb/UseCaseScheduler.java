package com.uygaruyaniksoy.moviedb;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UseCaseScheduler {
    public static final int POOL_SIZE = 2;
    public static final int MAX_POOL_SIZE = 4;
    public static final int TIMEOUT = 30;

    public static ThreadPoolExecutor executor;

    public static void execute(Runnable runnable) {
        if (UseCaseScheduler.executor == null) {
            executor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT,
                    TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(POOL_SIZE));
        }
        executor.execute(runnable);
    }
}
