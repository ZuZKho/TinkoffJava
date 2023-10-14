package edu.hw2.task4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallingPlusTest {

    @Test
    void test228() {
        CallingInfo result = Calling.callingInfo();
        assertEquals(result.className(), "CallingPlusTest");
        assertEquals(result.methodName(), "test228");
    }
}
