package edu.hw3;

import org.junit.jupiter.api.Test;
import static edu.hw3.Task4.convertToRoman;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class Task4Test {

    @Test
    void convertToRomanTest1() {
        assertEquals("MMMCDXXIV", convertToRoman(3424));
    }

    @Test
    void convertToRomanTest2() {
        assertEquals("MMMCMXCIX", convertToRoman(3999));
    }

    @Test
    void convertToRomanTest3() {
        assertNull(convertToRoman(4000));
    }

    @Test
    void convertToRomanTest4() {
        assertEquals("DCCCLXXXVIII", convertToRoman(888));
    }

    @Test
    void convertToRomanTest5() {
        assertEquals("XVI", convertToRoman(16));
    }

}
