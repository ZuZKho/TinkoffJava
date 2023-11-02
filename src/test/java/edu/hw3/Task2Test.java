package edu.hw3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static edu.hw3.Task2.clusterize;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @Test
    void clusterizeTest1() {
        assertArrayEquals(new String[]{"()", "()", "()"}, clusterize("()()()"));
    }

    @Test
    void clusterizeTest2() {
        assertArrayEquals(new String[]{"((()))"}, clusterize("((()))"));
    }

    @Test
    void clusterizeTest3() {
        assertArrayEquals(new String[]{"((()))", "(())", "()", "()", "(()())"}, clusterize("((()))(())()()(()())"));
    }

    @Test
    void clusterizeTest4() {
        assertArrayEquals(new String[]{"((())())", "(()(()()))"}, clusterize("((())())(()(()()))"));
    }
}
