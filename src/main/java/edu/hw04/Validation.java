package edu.hw04;

import java.util.HashSet;
import java.util.Set;

public final class Validation {
    private Validation() {
    }

    public static Set<ValidationError> validate(Animal animal) {

        Set<ValidationError> errorSet = new HashSet<>();

        if (animal.name().isBlank()) {
            errorSet.add(new ValidationError("Имя не может быть пустым", ValidationError.Field.NAME));
        }

        if (animal.type() == null) {
            errorSet.add(new ValidationError("Тип животного не может быть null", ValidationError.Field.TYPE));
        }

        if (animal.sex() == null) {
            errorSet.add(new ValidationError("Пол животного не может быть null", ValidationError.Field.SEX));
        }

        if (animal.age() < 0) {
            errorSet.add(new ValidationError("Возраст животного не может быть меньше 0", ValidationError.Field.AGE));
        }

        if (animal.height() < 0) {
            errorSet.add(new ValidationError("Рост животного не может быть меньше 0", ValidationError.Field.HEIGHT));
        }

        if (animal.weight() < 0) {
            errorSet.add(new ValidationError("Вес животного не может быть < 0", ValidationError.Field.WEIGHT));
        }

        return errorSet;
    }
}
