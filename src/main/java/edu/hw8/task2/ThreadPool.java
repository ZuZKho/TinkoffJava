package edu.hw8.task2;

public interface ThreadPool extends AutoCloseable {

    void start();

    void join();

    void execute(Runnable runnable);
}
