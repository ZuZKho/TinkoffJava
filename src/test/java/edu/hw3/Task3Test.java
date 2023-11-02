package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static edu.hw3.Task3.freqDict;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    @Test
    void freqDictTest1() {
        HashMap<String, Integer> dictionary = new HashMap<>();
        dictionary.put("a", 2);
        dictionary.put("bb", 2);

        assertEquals(dictionary, freqDict(new ArrayList<>(Arrays.asList(new String[]{"a", "bb", "a", "bb"}))));
    }


    @Test
    void freqDictTest2() {
        HashMap<String, Integer> dictionary = new HashMap<>();
        dictionary.put("this", 1);
        dictionary.put("that", 1);
        dictionary.put("and", 2);

        assertEquals(dictionary, freqDict(new ArrayList<>(Arrays.asList(new String[]{"this", "and", "that", "and"}))));
    }

    @Test
    void freqDictTest3() {
        HashMap<String, Integer> dictionary = new HashMap<>();
        dictionary.put("код", 3);
        dictionary.put("bug", 1);

        assertEquals(dictionary, freqDict(new ArrayList<>(Arrays.asList(new String[]{"код", "код", "код", "bug"}))));
    }

    @Test
    void freqDictTest4() {
        HashMap<Integer, Integer> dictionary = new HashMap<>();
        dictionary.put(1, 2);
        dictionary.put(2, 2);

        assertEquals(dictionary, freqDict(new ArrayList<>(Arrays.asList(new Integer[]{1, 1, 2, 2}))));
    }
}
