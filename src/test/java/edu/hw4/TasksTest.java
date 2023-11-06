package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TasksTest {

    private final Animal cat1 = new Animal("Lusi", Animal.Type.CAT, Animal.Sex.F, 12, 30, 45, true);
    private final Animal cat2 = new Animal("Whiskers", Animal.Type.CAT, Animal.Sex.M, 5, 25, 15, false);
    //private final Animal cat3 = new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 3, 28, 18, true);
    private final Animal dog1 = new Animal("M a x", Animal.Type.DOG, Animal.Sex.M, 4, 105, 35, true);
    private final Animal dog2 = new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 6, 35, 30, false);
    //private final Animal dog3 = new Animal("Spike", Animal.Type.DOG, Animal.Sex.M, 7, 45, 40, true);
    //private final Animal bird1 = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 3, false);
    private final Animal bird2 = new Animal("Mr . L", Animal.Type.BIRD, Animal.Sex.M, 4, 12, 2, false);
    private final Animal fish1 = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 2, false);
    private final Animal spider1 = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 1, 1, 2, true);
    //private final Animal spider2 = new Animal("Tarantol", Animal.Type.SPIDER, Animal.Sex.M, 2, 2, 2, true);

    private List<Animal> getAnimals() {
        return List.of(cat1, cat2, dog1, dog2, bird2, fish1, spider1);
    }

    @Test
    void task1() {
        assertThat(Tasks.task1(getAnimals()))
            .isEqualTo(List.of(
                spider1, fish1, bird2, cat2, cat1, dog2, dog1
            ));
    }

    @Test
    @SuppressWarnings("MagicNumber")
    void task2() {
        assertThat(Tasks.task2(getAnimals(), 3))
            .isEqualTo(List.of(
                cat1, dog1, dog2
            ));

        assertThatThrownBy(
            () -> Tasks.task2(getAnimals(), -1)
        )
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void task3() {
        assertThat(
            Tasks.task3(getAnimals()))
            .isEqualTo(
                Map.of(
                    Animal.Type.CAT, 2,
                    Animal.Type.DOG, 2,
                    Animal.Type.BIRD, 1,
                    Animal.Type.FISH, 1,
                    Animal.Type.SPIDER, 1
                ));
    }

    @Test
    void task4() {
        assertThat(
            Tasks.task4(getAnimals()))
            .isEqualTo(spider1);

        assertThat(Tasks.task4(List.of())).isNull();
    }

    @Test
    void task5() {
        assertThat(
            Tasks.task5(getAnimals()))
            .isEqualTo(Animal.Sex.M);
    }

    @Test
    void task6() {
        assertThat(
            Tasks.task6(getAnimals()))
            .isEqualTo(
                Map.of(
                    Animal.Type.CAT, cat1,
                    Animal.Type.DOG, dog1,
                    Animal.Type.BIRD, bird2,
                    Animal.Type.FISH, fish1,
                    Animal.Type.SPIDER, spider1
                ));
    }

    @Test
    @SuppressWarnings("MagicNumber")
    void task7() {
        assertThat(
            Tasks.task7(getAnimals(), 5))
            .isEqualTo(bird2);

        assertThatThrownBy(
            () -> Tasks.task7(getAnimals(), 20)
        )
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Tasks.task7(getAnimals(), -1)
        )
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @SuppressWarnings("MagicNumber")
    void task8() {
        assertThat(
            Tasks.task8(getAnimals(), 20))
            .isEqualTo(Optional.of(bird2));

        assertThatThrownBy(
            () -> Tasks.task7(getAnimals(), -1)
        )
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @SuppressWarnings("MagicNumber")
    void task9() {
        assertThat(
            Tasks.task9(getAnimals()))
            .isEqualTo(26);
    }

    @Test
    void task10() {
        assertThat(
            Tasks.task10(getAnimals()))
            .isEqualTo(
                List.of(
                    cat1, cat2, dog2, bird2, fish1, spider1
                ));
    }

    @Test
    void task11() {
        assertThat(
            Tasks.task11(getAnimals()))
            .isEqualTo(
                List.of(
                    dog1
                ));
    }

    @Test
    void task12() {
        assertThat(
            Tasks.task12(getAnimals()))
            .isEqualTo(2);
    }

    @Test
    void task13() {
        assertThat(
            Tasks.task13(getAnimals()))
            .isEqualTo(
                List.of(
                    dog1, bird2
                ));
    }

    @Test
    @SuppressWarnings("MagicNumber")
    void task14() {
        assertThat(
            Tasks.task14(getAnimals(), 50))
            .isTrue();

        assertThat(
            Tasks.task14(getAnimals(), 300))
            .isFalse();
    }

    @Test
    @SuppressWarnings("MagicNumber")
    void task15() {
        assertThat(
            Tasks.task15(getAnimals(), 2, 4))
            .isEqualTo(Map.of(
                Animal.Type.DOG, dog1.weight(),
                Animal.Type.BIRD, bird2.weight()
            ));
    }

    @Test
    void task16() {
        assertThat(
            Tasks.task16(getAnimals()))
            .isEqualTo(
                List.of(
                    cat2, cat1, dog2, dog1, bird2, fish1, spider1
                ));
    }

    @Test
    void task17() {
        assertThat(
            Tasks.task17(getAnimals()))
            .isFalse();

        assertThat(
            Tasks.task17(List.of(
                dog1, dog2, spider1,
                new Animal("Tarantol", Animal.Type.SPIDER, Animal.Sex.M, 2, 2, 2, true)
            )))
            .isTrue();
    }

    @Test
    @SuppressWarnings("MagicNumber")
    void task18() {
        var huge = "huge";

        assertThat(
            Tasks.task18(
                getAnimals(),
                List.of(
                    new Animal(huge, Animal.Type.FISH, Animal.Sex.F, 3, 2, 7, false),
                    new Animal("fish7", Animal.Type.FISH, Animal.Sex.M, 5, 2, 2, false)
                ),
                List.of(
                    new Animal("newFish", Animal.Type.FISH, Animal.Sex.F, 2, 2, 2, false),
                    fish1
                )
            )
        ).isEqualTo(new Animal(huge, Animal.Type.FISH, Animal.Sex.F, 3, 2, 7, false));

        assertThat(Tasks.task18(List.of())).isNull();
    }

    @Test
    @SuppressWarnings("MagicNumber")
    void task19() {
        var badCat = "badCat";

        assertThat(
            Tasks.task19(List.of(
                dog1,
                cat2,
                spider1,
                new Animal("", null, Animal.Sex.F, 2, 3, 4, true),
                new Animal(badCat, Animal.Type.CAT, Animal.Sex.F, -1, 3, 4, true)
            )))
            .isEqualTo(Map.of(
                "",
                Set.of(
                    new ValidationError("Имя не может быть пустым", ValidationError.Field.NAME),
                    new ValidationError("Тип животного не может быть null", ValidationError.Field.TYPE)
                ),

                badCat,
                Set.of(new ValidationError("Возраст животного не может быть меньше 0", ValidationError.Field.AGE)
                )
            ));
    }

    @Test
    @SuppressWarnings("MagicNumber")
    void task20() {
        var badDog = "badDog";

        assertThat(
            Tasks.task20(List.of(
                dog1,
                cat2,
                spider1,
                new Animal(badDog, Animal.Type.DOG, Animal.Sex.F, -1, 3, 4, true)
            )))
            .isEqualTo(Map.of(
                badDog, "ValidationError{field=AGE, errorMessage='Возраст животного не может быть меньше 0'}"
            ));
    }
}
