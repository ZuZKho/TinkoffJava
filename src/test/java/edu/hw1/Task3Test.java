package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    @Test
    @DisplayName("task 3: test 1")
    void task3test1() {
        assertTrue(Task3.isNestable(new Integer[] {1, 2, 3, 4}, new Integer[] {0, 6}));
    }

    @Test
    @DisplayName("task 3: test 2")
    void task3test2() {
        assertTrue(Task3.isNestable(new Integer[] {3, 1}, new Integer[] {4, 0}));
    }

    @Test
    @DisplayName("task 3: test 3")
    void task3test3() {
        assertFalse(Task3.isNestable(new Integer[] {9, 9, 8}, new Integer[] {8, 9}));
    }

    @Test
    @DisplayName("task 3: test 4")
    void task3test4() {
        assertFalse(Task3.isNestable(new Integer[] {1, 2, 3, 4}, new Integer[] {2, 3}));
    }

}
