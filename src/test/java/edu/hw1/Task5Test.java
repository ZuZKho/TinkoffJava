package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task5Test {

    @Test
    @DisplayName("task 5: test 1")
    void test1() {
        assertTrue(Task5.isPalindromeDescendant(11211230));
    }

    @Test
    @DisplayName("task 5: test 2")
    void test2() {
        assertTrue(Task5.isPalindromeDescendant(23336014));
    }

    @Test
    @DisplayName("task 5: test 3")
    void test3() {
        assertTrue(Task5.isPalindromeDescendant(11));
    }

    @Test
    @DisplayName("task 5: test 4")
    void test4() {
        // 98988 -> 17178 -> 888
        assertTrue(Task5.isPalindromeDescendant(98988));
    }

    @Test
    @DisplayName("task 5: test 5")
    void test5() {
        // 123456 -> 3711 -> 102 -> 12
        assertFalse(Task5.isPalindromeDescendant(123456));
    }
}
