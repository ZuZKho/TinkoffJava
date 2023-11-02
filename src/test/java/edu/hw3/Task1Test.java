package edu.hw3;

import org.junit.jupiter.api.Test;

import static edu.hw3.Task1.atbash;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @Test
    void atbashTest1() {
        assertEquals("Svool dliow!", atbash("Hello world!"));
    }

    @Test
    void atbashTest2() {
        assertEquals("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi", atbash("Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler"));
    }

}
