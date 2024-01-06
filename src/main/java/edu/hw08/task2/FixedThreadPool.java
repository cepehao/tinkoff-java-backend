package edu.hw08.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public final class FixedThreadPool implements ThreadPool {
    private final FixedThread[] threads;
    private final BlockingQueue<Runnable> tasks;
    private final AtomicBoolean isShutdown;

    private FixedThreadPool(FixedThread[] threads) {
        this.threads = threads;
        this.tasks = new LinkedBlockingQueue<>();
        this.isShutdown = new AtomicBoolean(false);
        start();
    }

    public static FixedThreadPool create(int countThreads) {
        if (countThreads <= 0) {
            throw new IllegalArgumentException("Введите кол-во потоков > 0");
        }
        return new FixedThreadPool(new FixedThread[countThreads]);
    }

    @Override
    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new FixedThread();
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (!isShutdown.get()) {
            try {
                tasks.put(runnable);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void close() {
        isShutdown.set(true);
    }

    private final class FixedThread extends Thread {
        @Override
        public void run() {
            while (!isShutdown.get() || !tasks.isEmpty()) {
                Runnable runnable;
                while ((runnable = tasks.poll()) != null) {
                    runnable.run();
                }
            }
        }
    }
}
