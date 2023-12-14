package edu.hw1;

import static com.github.stefanbirkner.systemlambda.SystemLambda.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task0Test {

    @Test
    void application_writes_multiple_lines_to_System_err(
    ) throws Exception {
        String text = tapSystemErrNormalized(Task0::task0);


        String lines[] = text.split("\n");
        if (lines.length > 1) {
            assertEquals(lines[1], "INFO: Hello, world!");
        }
    }
}
