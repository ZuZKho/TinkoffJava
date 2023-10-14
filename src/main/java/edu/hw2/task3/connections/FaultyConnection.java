package edu.hw2.task3.connections;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FaultyConnection implements Connection {

    Logger logger = Logger.getLogger("myLogger");

    public FaultyConnection() {

        logger.log(Level.INFO, "Connection established.");
    }

    public void execute(String command) {
        Random rnd = new Random();
        if (rnd.nextInt(2) == 0) {
            logger.info("Error while executing command...");
            throw new ConnectionException();
        }

        logger.info("Command '" + command + "' was executed successfully.");
    }

    public void close() {
        logger.info("Connection closed.");
    }
}
