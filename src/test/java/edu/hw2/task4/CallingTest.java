package edu.hw2.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CallingTest {

    @Test
    void testCallingInfo() {
        CallingInfo result = Calling.callingInfo();
        assertEquals(result.className(), "CallingTest");
        assertEquals(result.methodName(), "testCallingInfo");
    }
}
