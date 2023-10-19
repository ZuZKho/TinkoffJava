package edu.hw2.task3.connections;

import java.util.logging.Logger;

public class StableConnection implements Connection {

    Logger logger = Logger.getLogger("myLogger");

    public StableConnection() {
        logger.info("Connection established.");
    }

    public void execute(String command) {
        logger.info(String.format("Command '%s' was executed successfully.", command));
    }

    public void close() {
        logger.info("Connection closed.");
    }
}
