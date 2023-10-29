package edu.hw4;

import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.toMap;

public class Tasks {

    public static List<Animal> task1(List<Animal> input) {
        Comparator<Animal> myComparator = new Comparator<Animal>() {
            @Override
            public int compare(Animal h1, Animal h2) {
                return Integer.compare(h1.height(), h2.height());
            }
        };

        return input.stream()
            .sorted(myComparator)
            .toList();
    }

    public static List<Animal> task2(List<Animal> input, int limit) {
        Comparator<Animal> myComparator = new Comparator<Animal>() {
            @Override
            public int compare(Animal h1, Animal h2) {
                return Integer.compare(h2.weight(), h1.weight());
            }
        };

        return input.stream()
            .sorted(myComparator)
            .limit(limit)
            .toList();
    }

    public static Map<Animal.Type, Integer> task3(List<Animal> input) {
        return input.stream()
            .collect(groupingBy(
                Animal::type,
                collectingAndThen(counting(), Long::intValue)
            ));
    }


    public static Optional<Animal> task4(List<Animal> input) {
        Comparator<Animal> myComparator = new Comparator<Animal>() {
            @Override
            public int compare(Animal h1, Animal h2) {
                return Integer.compare(h1.name().length(), h2.name().length());
            }
        };

        return input.stream().max(myComparator);
    }

    public static Animal.Sex task5(List<Animal> input) {
        return input.stream()
            .map(a -> (a.sex() == Animal.Sex.M ? 1 : -1))
            .reduce(0, Integer::sum) >= 0 ? Animal.Sex.M : Animal.Sex.F;
    }

    public static Map<Animal.Type, Animal> task6(List<Animal> input) {
        Comparator<Animal> myComparator = new Comparator<Animal>() {
            @Override
            public int compare(Animal h1, Animal h2) {
                return Integer.compare(h1.height(), h2.height());
            }
        };

        return input.stream()
            .collect(toMap(
                Animal::type,
                Function.identity(),
                (a, b) -> a.weight() > b.weight() ? a : b
            ));
    }

    public static Optional<Animal> task7(List<Animal> input, int k) {
        Comparator<Animal> myComparator = new Comparator<Animal>() {
            @Override
            public int compare(Animal h1, Animal h2) {
                return - Integer.compare(h1.age(), h2.age());
            }
        };

        return input.stream()
            .sorted(myComparator)
            .limit(k)
            .reduce((a, b) -> b);
    }

    public static Optional<Animal> task8(List<Animal> input, int k) {
        Comparator<Animal> myComparator = new Comparator<Animal>() {
            @Override
            public int compare(Animal h1, Animal h2) {
                return Integer.compare(h1.weight(), h2.weight());
            }
        };

        return input.stream()
            .filter(t -> t.height() < k)
            .max(myComparator);
    }

    public static Integer task9(List<Animal> input) {
        return input.stream().map(Animal::paws).reduce(0, Integer::sum);
    }

    public static List<Animal> task10(List<Animal> input) {
        return input.stream()
            .filter(t -> t.age() != t.paws())
            .toList();
    }
}
