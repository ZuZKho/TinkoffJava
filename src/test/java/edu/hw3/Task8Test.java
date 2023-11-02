package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task8Test {

    @Test
    void test1() {
        var iterator = new BackwardIterator<Integer>(List.of(1, 2, 3));
        assertEquals(3, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(1, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void test2() {
        var iterator = new BackwardIterator<Integer>(List.of(1));
        assertEquals(1, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void test3() {
        var iterator = new BackwardIterator<Integer>(List.of());
        assertFalse(iterator.hasNext());
    }

    @Test
    void test4() {
        var iterator = new BackwardIterator<Integer>(List.of(1));
        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }
}
