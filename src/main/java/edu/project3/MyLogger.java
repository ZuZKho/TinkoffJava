package edu.project3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger {

    private static final Logger LOGGER = java.util.logging.Logger.getLogger("logger");

    private MyLogger() {
    }

    public static void log(Level level, String string) {
        LOGGER.log(level, string);
    }
}
