package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Nested
    @DisplayName("Task1: time")
    class Task1 {
        @Test
        @DisplayName("task 1: no errors 1")
        void task1NoErrors1() {
            assertEquals(3333, Main.minutesToSeconds("55:33"));
        }

        @Test
        @DisplayName("task 1: no errors 2")
        void task1NoErrors2() {
            assertEquals(59940, Main.minutesToSeconds("999:00"));
        }

        @Test
        @DisplayName("task 1: seconds error 1")
        void task1SecondsError1() {
            assertEquals(-1, Main.minutesToSeconds("55:333"));
        }

        @Test
        @DisplayName("task 1: seconds error 2")
        void task1SecondsError2() {
            assertEquals(-1, Main.minutesToSeconds("0:-1"));
        }

        @Test
        @DisplayName("task 1: format error 1")
        void task1FormatError1() {
            assertEquals(-1, Main.minutesToSeconds("55.33"));
        }

        @Test
        @DisplayName("task 1: format error 2")
        void task1FormatError2() {
            assertEquals(-1, Main.minutesToSeconds("12f:33"));
        }
    }

    @Nested
    @DisplayName("Task2: count digits")
    class Task2 {
        @Test
        @DisplayName("task 2: test 1")
        void task2test1() {
            assertEquals(3, Main.countDigits(123));
        }

        @Test
        @DisplayName("task 2: test 2")
        void task2test2() {
            assertEquals(1, Main.countDigits(8));
        }

        @Test
        @DisplayName("task 2: test 3")
        void task2test3() {
            assertEquals(1, Main.countDigits(0));
        }
    }

    @Nested
    @DisplayName("Task3: is nestable")
    class Task3 {
        @Test
        @DisplayName("task 3: test 1")
        void task3test1() {
            assertTrue(Main.isNestable(new Integer[] {1, 2, 3, 4}, new Integer[] {0, 6}));
        }

        @Test
        @DisplayName("task 3: test 2")
        void task3test2() {
            assertTrue(Main.isNestable(new Integer[] {3, 1}, new Integer[] {4, 0}));
        }

        @Test
        @DisplayName("task 3: test 3")
        void task3test3() {
            assertFalse(Main.isNestable(new Integer[] {9, 9, 8}, new Integer[] {8, 9}));
        }

        @Test
        @DisplayName("task 3: test 4")
        void task3test4() {
            assertFalse(Main.isNestable(new Integer[] {1, 2, 3, 4}, new Integer[] {2, 3}));
        }
    }

    @Nested
    @DisplayName("Task4: fixString ")
    class Task4 {
        @Test
        @DisplayName("task 4: test 1")
        void task4test1() {
            assertEquals("214365", Main.fixString("123456"));
        }

        @Test
        @DisplayName("task 4: test 2")
        void task4test2() {
            assertEquals("This is a mixed up string.", Main.fixString("hTsii  s aimex dpus rtni.g"));
        }

        @Test
        @DisplayName("task 4: test 3")
        void task4test3() {
            assertEquals("abcde", Main.fixString("badce"));
        }
    }

    @Nested
    @DisplayName("Task5: isPalindromeDescendant")
    class Task5 {
        @Test
        @DisplayName("task 5: test 1")
        void test1() {
            assertTrue(Main.isPalindromeDescendant(11211230));
        }

        @Test
        @DisplayName("task 5: test 2")
        void test2() {
            assertTrue(Main.isPalindromeDescendant(23336014));
        }

        @Test
        @DisplayName("task 4: test 3")
        void test3() {
            assertTrue(Main.isPalindromeDescendant(11));
        }

        @Test
        @DisplayName("task 4: test 4")
        void test4() {
            // 98988 -> 17178 -> 888
            assertTrue(Main.isPalindromeDescendant(98988));
        }

        @Test
        @DisplayName("task 4: test 5")
        void test5() {
            // 123456 -> 3711 -> 102 -> 12
            assertFalse(Main.isPalindromeDescendant(123456));
        }
    }

    @Nested
    @DisplayName("Task6: countK")
    class Task6 {
        @Test
        @DisplayName("task 6: test 1")
        void test1() {
            assertEquals(5, Main.countK(6621));
        }

        @Test
        @DisplayName("task 6: test 2")
        void test2() {
            // 1112 -> 0999 -> 8991 -> 8082 -> 8532 -> 6174
            assertEquals(5, Main.countK(1112));
        }

        @Test
        @DisplayName("task 6: test 3")
        void test3() {
            assertEquals(0, Main.countK(6174));
        }
    }

    @Nested
    @DisplayName("Task7: shifts")
    class Task7 {
        @Test
        @DisplayName("task 7: test 1")
        void test1() {
            assertEquals(4, Main.rotateRight(8, 1));
        }

        @Test
        @DisplayName("task 7: test 2")
        void test2() {
            // 9: 1001 -> 1100 -> 0110
            assertEquals(6, Main.rotateRight(9, 2));
        }

        @Test
        @DisplayName("task 7: test 3")
        void test3() {
            assertEquals(1, Main.rotateLeft(16, 1));
        }

        @Test
        @DisplayName("task 7: test 4")
        void test4() {
            assertEquals(6, Main.rotateLeft(17, 2));
        }
    }

    @Nested
    @DisplayName("Task8: knights")
    class Task8 {
        @Test
        @DisplayName("task 8: test 1")
        void test1() {
            int[][] testArr = new int[][]
                {
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 1, 0, 1, 0},
                    {0, 1, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0}
                };

            assertTrue(Main.knightBoardCapture(testArr));
        }

        @Test
        @DisplayName("task 8: test 2")
        void test2() {
            int[][] testArr = new int[][]
                {
                    {1, 0, 1, 0, 1, 0, 1, 0},
                    {0, 1, 0, 1, 0, 1, 0, 1},
                    {0, 0, 0, 0, 1, 0, 1, 0},
                    {0, 0, 1, 0, 0, 1, 0, 1},
                    {1, 0, 0, 0, 1, 0, 1, 0},
                    {0, 0, 0, 0, 0, 1, 0, 1},
                    {1, 0, 0, 0, 1, 0, 1, 0},
                    {0, 0, 0, 1, 0, 1, 0, 1}
                };

            assertFalse(Main.knightBoardCapture(testArr));
        }

        @Test
        @DisplayName("task 8: test 3")
        void test3() {
            int[][] testArr = new int[][]
                {
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0}
                };

            assertFalse(Main.knightBoardCapture(testArr));
        }
    }
}
