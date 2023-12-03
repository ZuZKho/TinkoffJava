package edu.hw8.task2;

import java.util.concurrent.LinkedBlockingDeque;

public class FixedThreadPool implements ThreadPool {

    private final Thread[] threads;
    private final LinkedBlockingDeque<Runnable> queue;

    private FixedThreadPool(int n) {
        threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            threads[i] = new Thread() {
                public void run() {

                    while (true) {
                        Runnable task;
                        try {
                            task = queue.take();
                        } catch (InterruptedException e) {
                            return;
                        }

                        task.run();
                        synchronized (queue) {
                            queue.notifyAll();
                        }
                    }
                }
            };
        }
        queue = new LinkedBlockingDeque<>();
    }

    public static FixedThreadPool create(int n) {
        return new FixedThreadPool(n);
    }

    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    public void execute(Runnable runnable) {
        try {
            queue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        for (var thread : threads) {
            thread.interrupt();
        }
    }

    public void join() {
        while (!queue.isEmpty()) {
            try {
                synchronized (queue) {
                    queue.wait();
                }
                //  Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        close();
    }

}
