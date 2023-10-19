package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    @Test
    @DisplayName("task 4: test 1")
    void task4test1() {
        assertEquals("214365", Task4.fixString("123456"));
    }

    @Test
    @DisplayName("task 4: test 2")
    void task4test2() {
        assertEquals("This is a mixed up string.", Task4.fixString("hTsii  s aimex dpus rtni.g"));
    }

    @Test
    @DisplayName("task 4: test 3")
    void task4test3() {
        assertEquals("abcde", Task4.fixString("badce"));
    }

}
