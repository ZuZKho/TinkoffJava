package edu.hw4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TasksTest {

    Animal barsik = new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 5000, false);
    Animal belka = new Animal("Belka", Animal.Type.CAT, Animal.Sex.F, 2, 40, 4000, false);
    Animal bobik = new Animal("Bobik", Animal.Type.DOG, Animal.Sex.M, 7, 60, 10000, true);
    Animal sky = new Animal("Sky sky", Animal.Type.BIRD, Animal.Sex.F, 2, 15, 100, false);
    Animal nemo = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 20, 300, false);
    Animal karabas = new Animal("Karabas", Animal.Type.SPIDER, Animal.Sex.M, 1, 2, 30, true);
    Animal bob = new Animal("Bob", Animal.Type.DOG, Animal.Sex.M, 12, 120, 15000, true);
    Animal alice = new Animal("Alice", Animal.Type.DOG, Animal.Sex.F, 11, 105, 12000, false);
    Animal tuko = new Animal("Tuko tuko tuko", Animal.Type.BIRD, Animal.Sex.M, 2, 10, 5, false);
    Animal whale = new Animal("Whale", Animal.Type.FISH, Animal.Sex.M, 25, 7000, 10000000, false);
    Animal witch = new Animal("Witch", Animal.Type.FISH, Animal.Sex.F, -2, 0, 10000000, true);

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
        assertEquals(Optional.of(sky), Tasks.task4(List.of(barsik, belka, sky, karabas)));
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

    @Test
    void problem11Test() {
        assertEquals(List.of(bob), Tasks.task11(List.of(sky, barsik, bob, alice)));
    }

    @Test
    void problem12Test() {
        assertEquals(3, Tasks.task12(List.of(tuko, barsik, bob, alice)));
    }

    @Test
    void problem13Test() {
        assertEquals(List.of(tuko), Tasks.task13(List.of(sky, barsik, bob, alice, tuko)));
    }

    @Test
    void problem14Test() {
        assertTrue(Tasks.task14(List.of(sky, barsik, bob, alice, tuko), 115));
        assertFalse(Tasks.task14(List.of(sky, barsik, bob, alice, tuko), 125));
    }

    @Test
    void problem15Test() {
        assertEquals(15000, Tasks.task15(List.of(sky, barsik, bob, belka, bobik), 5, 7));
    }

    @Test
    void problem16Test() {
        assertEquals(
            List.of(barsik, belka, bob, bobik, alice, tuko),
            Tasks.task16(List.of(barsik, belka, bob, tuko, bobik, alice))
        );
    }

    @Test
    void problem17Test() {
        assertTrue(Tasks.task17(List.of(karabas, bob, alice)));
        assertFalse(Tasks.task17(List.of(karabas, bob)));
        assertFalse(Tasks.task17(List.of(karabas)));
    }

    @Test
    void problem18Test() {
        assertEquals(Optional.of(whale), Tasks.task18(List.of(barsik, belka), List.of(nemo, bob), List.of(tuko, bobik, alice, whale)));
    }

    @Test
    void problem19Test() {
        Map<String, Set<ValidationError>> expected = new HashMap<>();

        expected.put(witch.name(), new HashSet<>());
        expected.get(witch.name()).add(new ValidationError("Animal can't have negative age"));
        expected.get(witch.name()).add(new ValidationError("Animal can't have negative or zero height"));

        assertEquals(expected, Tasks.task19(List.of(witch, whale)));
    }

    @Test
    void problem20Test() {
        Map<String, String> expected = new HashMap<>();

        expected.put(witch.name(), "Animal can't have negative age\nAnimal can't have negative or zero height");

        assertEquals(expected, Tasks.task20(List.of(witch, whale)));
    }
}
