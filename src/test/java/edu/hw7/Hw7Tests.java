package edu.hw7;

import edu.hw7.task3.Person;
import edu.hw7.task3.PersonDatabaseInterface;
import edu.hw7.task3.ReadWriteLockPersonDatabase;
import edu.hw7.task3.SynchronizedPersonDatabase;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Hw7Tests {

    @Test
    void incrementerTest1() {
        Incrementer incrementer = new Incrementer(0);

        incrementer.multiThreadIncrement(5, 10);

        assertEquals(50, incrementer.get());
    }

    @Test
    void incrementerTest2() {
        Incrementer incrementer = new Incrementer(0);

        incrementer.multiThreadIncrement(1, 10);

        assertEquals(10, incrementer.get());
    }

    @Test
    void factorialTest1() {
        assertEquals(BigInteger.valueOf(1), FactorialCounter.factorial(0));
        assertEquals(BigInteger.valueOf(1), FactorialCounter.factorial(1));
    }

    @Test
    void factorialTest2() {
        assertEquals(BigInteger.valueOf(3628800), FactorialCounter.factorial(10));
    }

    @Test
    @DisplayName("One thread")
    void piTest1() {
        PiNumberFinder piNumberFinder = new PiNumberFinder();
        for (int i = 0; i < 10; i++) {
            assertTrue(Math.abs(Math.PI - piNumberFinder.calculatePiNumber(100000)) <= 0.05);
        }
    }

    @Test
    @DisplayName("Multithread")
    void piTest2() {
        PiNumberFinder piNumberFinder = new PiNumberFinder();
        for (int i = 0; i < 10; i++) {
            assertTrue(Math.abs(Math.PI - piNumberFinder.multithreadCalculatePiNumber(5, 100000)) <= 0.05);
        }
    }

    @Test
    @DisplayName("piFinder statistics")
    @Disabled
    void piTestStatistics() {

        PiNumberFinder piNumberFinder = new PiNumberFinder();
        List<Integer> pointsCount = Arrays.asList(100, 10000, 1000000, 10000000);
        List<Integer> threadsCount = Arrays.asList(1, 2, 3, 5, 10);
        int triesCount = 10;

        for (var points : pointsCount) {
            for (var threadCount : threadsCount) {

                double totalAccuracy = 0;
                long totalTime = 0;

                for (int i = 0; i < triesCount; i++) {
                    Long start = System.nanoTime();

                    double result;
                    if (threadCount == 1) {
                        result = piNumberFinder.calculatePiNumber(points);
                    } else {
                        result = piNumberFinder.multithreadCalculatePiNumber(threadCount, points);
                    }

                    Long end = System.nanoTime();

                    totalAccuracy += Math.abs(Math.PI - result);
                    totalTime += end - start;
                }

                System.out.printf(
                    "Points: %d, Threads: %d, Accuracy: %f, MilliSeconds: %d\n",
                    points,
                    threadCount,
                    totalAccuracy / triesCount,
                    totalTime / triesCount / 1000000
                );
            }
            System.out.println();
        }
    }

// Не знаю как тестить raceCondition
//
//    @Nested
//    @DisplayName("Checking raceCondition on PersonDatabases")
//    class raceCondition {
//
//        private void testBody(Supplier<PersonDatabaseInterface> constructor) {
//            // Arrange
//            AtomicInteger errorsCount = new AtomicInteger(0);
//            final int triesCount = 3000;
//
//            // Act
//            for (int i = 0; i < triesCount; i++) {
//
//                PersonDatabaseInterface personDatabase = new SynchronizedPersonDatabase();
//                Thread adder = new Thread(() -> {
//                    Person person1 = new Person(1, "Matvey", "Pushkina 12", "+72282282288");
//                    Person person2 = new Person(2, "Andrey", "Lenina 13", "+71112223344");
//
//                    personDatabase.add(person1);
//                    personDatabase.add(person2);
//                });
//
//                Thread checker = new Thread(() -> {
//                    try {
//                        adder.wait(10);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    if (personDatabase.findByAddress("Pushkina 12") == null) {
//                            if (personDatabase.findByName("Matvey") != null) {
//                                errorsCount.incrementAndGet();
//                            }
//                        }
//
//                        if (personDatabase.findByName("Andrey") == null) {
//                            if (personDatabase.findByAddress("Lenina 13") != null) {
//                                errorsCount.incrementAndGet();
//                            }
//                        }
//
//                });
//
//                adder.start();
//                checker.start();
//                try {
//                    adder.join();
//                    checker.join();
//                } catch (InterruptedException e) {
//                    Logger.getLogger("myLogger").info("Error while multithreading");
//                }
//            }
//
//            // Assert
//            assertEquals(0, errorsCount.get());
//        }
//
//        @Test
//        void synchronizedPersonDatabaseTest() {
//            testBody(SynchronizedPersonDatabase::new);
//        }
//
//        @Test
//        void ReadWriteLockPersonDatabaseTest() {
//            testBody(ReadWriteLockPersonDatabase::new);
//        }
//    }

    @Nested
    @DisplayName("Checking PersonDatabases correctness")
    class personDatabaseCorrectnessTests {

        private void testBody1(Supplier<PersonDatabaseInterface> constructor) {
            // Arrange
            PersonDatabaseInterface personDatabase = constructor.get();
            Person person1 = new Person(1, "Matvey", "Pushkina 12", "+72282282288");
            Person person2 = new Person(2, "Andrey", "Lenina 13", "+71112223344");

            // Act
            personDatabase.add(person1);
            personDatabase.add(person2);

            // Assert
            assertEquals(person1, personDatabase.findByName("Matvey"));
            assertEquals(person1, personDatabase.findByAddress("Pushkina 12"));
            assertEquals(person1, personDatabase.findByPhone("+72282282288"));
            assertNull(personDatabase.findByName("Alexey"));
        }

        private void testBody2(Supplier<PersonDatabaseInterface> constructor) {
            // Arrange
            PersonDatabaseInterface personDatabase = constructor.get();
            Person person1 = new Person(1, "Matvey", "Pushkina 12", "+72282282288");
            Person person2 = new Person(2, "Andrey", "Lenina 13", "+71112223344");

            // Act
            personDatabase.add(person1);
            personDatabase.add(person2);
            personDatabase.delete(1);

            // Assert
            assertNull(personDatabase.findByPhone("+72282282288"));
        }

        @Test
        void synchronizedPersonDatabaseTest1() {
            testBody1(SynchronizedPersonDatabase::new);
        }

        @Test
        void ReadWriteLockPersonDatabaseTest1() {
            testBody1(ReadWriteLockPersonDatabase::new);
        }

        @Test
        void synchronizedPersonDatabaseTest2() {
            testBody2(SynchronizedPersonDatabase::new);
        }

        @Test
        void ReadWriteLockPersonDatabaseTest2() {
            testBody2(ReadWriteLockPersonDatabase::new);
        }
    }

}
