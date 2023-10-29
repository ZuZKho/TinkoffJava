package edu.hw4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasksTest {

    Animal barsik = new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 5000, false);
    Animal belka = new Animal("Belka", Animal.Type.CAT, Animal.Sex.F, 2, 40, 4000, false);
    Animal bobik = new Animal("Bobik", Animal.Type.DOG, Animal.Sex.M, 7, 60, 10000, true);
    Animal sky = new Animal("Sky", Animal.Type.BIRD, Animal.Sex.F, 2, 15, 100, false);
    Animal nemo = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 20, 300, false);
    Animal karabas = new Animal("Karabas", Animal.Type.SPIDER, Animal.Sex.M, 1, 2, 30, true);


    @Test
    void problem1Test() {
        assertEquals(List.of(sky, nemo, belka, bobik), Tasks.task1(List.of(belka, sky, bobik, nemo)));
    }

    @Test
    void problem2Test() {
        assertEquals(List.of(bobik, belka), Tasks.task2(List.of(belka, sky, bobik, nemo), 2));
    }

    @Test
    void problem3Test() {
        Map<Animal.Type, Integer> expected = new HashMap<>();
        expected.put(Animal.Type.CAT, 2);
        expected.put(Animal.Type.DOG, 1);
        assertEquals(expected, Tasks.task3(List.of(barsik, bobik, belka)));
    }

    @Test
    void problem4Test() {
        assertEquals(Optional.of(karabas), Tasks.task4(List.of(barsik, belka, sky, karabas)));
    }

    @Test
    void problem5Test() {
        assertEquals(Animal.Sex.F, Tasks.task5(List.of(belka, bobik, sky)));
        assertEquals(Animal.Sex.M, Tasks.task5(List.of(belka, bobik, sky, karabas)));
    }

    @Test
    void problem6Test() {
        Map<Animal.Type, Animal> expected = new HashMap<>();
        expected.put(Animal.Type.CAT, barsik);
        expected.put(Animal.Type.DOG, bobik);

        assertEquals(expected, Tasks.task6(List.of(belka, barsik, bobik)));
    }

    @Test
    void problem7Test() {
        assertEquals(Optional.of(barsik), Tasks.task7(List.of(barsik, belka, bobik, nemo, karabas), 2));
    }

    @Test
    void problem8Test() {
        assertEquals(Optional.of(barsik), Tasks.task8(List.of(barsik, belka, bobik), 60));
    }

    @Test
    void problem9Test() {
        assertEquals(18, Tasks.task9(List.of(barsik, belka, sky, karabas)));
    }


    @Test
    void problem10Test() {
        assertEquals(List.of(barsik), Tasks.task10(List.of(sky, barsik)));
    }

}
