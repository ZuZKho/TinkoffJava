package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task6Test {

    @Test
    @DisplayName("task 6: test 1")
    void test1() {
        assertEquals(5, Task6.countK(6621));
    }

    @Test
    @DisplayName("task 6: test 2")
    void test2() {
        // 1112 -> 0999 -> 8991 -> 8082 -> 8532 -> 6174
        assertEquals(5, Task6.countK(1112));
    }

    @Test
    @DisplayName("task 6: test 3")
    void test3() {
        assertEquals(0, Task6.countK(6174));
    }
}
