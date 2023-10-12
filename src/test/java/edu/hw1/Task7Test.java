package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task7Test {

    @Test
    @DisplayName("task 7: test 1")
    void test1() {
        assertEquals(4, Task7.rotateRight(8, 1));
    }

    @Test
    @DisplayName("task 7: test 2")
    void test2() {
        // 9: 1001 -> 1100 -> 0110
        assertEquals(6, Task7.rotateRight(9, 2));
    }

    @Test
    @DisplayName("task 7: test 3")
    void test3() {
        assertEquals(1, Task7.rotateLeft(16, 1));
    }

    @Test
    @DisplayName("task 7: test 4")
    void test4() {
        assertEquals(6, Task7.rotateLeft(17, 2));
    }

}
