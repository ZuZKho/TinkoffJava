package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class Tasks {

    private Tasks() {
    }

    public static List<Animal> task1(List<Animal> input) {
        return input.stream()
            .sorted(Comparator.comparing(Animal::height))
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
                return -Integer.compare(h1.age(), h2.age());
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

    public static List<Animal> task11(List<Animal> input) {
        final int MINHEIGHT = 100;
        return input.stream()
            .filter(t -> t.height() > MINHEIGHT && t.bites())
            .toList();
    }

    public static Integer task12(List<Animal> input) {
        return input.stream()
            .filter(t -> t.weight() > t.height())
            .map(e -> 1)
            .reduce(0, Integer::sum);
    }

    public static List<Animal> task13(List<Animal> input) {
        final int THREE = 3;
        return input.stream()
            .filter(t -> {
                String[] lst = t.name().split(" ");
                return lst.length >= THREE;
            })
            .toList();
    }

    public static Boolean task14(List<Animal> input, int k) {
        return input.stream()
            .anyMatch(t -> t.type() == Animal.Type.DOG && t.height() > k);
    }

    public static Integer task15(List<Animal> input, int k, int l) {
        return input.stream()
            .filter(t -> t.age() >= k && t.age() <= l)
            .map(Animal::weight)
            .reduce(0, Integer::sum);
    }

    public static List<Animal> task16(List<Animal> input) {
        return input.stream()
            .sorted(
                Comparator.comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name))
            .toList();
    }

    public static Boolean task17(List<Animal> input) {
        if (input.stream().noneMatch(t -> t.type() == Animal.Type.SPIDER)) {
            return false;
        }
        if (input.stream().noneMatch(t -> t.type() == Animal.Type.DOG)) {
            return false;
        }
        Double spiders = input.stream()
            .filter(t -> t.type() == Animal.Type.SPIDER)
            .collect(Collectors.averagingDouble(t -> t.bites() ? 1 : 0));
        Double dogs = input.stream()
            .filter(t -> t.type() == Animal.Type.DOG)
            .collect(Collectors.averagingDouble(t -> t.bites() ? 1 : 0));
        return spiders > dogs;
    }

    public static Optional<Animal> task18(List<Animal>... lists) {
        Comparator<Animal> myComparator = new Comparator<Animal>() {
            @Override
            public int compare(Animal h1, Animal h2) {
                return Integer.compare(h1.weight(), h2.weight());
            }
        };

        return Stream.of(lists)
            .flatMap(Collection::stream)
            .filter(t -> t.type() == Animal.Type.FISH)
            .max(myComparator);
    }

    public static Map<String, Set<ValidationError>> task19(List<Animal> input) {
        return input.stream()
            .filter(animal -> !Validator.getErrors(animal).isEmpty())
            .collect(Collectors.toMap(Animal::name, Validator::getErrors));
    }

    public static Map<String, String> task20(List<Animal> input) {
        return input.stream()
            .filter(animal -> !Validator.getErrors(animal).isEmpty())
            .collect(
                Collectors.toMap(
                    Animal::name,
                    t ->
                        Validator.getErrors(t).stream()
                            .map(ValidationError::getMessage)
                            .collect(Collectors.joining("\n"))
                )
            );
    }
}
