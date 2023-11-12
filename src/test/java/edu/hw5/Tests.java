package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static edu.hw5.Task1.getDuration;
import static edu.hw5.Task2.getBadFridaysOfYear;
import static edu.hw5.Task2.getNextBadFriday;
import static edu.hw5.Task3.parseDate;
import static edu.hw5.Task4.isStrongPassword;
import static edu.hw5.Task5.isCarSign;
import static edu.hw5.Task6.substring;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {

    @Test
    @DisplayName("Task1 test1")
    void task1test1() {
        List<String> log = new ArrayList<>();
        log.add("2022-03-12, 20:20 - 2022-03-12, 23:50");
        log.add("2022-04-01, 21:30 - 2022-04-02, 01:20");

        Duration actual = getDuration(log);

        assertEquals(Duration.parse("PT3H40M"), actual);
    }

    @Test
    @DisplayName("Task1 test2")
    void task1test2() {
        List<String> log = new ArrayList<>();

        Duration actual = getDuration(log);

        assertNull(actual);
    }

    @Test
    @DisplayName("Task2 test1")
    void task2test1() {
        List<LocalDate> expected = new ArrayList<>();
        expected.add(LocalDate.of(2024, 9, 13));
        expected.add(LocalDate.of(2024, 12, 13));

        var badFridays = getBadFridaysOfYear(2024);

        assertEquals(expected, badFridays);
    }

    @Test
    @DisplayName("Task2 test2")
    void task2test2() {
        List<LocalDate> expected = new ArrayList<>();
        expected.add(LocalDate.of(1925, 2, 13));
        expected.add(LocalDate.of(1925, 3, 13));
        expected.add(LocalDate.of(1925, 11, 13));

        var badFridays = getBadFridaysOfYear(1925);

        assertEquals(expected, badFridays);
    }

    @Test
    @DisplayName("Task2 test3")
    void task2test3() {
        assertEquals(LocalDate.of(1925, 2, 13), getNextBadFriday(LocalDate.of(1925, 2, 13)));
    }

    @Test
    @DisplayName("Task2 test4")
    void task2test4() {
        assertEquals(LocalDate.of(1925, 2, 13), getNextBadFriday(LocalDate.of(1925, 2, 12)));
    }

    @Test
    @DisplayName("Task2 test5")
    void task2test5() {
        assertEquals(LocalDate.of(1926, 8, 13), getNextBadFriday(LocalDate.of(1925, 11, 14)));
    }

    @Test
    @DisplayName("Task3 test1: dash type")
    void task3test1() {
        assertEquals(Optional.of(LocalDate.of(2020, 10, 10)), parseDate("2020-10-10"));
        assertEquals(Optional.of(LocalDate.of(2020, 10, 1)), parseDate("2020-10-1"));
        assertEquals(Optional.empty(), parseDate("2020-100-10"));
        assertEquals(Optional.empty(), parseDate("2020-10-0"));
        assertEquals(Optional.empty(), parseDate("22020-10-0"));
        assertEquals(Optional.empty(), parseDate("2023-2-29"));
    }

    @Test
    @DisplayName("Task3 test2: slash type")
    void task3test2() {
        assertEquals(Optional.of(LocalDate.of(1976, 3, 10)), parseDate("10/3/1976"));
        assertEquals(Optional.of(LocalDate.of(20, 3, 1)), parseDate("1/3/20"));
        assertEquals(Optional.empty(), parseDate("2020/100/10"));
        assertEquals(Optional.empty(), parseDate("0/10/1234"));
        assertEquals(Optional.empty(), parseDate("29/2/2023"));
    }

    @Test
    @DisplayName("Task3 test3: day type")
    void task3test3() {
        assertEquals(Optional.of(LocalDate.now()), parseDate("today"));
        assertEquals(Optional.of(LocalDate.now().plusDays(1)), parseDate("tomorrow"));
        assertEquals(Optional.of(LocalDate.now().minusDays(1)), parseDate("yesterday"));
        assertEquals(Optional.empty(), parseDate("todayz"));
        assertEquals(Optional.empty(), parseDate("last week"));
    }

    @Test
    @DisplayName("Task3 test4: ago type")
    void task3test4() {
        assertEquals(Optional.of(LocalDate.now().minusDays(1)), parseDate("1 day ago"));
        assertEquals(Optional.of(LocalDate.now().minusDays(2234)), parseDate("2234 days ago"));
        assertEquals(Optional.of(LocalDate.now()), parseDate("0 days ago"));
        assertEquals(Optional.empty(), parseDate("-2 days ago"));
        assertEquals(Optional.empty(), parseDate("1 days ago"));
    }

    @Test
    @DisplayName("Task4 test1")
    void task4test1() {
        assertTrue(isStrongPassword("sdf$"));
        assertTrue(isStrongPassword("abs*sslksd**%kfjlk"));
        assertTrue(isStrongPassword("abs|sslksdkfjlk"));
    }

    @Test
    @DisplayName("Task4 test2")
    void task4test2() {
        assertFalse(isStrongPassword("abs/sslksdkfjlk"));
        assertFalse(isStrongPassword("a"));
    }

    @Test
    @DisplayName("Task5 test1")
    void task5test1() {
        assertTrue(isCarSign("А123ВЕ777"));
        assertTrue(isCarSign("О777ОО177"));
    }

    @Test
    @DisplayName("Task5 test2")
    void task5test2() {
        assertFalse(isCarSign("123АВЕ777"));
        assertFalse(isCarSign("А123ВГ77"));
        assertFalse(isCarSign("А123ВЕ7777"));
        assertFalse(isCarSign("АВ123ВЕ777"));
    }

    @Test
    @DisplayName("Task6 test1")
    void task6test1() {
        assertTrue(substring("achfdbaabgabcaabg", "abc"));
        assertTrue(substring("abbc", "abc"));
        assertTrue(substring("abbc", ""));
    }

    @Test
    @DisplayName("Task6 test2")
    void task6test2() {
        assertFalse(substring("ababab", "abc"));
    }

    @Test
    @DisplayName("Task7 test1: first pattern")
    void task7test1() {
        assertTrue(Task7.isFirstPattern("000"));
        assertTrue(Task7.isFirstPattern("10010"));
        assertTrue(Task7.isFirstPattern("110"));

        assertFalse(Task7.isFirstPattern(""));
        assertFalse(Task7.isFirstPattern("111"));
        assertFalse(Task7.isFirstPattern("00100"));
    }

    @Test
    @DisplayName("Task7 test2: second pattern")
    void task7test2() {
        assertTrue(Task7.isSecondPattern("01010101010"));
        assertTrue(Task7.isSecondPattern("00"));
        assertTrue(Task7.isSecondPattern("1"));
        assertTrue(Task7.isSecondPattern(""));

        assertFalse(Task7.isSecondPattern("10"));
        assertFalse(Task7.isSecondPattern("100101010"));
    }

    @Test
    @DisplayName("Task7 test3: third pattern")
    void task7test3() {
        assertTrue(Task7.isThirdPattern("001"));
        assertTrue(Task7.isThirdPattern("01"));
        assertTrue(Task7.isThirdPattern("1"));

        assertFalse(Task7.isThirdPattern(""));
        assertFalse(Task7.isThirdPattern("1010"));
    }

    @Test
    @DisplayName("Task8 test1: first pattern")
    void task8test1() {
        assertTrue(Task8.isFirstPattern("010"));
        assertTrue(Task8.isFirstPattern("1"));
        assertTrue(Task8.isFirstPattern("1101101"));

        assertFalse(Task8.isFirstPattern(""));
        assertFalse(Task8.isFirstPattern("11"));
        assertFalse(Task8.isFirstPattern("0010"));
    }

    @Test
    @DisplayName("Task8 test2: second pattern")
    void task8test2() {
        assertTrue(Task8.isSecondPattern("0"));
        assertTrue(Task8.isSecondPattern("10"));
        assertTrue(Task8.isSecondPattern("1111"));
        assertTrue(Task8.isSecondPattern("010101010"));

        assertFalse(Task8.isSecondPattern("101"));
        assertFalse(Task8.isSecondPattern("00"));
    }

    @Test
    @DisplayName("Task8 test3: third pattern")
    void task8test3() {
        assertTrue(Task8.isThirdPattern("000"));
        assertTrue(Task8.isThirdPattern("101010111"));
        assertTrue(Task8.isThirdPattern("010011110101011"));
        assertTrue(Task8.isThirdPattern(""));
        assertTrue(Task8.isThirdPattern("11111"));

        assertFalse(Task8.isThirdPattern("1010"));
        assertFalse(Task8.isThirdPattern("0000"));
    }

    @Test
    @DisplayName("Task8 test5: fifth pattern")
    void task8test5() {
        assertTrue(Task8.isFifthPattern("101"));
        assertTrue(Task8.isFifthPattern("1"));
        assertTrue(Task8.isFifthPattern(""));
        assertTrue(Task8.isFifthPattern("111110"));

        assertFalse(Task8.isFifthPattern("011111111"));
        assertFalse(Task8.isFifthPattern("0"));
    }

    @Test
    @DisplayName("Task8 test7: fifth pattern")
    void task8test7() {
        assertTrue(Task8.isSeventhPattern("10001001"));
        assertTrue(Task8.isSeventhPattern("0000100101010"));
        assertTrue(Task8.isSeventhPattern(""));
        assertTrue(Task8.isSeventhPattern("00"));
        assertTrue(Task8.isSeventhPattern("1"));
        assertTrue(Task8.isSeventhPattern("0"));
        assertTrue(Task8.isSeventhPattern("10101010"));
        assertTrue(Task8.isSeventhPattern("1010101"));

        assertFalse(Task8.isSeventhPattern("01100"));
        assertFalse(Task8.isSeventhPattern("11"));
        assertFalse(Task8.isSeventhPattern("00010010101011"));
    }

}
