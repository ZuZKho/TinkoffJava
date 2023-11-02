package edu.hw3;

import edu.hw3.task5.Person;
import org.junit.jupiter.api.Test;

import static edu.hw3.task5.Task5.parseContacts;
import static org.junit.jupiter.api.Assertions.*;

class Task5Test {

    @Test
    void parseContactsTest1() {
        String[] input = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        Person[] expected = new Person[] {new Person("Thomas Aquinas"),
            new Person("Rene Descartes"),
            new Person("David Hume"),
            new Person("John Locke")};

        assertArrayEquals(expected, parseContacts(input, "ASC"));
    }

    @Test
    void parseContactsTest2() {
        String[] input = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        Person[] expected = new Person[] {new Person("Carl Gauss"),
            new Person("Leonhard Euler"),
            new Person("Paul Erdos")};

        assertArrayEquals(expected, parseContacts(input, "DESC"));
    }

    @Test
    void parseContactsTest3() {
        String[] input = null;
        Person[] expected = new Person[]{};

        assertArrayEquals(expected, parseContacts(input, "DESC"));
    }

    @Test
    void parseContactsTest4() {
        String[] input = new String[]{};
        Person[] expected = new Person[]{};

        assertArrayEquals(expected, parseContacts(input, "DESC"));
    }

    @Test
    void parseContactsTest5() {
        String[] input = new String[] {"John", "Thomas Aquinas", "David Lume", "Rene"};
        Person[] expected = new Person[] {new Person("Thomas Aquinas"),
            new Person("John"),
            new Person("David Lume"),
            new Person("Rene")};

        assertArrayEquals(expected, parseContacts(input, "ASC"));
    }
}
