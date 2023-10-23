package edu.hw3;

import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task7Test {

    @Test
    void test1(){
        TreeMap<String, String> tree = new TreeMap<>(new Task7Comparator());
        tree.put(null, "test");
        tree.put("12", "12");

        assertTrue(tree.containsKey(null));
        assertTrue(tree.containsKey("12"));
        tree.remove(null);
        assertFalse(tree.containsKey(null));
    }

}
