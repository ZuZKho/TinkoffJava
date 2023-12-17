package edu.hw10.task1;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RandomObjectGeneratorTest {

    @RepeatedTest(10)
    void testConstructor() {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        assertDoesNotThrow(() -> {
            var myClass = rog.nextObject(Person.class);

            if (myClass instanceof Person person) {
                assertNotNull(person.name());
                assertTrue(person.age() >= 1 && person.age() <= 100);
                assertTrue(person.id() >= 0);
            } else {
                fail();
            }
        });
    }

    @RepeatedTest(10)
    void testFabricMethod() {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        assertDoesNotThrow(() -> {
            var myClass = rog.nextObject(Person.class, "create");

            if (myClass instanceof Person person) {
                assertNotNull(person.name());
                assertTrue(person.age() >= 1 && person.age() <= 100);
            } else {
                fail();
            }
        });
    }

    @RepeatedTest(10)
    void testRecord() {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        assertDoesNotThrow(() -> {
            var myClass = rog.nextObject(PersonRecord.class);

            if (myClass instanceof PersonRecord person) {
                assertNotNull(person.name());
                assertTrue(person.age() >= 1 && person.age() <= 100);
                assertTrue(person.id() >= 0);
            } else {
                fail();
            }
        });
    }

}
