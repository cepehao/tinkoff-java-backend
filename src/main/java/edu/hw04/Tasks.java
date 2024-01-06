package edu.hw04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

final class Tasks {
    private Tasks() {

    }

    /*
     * 1. Отсортировать животных по росту от самого маленького к самому большому -> List<Animal>
     */
    public static List<Animal> task1(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    /*
     * 2. Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых -> List<Animal>
     */
    public static List<Animal> task2(List<Animal> animals, int k) {
        if (k < 1) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    /*
     * 3. Сколько животных каждого вида -> Map<Animal.Type, Integer>
     */
    public static Map<Animal.Type, Integer> task3(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
            ));
    }

    /*
     * 4. У какого животного самое длинное имя -> Animal
     */
    public static Animal task4(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    /*
     * 5. Каких животных больше: самцов или самок -> Sex
     */
    public static Animal.Sex task5(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::sex,
                Collectors.counting()
            ))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    /*
     * 6. Самое тяжелое животное каждого вида -> Map<Animal.Type, Animal>
     */
    public static Map<Animal.Type, Animal> task6(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
            ));
    }

    /*
     * 7. K-е самое старое животное -> Animal
     */
    public static Animal task7(List<Animal> animals, int k) {
        if (animals.size() < k || k <= 1) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }

    /*
     * 8. Самое тяжелое животное среди животных ниже k см -> Optional<Animal>
     */
    public static Optional<Animal> task8(List<Animal> animals, int k) {
        if (k < 0) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .filter(animal -> animal.height() < k)
            .findFirst();
    }

    /*
     * 9. Сколько в сумме лап у животных в списке -> Integer
     */
    public static int task9(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    /*
     * 10. Список животных, возраст у которых не совпадает с количеством лап -> List<Animal>
     */
    public static List<Animal> task10(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    /*
     * 11. Список животных, которые могут укусить (bites == true) и рост которых превышает 100 см -> List<Animal>
     */
    @SuppressWarnings("MagicNumber")
    public static List<Animal> task11(List<Animal> animals) {
        return animals.stream()
            .filter(it -> it.bites() && it.height() > 100)
            .toList();
    }

    /*
     * 12. Сколько в списке животных, вес которых превышает рост -> Integer
     */
    public static int task12(List<Animal> animals) {
        return (int) animals.stream()
            .filter(it -> it.weight() > it.height()).count();
    }

    /*
     * 13. Список животных, имена которых состоят из более чем двух слов -> List<Animal>
     */
    public static List<Animal> task13(List<Animal> animals) {
        return animals.stream()
            .filter(it -> it.name().strip().split(" ").length > 2)
            .toList();
    }

    /*
     * 14. Есть ли в списке собака ростом более k см -> Boolean
     * Считаем, что собака не может быть ниже 10см.
     */
    @SuppressWarnings("MagicNumber")
    public static boolean task14(List<Animal> animals, int k) {
        if (k < 10) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .anyMatch(it -> it.type() == Animal.Type.DOG && it.height() > k);
    }

    /*
     * 15. Найти суммарный вес животных каждого вида, которым от k до l лет -> Map<Animal.Type, Integer>
     */
    public static Map<Animal.Type, Integer> task15(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(it -> it.age() >= k && it.age() <= l)
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.summingInt(Animal::weight)
            ));
    }

    /*
     * 16. Список животных, отсортированный по виду, затем по полу, затем по имени -> List<Animal>
     */
    public static List<Animal> task16(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name)
            )
            .toList();
    }

    /*
     * 17. Правда ли, что пауки кусаются чаще, чем собаки -> Boolean(если данных для ответа недостаточно, вернуть false)
     */
    public static boolean task17(List<Animal> animals) {
        // Если в выборке кол-во собак не равняется кол-ву пауков - выборка некорректная
        var spiderCount = animals.stream()
            .filter(it -> it.type() == Animal.Type.SPIDER)
            .count();

        var dogCount = animals.stream()
            .filter(it -> it.type() == Animal.Type.DOG)
            .count();

        if (spiderCount != dogCount) {
            return false;
        }

        return animals.stream()
            .filter(it -> it.type().equals(Animal.Type.SPIDER) && it.bites()).count()
            > animals.stream().filter(it -> it.type().equals(Animal.Type.DOG) && it.bites()).count();
    }

    /*
     * 18. Найти самую тяжелую рыбку в 2-х или более списках -> Animal
     */
    @SafeVarargs
    public static Animal task18(List<Animal>... animals) {
        return Arrays.stream(animals)
            .flatMap(List::stream)
            .filter(it -> it.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .orElse(null);
    }

    /*
     * 19. Животные, в записях о которых есть ошибки: вернуть имя и список ошибок -> Map<String, Set<ValidationError>>.
     */
    public static Map<String, Set<ValidationError>> task19(List<Animal> animals) {
        return animals.stream()
            .filter(it -> !Validation.validate(it).isEmpty())
            .collect(Collectors.toMap(
                Animal::name,
                Validation::validate
            ));
    }

    /*
     * 20. Сделать результат предыдущего задания более читабельным:
     *     вернуть имя и названия полей с ошибками, объединенные в строку -> Map<String, String>
     */
    public static Map<String, String> task20(List<Animal> animals) {
        return task19(animals)
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                it -> String.join(", ", it.getValue().stream().map(ValidationError::toString).toList())
            ));
    }
}
