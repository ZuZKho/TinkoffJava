package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @Test
    @DisplayName("task 2: test 1")
    void task2test1() {
        assertEquals(3, Task2.countDigits(123));
    }

    @Test
    @DisplayName("task 2: test 2")
    void task2test2() {
        assertEquals(1, Task2.countDigits(8));
    }

    @Test
    @DisplayName("task 2: test 3")
    void task2test3() {
        assertEquals(1, Task2.countDigits(0));
    }

}
