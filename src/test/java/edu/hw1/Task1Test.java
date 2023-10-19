package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

        @Test
        @DisplayName("task 1: no errors 1")
        void task1NoErrors1() {
            assertEquals(3333, Task1.minutesToSeconds("55:33"));
        }

        @Test
        @DisplayName("task 1: no errors 2")
        void task1NoErrors2() {
            assertEquals(59940, Task1.minutesToSeconds("999:00"));
        }

        @Test
        @DisplayName("task 1: seconds error 1")
        void task1SecondsError1() {
            assertEquals(-1, Task1.minutesToSeconds("55:333"));
        }

        @Test
        @DisplayName("task 1: seconds error 2")
        void task1SecondsError2() {
            assertEquals(-1, Task1.minutesToSeconds("0:-1"));
        }

        @Test
        @DisplayName("task 1: format error 1")
        void task1FormatError1() {
            assertEquals(-1, Task1.minutesToSeconds("55.33"));
        }

        @Test
        @DisplayName("task 1: format error 2")
        void task1FormatError2() {
            assertEquals(-1, Task1.minutesToSeconds("12f:33"));
        }
}
