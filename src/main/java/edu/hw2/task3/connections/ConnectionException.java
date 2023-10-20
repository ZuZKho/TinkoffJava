package edu.hw2.task3.connections;

public class ConnectionException extends RuntimeException {

    public ConnectionException() {
    }

    public ConnectionException(Throwable cause) {
        this.initCause(cause);
    }
}
